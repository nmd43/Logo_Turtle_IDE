package slogo.model;
import java.awt.geom.Point2D;
import java.util.function.Consumer;

/**
 * This interface defines the behaviors and properties of a Turtle in the SLogo environment.
 * A Turtle has a position, heading (orientation), pen state (up or down), and visibility.
 * Additionally, it supports executing actions that can modify its state.
 */
public interface TurtleInterface {
  /**
   * Gets the unique identifier of the turtle.
   *
   * @return The turtle's ID.
   */
  int getId();

  /**
   * Gets the current position of the turtle.
   *
   * @return A {@link Point2D.Double} object representing the turtle's position.
   */
  Point2D.Double getPosition();
  /**
   * Sets the position of the turtle.
   *
   * @param position The new position as a {@link Point2D.Double} object.
   */
  void setPosition(Point2D.Double position);
  /**
   * Gets the current heading of the turtle.
   *
   * @return The turtle's heading as a double.
   */
  double getHeading();
  /**
   * Sets the heading (orientation) of the turtle.
   *
   * @param heading The new heading as a double.
   */
  void setHeading(double heading);
  /**
   * Checks if the turtle's pen is down.
   *
   * @return True if the pen is down, false otherwise.
   */
  boolean isPenDown();
  /**
   * Sets the state of the turtle's pen.
   *
   * @param penDown True to put the pen down, false to lift it up.
   */
  void setPenDown(boolean penDown);
  /**
   * Checks if the turtle is visible.
   *
   * @return True if the turtle is visible, false otherwise.
   */
  boolean isVisible();
  /**
   * Sets the visibility of the turtle.
   *
   * @param visible True to make the turtle visible, false to hide it.
   */
  void setVisible(boolean visible);
  /**
   * Executes a given action on the turtle.
   * This method allows for flexible modifications to the turtle's state beyond the basic set/get methods.
   *
   * @param action A {@link Consumer<TurtleInterface>} that specifies the action to be performed on the turtle.
   */
  void executeAction(Consumer<TurtleInterface> action);

}
