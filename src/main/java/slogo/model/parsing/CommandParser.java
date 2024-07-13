package slogo.model.parsing;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import slogo.controller.ApplicationController;
import slogo.model.commands.CommandInterface;
import slogo.model.infoLists.HistoryManager;
import slogo.model.infoLists.VariableManager;
import slogo.model.parsing.CommandMetadata.Parameter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandParser {
  private final Map<String, CommandMetadata> commandMetadataMap = new HashMap<>();
  private Map<String, String> aliasToCanonicalNameMap = new HashMap<>();
  private final VariableManager variableManager;
  private final HistoryManager historyManager;
  private ResourceBundle commandsBundle;
  private String currentLanguage = "English"; //default language is English


  public CommandParser() {
    loadCommandMetadata();
    variableManager = VariableManager.getInstance();
    historyManager = HistoryManager.getInstance();
  }

  public void setLanguage(String language) {
    this.currentLanguage = language;
    this.commandsBundle = ResourceBundle.getBundle("properties.commands." + currentLanguage, Locale.forLanguageTag(currentLanguage));

  }

  public void loadCommandMetadata() {
    try {
      URL url = getClass().getClassLoader().getResource("commands");
      File commandsDir = new File(url.toURI());
      processDirectory(commandsDir);
    } catch (Exception e) {
      e.printStackTrace();
      // Handle exception appropriately
    }
  }

  private void processDirectory(File directory) throws ParserConfigurationException, IOException, SAXException {
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          // Recursively process the directory
          processDirectory(file);
        } else {
          // Process the file if it's not a directory
          CommandMetadata metadata = parseCommandFile(file);
          System.out.println("Metadata: " + metadata.getCanonicalName());
          commandMetadataMap.put(metadata.getCanonicalName().toLowerCase(), metadata);
          aliasToCanonicalNameMap.put(metadata.getCanonicalName().toLowerCase(), metadata.getCanonicalName().toLowerCase());

          //aliasToCanonicalNameMap.put(metadata.getCanonicalName().toLowerCase(), metadata.toString().toLowerCase());

          // Map each alias to its canonical name
          for (String alias : metadata.getAliases()) {
            aliasToCanonicalNameMap.put(alias.toLowerCase(), metadata.getCanonicalName().toLowerCase());
            //commandMetadataMap.put(alias, metadata);
          }

        }
      }
    }
  }

  private CommandMetadata parseCommandFile(File file) throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(file);
    doc.getDocumentElement().normalize();

    NodeList commandNodes = doc.getElementsByTagName("Command");
    Element commandElement = (Element) commandNodes.item(0); // Assuming there's only one command for now

    String canonicalName = commandElement.getElementsByTagName("CanonicalName").item(0).getTextContent();

    List<Parameter> parameters = new ArrayList<>();
    NodeList parameterNodes = commandElement.getElementsByTagName("Parameter");
    for (int i = 0; i < parameterNodes.getLength(); i++) {
      Element parameterElement = (Element) parameterNodes.item(i);
      String paramName = parameterElement.getAttribute("name");
      String paramType = parameterElement.getAttribute("type");
      String paramDescription = parameterElement.getAttribute("description");
      parameters.add(new Parameter(paramName, paramType, paramDescription));
    }

    int numberOfExpectedParameters = Integer.parseInt(commandElement.getElementsByTagName("NumberOfExpectedParameters").item(0).getTextContent());
    String implementingClassName = commandElement.getElementsByTagName("ImplementingClassName").item(0).getTextContent();

    List<String> aliases = new ArrayList<>();
    NodeList aliasList = commandElement.getElementsByTagName("Alias");
    for (int i = 0; i < aliasList.getLength(); i++) {
      aliases.add(aliasList.item(i).getTextContent());
    }

    return new CommandMetadata(canonicalName, parameters, numberOfExpectedParameters, implementingClassName, aliases);
  }


  public ParsedCommand parseCommand(String input) throws ParsingException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String[] parts = input.split("\\s+");
    if (isCommentOrEmpty(input)) {
      return null;
    }

    String commandKey = getCommandKey(parts);
    CommandMetadata metadata = getCommandMetadata(commandKey);
    validateNumberOfParameters(parts, metadata);

    CommandInterface command = createCommandInstance(metadata);
    Object[] parameters = parseParameters(parts, metadata);

    // Add the command to history
    historyManager.addToHistory(input);
    return new ParsedCommand(command, parameters);
  }

  private boolean isCommentOrEmpty(String input) {
    return input.trim().isEmpty() || input.trim().startsWith("#");
  }

  private String getCommandKey(String[] parts) throws ParsingException {
    String userCommandKey = parts[0].toLowerCase();
    //System.out.println("")
    String canonicalCommandKey;

    // Try to get canonical name directly from alias or user input
    canonicalCommandKey = aliasToCanonicalNameMap.get(userCommandKey);
    //First, get error message in correct language
    String errorMessage = commandsBundle.getString("InvalidCommand");

    if (canonicalCommandKey == null) {
      // If not found, attempt to translate if not using English
      if (!currentLanguage.equals("English")) {
        try {
          String translatedKey = commandsBundle.getString(userCommandKey);
          canonicalCommandKey = aliasToCanonicalNameMap.get(translatedKey.toLowerCase());
        } catch (MissingResourceException e) {
          // If translation fails or not found in alias map, the command is invalid
          throw new ParsingException(errorMessage + ": " + userCommandKey);
        }
      } else {
        // If still not found and using English, the command is invalid
        throw new ParsingException(errorMessage + ": " + userCommandKey);
      }
    }

    if (canonicalCommandKey == null || !commandMetadataMap.containsKey(canonicalCommandKey)) {
      throw new ParsingException(errorMessage + ": " + userCommandKey);
    }
    return canonicalCommandKey;
  }


  private CommandMetadata getCommandMetadata(String commandKey) {
    return commandMetadataMap.get(commandKey);
  }

  private void validateNumberOfParameters(String[] parts, CommandMetadata metadata) throws ParsingException {
    int expectedParameters = metadata.getNumberOfExpectedParameters();
    if (parts.length > expectedParameters + 1) {
      throw new ParsingException("Invalid Number of Parameters: " + (parts.length - 1) + ". Expected parameters: " + expectedParameters);
    }
  }

  private CommandInterface createCommandInstance(CommandMetadata metadata) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Class<?> clazz = Class.forName(metadata.getImplementingClassName());
    return (CommandInterface) clazz.getDeclaredConstructor().newInstance();
  }

  private Object[] parseParameters(String[] parts, CommandMetadata metadata) throws ParsingException {
    List<Object> parameters = new ArrayList<>();
    if (metadata.getNumberOfExpectedParameters() > 0 && parts.length > 1) {
      for (int i = 1; i < parts.length; i++) {
        parameters.add(parseParameter(parts[i], metadata.getParameters().get(i - 1).getType()));
      }
    }
    return parameters.toArray();
  }

  private Object parseParameter(String param, String parameterType) throws ParsingException {
    if (param.startsWith(":")) {
      return parseUserDefinedVariable(param.substring(1));
    } else {
      return parseLiteral(param, parameterType);
    }
  }

  private Object parseUserDefinedVariable(String variableName) throws ParsingException {
    Map<String, Double> userDefinedVariables = variableManager.getVariables();
    if (userDefinedVariables.containsKey(variableName)) {
      return userDefinedVariables.get(variableName);
    } else {
      throw new ParsingException("Invalid Variable");
    }
  }

  private Object parseLiteral(String param, String parameterType) throws ParsingException {
    Class<?> expectedType = mapParameterType(parameterType);
    try {
      if (expectedType.equals(Double.class)) {
        return Double.parseDouble(param);
      } else if (expectedType.equals(Integer.class)) {
        return Integer.parseInt(param);
      } else if (expectedType.equals(String.class)) {
        return param;
      } else {
        throw new ParsingException("Invalid parameter type");
      }
    } catch (NumberFormatException e) {
      throw new ParsingException("Invalid parameter type");
    }
  }

  private Class<?> mapParameterType(String parameterType) throws ParsingException {
      return switch (parameterType) {
          case "double" -> Double.class;
          case "int", "integer" -> Integer.class;
          case "string" -> String.class;
          default -> throw new ParsingException("Unsupported parameter type: " + parameterType);
      };
  }
}

