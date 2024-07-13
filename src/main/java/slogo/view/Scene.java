package slogo.view;

/**
 * Scene represents a parent abstract view
 *
 * @author Jonathan Esponda
 */
public interface Scene {

  /**
   * initScene initializes a JavaFX Scene
   *
   * @param width:  width of screen
   * @param height: height of screen
   */
  void initScene(int width, int height);

  /**
   * updates a JavaFX Scene
   *
   * @param elapsedTime: elapsed time for frame rates
   */
  void update(double elapsedTime);


  /**
   * Returns the current Scene
   *
   * @return current Scene
   */
  javafx.scene.Scene getScene();
}