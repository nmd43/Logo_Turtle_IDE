package slogo.config;

/**
 * Interface for FileGrabbers that will allow for users to get a file chooser and limit them
 * to the specific file extensions.
 *
 * @author Jonathan Esponda
 */
public interface FileGrabber {

  /**
   * MakeChooser creates the file chooser that will be displayed to the user.
   *
   * @param extensionAccepted the only file extension that will be accepted
   */
  javafx.stage.FileChooser makeChooser(String extensionAccepted);

  }


