package slogo.view;


/**
 * Interface that allows subclass scenes to switch to different scenes
 *
 * @author Jonathan Esponda
 */
public interface SceneSwitcher {

  /**
   * This method switches to a new AbstractScene
   *
   * @param scene: AbstractScene that will be switched to
   */
  void switchToScene(Scene scene);

  /**
   * Open file prompt to load previous session
   */
  void loadSimulation(String action);


}
