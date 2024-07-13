package slogo.config;

import java.io.File;
import javafx.stage.FileChooser;

/**
 * Code for FileSelector was inspired by the initial project code provided for CellSociety. The
 * FileSelector implements FileGrabber and allows for users to select a file from their computer to
 * upload based on the specified extension.
 *
 * @author Jonathan Esponda
 */

public class FileSelector implements FileGrabber {

  private static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";

  /**
   * MakeChooser creates the file chooser that will be displayed to the user.
   *
   * @param extensionAccepted the only file extension that will be accepted
   */
  @Override
  public FileChooser makeChooser(String extensionAccepted) {
    FileChooser result = new FileChooser();
    result.setTitle("Retrieve Turtle PNG");
    result.setInitialDirectory(new File(DATA_FILE_FOLDER));
    result.getExtensionFilters()
        .setAll(new FileChooser.ExtensionFilter("Data Files", extensionAccepted));
    return result;
  }
}


