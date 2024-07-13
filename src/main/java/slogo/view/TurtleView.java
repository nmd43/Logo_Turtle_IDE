package slogo.view;

import javafx.scene.image.ImageView;

/**
 * TurtleView interface allows for the creation of turtles to be displayed.
 *
 * @author Jonathan Esponda
 */
public interface TurtleView {

  /**
   * Returns the imageView of the turtle.
   */
  ImageView getImageView();

  /**
   * Updates the turtle position when the update method is called.
   *
   * @param x new x position
   * @param y new y position
   */
  void updatePosition(double x, double y);

  /**
   * Updates the angle the turtle is facing when the update method is called.
   *
   * @param degrees new degree change
   */
  void updateHeading(double degrees);

  /**
   * Updates the visibility of the turtle.
   *
   * @param id turtle id being affected
   * @param visible boolean that decides whether the turtle is visible or not
   */
  void updateVisibility(int id, boolean visible);

  /**
   * Set the direction of the turtle.
   *
   * @param id turtle id being affected
   * @param degrees double that the turtle's direction will be changed to
   */
  void setHeading(int id, double degrees);
}
