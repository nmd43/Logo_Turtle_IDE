package slogo.view;

import java.io.File;
import javafx.stage.Stage;
import slogo.config.FileSelector;
import slogo.config.XmlManager;
import slogo.controller.ApplicationController;


/**
 * SimulationLoader takes in the saved slogo file, parses it, and then the loadNewMainScene method
 * creates the scene.
 */
public interface SimulationLoader {

  /**
   * loadSimulation loads a saved Slogo file, .slogo is the only extension accepted.
   *
   * @param controller    Access to the controller so that previous history can be executed on a
   *                      load.
   * @param sceneSwitcher sceneSwitcher that allows for switching to different scenes
   */
  default void loadSimulation(ApplicationController controller, SceneSwitcher sceneSwitcher) {
    try {
      FileSelector fileSelector = new FileSelector();
      Stage stage = new Stage();
      File file = fileSelector.makeChooser("*.slogo")
          .showOpenDialog(stage);
      if (file != null) {
        XmlManager xmlManager = new XmlManager(file, controller);
        loadNewMainScene(xmlManager, sceneSwitcher, controller);
        xmlManager.runHistoryCommands();
      } else {
        System.out.println("No file selected");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * loadNewMainScene switches to the saved main scene
   *
   * @param xmlManager    takes in the file manager to be able to retrieve information from the
   *                      saved file.
   * @param sceneSwitcher sceneSwitcher that allows for switching to different scenes
   */
  default void loadNewMainScene(XmlManager xmlManager, SceneSwitcher sceneSwitcher,
      ApplicationController controller) {
    sceneSwitcher.switchToScene(new MainScene(sceneSwitcher,
        xmlManager.getConfigurationValue("language"), controller));
  }

}
