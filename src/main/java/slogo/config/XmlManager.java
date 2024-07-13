package slogo.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import slogo.controller.ApplicationController;

/**
 * XmlManager class that will be used to grab the information from the slogo files that will be
 * modeled after XML files. Borrowed from my CellSociety project.
 *
 * @author Jonathan Esponda
 */
public class XmlManager {

  private Document document;
  private Map<String, String> attributeMap;
  private ApplicationController applicationController;

  /**
   * XmlManager method that will be used to grab the information from the slogo files that will be
   * modeled after XML files.
   */

  public XmlManager(File file, ApplicationController controller) {
    applicationController = controller;
    try {
      File xmlFile = new File(String.valueOf(file));
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      document = documentBuilder.parse(xmlFile);
      document.getDocumentElement().normalize();
      validateXml();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      System.out.println("File Configuration Error" + "Your XML file is "
          + "incorrectly"
          + " formatted: " + " " + e.getMessage());
    } catch (IllegalArgumentException e) {
      document = null;
    }
  }

  /**
   * getConfigurationValue method that grabs the value of the elements in the saved slogo files.
   *
   * @param elementName element tag that we are retreiving the value from
   */
  public String getConfigurationValue(String elementName) {
    NodeList nodeList = document.getElementsByTagName(elementName);
    if (nodeList.getLength() > 0) {
      Node node = nodeList.item(0);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        return element.getTextContent();
      }
    }
    return "Error, no such value exists";
  }

  /**
   * validateXML method that checks to make sure that the slogo files have the required element
   * tags.
   */
  private void validateXml() {
    NodeList nodeList = document.getElementsByTagName("*");
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node instanceof Element) {
        Element element = (Element) node;
        String tagName = element.getTagName();
        if (tagName.equals("language") || tagName.equals("call") || tagName.equals("slogo")) {
          String elementValue = getConfigurationValue(tagName);
          if (elementValue.isEmpty()) {
            throw new IllegalArgumentException(tagName + " tag cannot be empty");
          }
        }
      }
    }
  }

  /**
   * runHistoruCommands method that gets the calls from within the call elements and runs them by
   * sending them to the model.
   */
  public void runHistoryCommands() throws Exception {
    NodeList nodeList = document.getElementsByTagName("call");
    applicationController.handleCommandInput("clearscreen");
    try {
      for (int i = 0; i < nodeList.getLength(); i++) {
        Element callElement = (Element) nodeList.item(i);
        String command = callElement.getTextContent();
        applicationController.handleCommandInput(command);
      }
    } catch (Error e) {
      e.printStackTrace();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
