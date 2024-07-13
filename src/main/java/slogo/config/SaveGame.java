package slogo.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Saves game by allowing user to select where to save and then writing xmlData to that file. Used
 * similar code to my saveGame in cell society.
 *
 * @author Jonathan Esponda
 */
public class SaveGame {

  private Stage stage;

  public SaveGame(Stage stage) {
    this.stage = stage;
  }

  /**
   * Saves game by allowing user to select where to save and then writing xmlData to that file.
   *
   * @param gameData string that is getting written to the saved file
   */
  public void createXmlFile(String gameData) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Game");
    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Slogo "
        + "Files",
        "*.slogo");
    fileChooser.getExtensionFilters().add(extensionFilter);
    File file = fileChooser.showSaveDialog(stage);

    if (file != null) {
      try (FileWriter writer = new FileWriter(file)) {
        writer.write(gameData);
        System.out.println("File saved to " + file.getAbsolutePath());
      } catch (IOException e) {
        System.out.println("File was not saved");
      }
    }
  }

}
