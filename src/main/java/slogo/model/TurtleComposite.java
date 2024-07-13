package slogo.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.function.Consumer;
/**
 * Represents a composite of turtles in the SLogo environment. This class implements the TurtleInterface
 * and acts as a composite object in the composite pattern, allowing for operations to be performed on multiple
 * turtles as if they were a single entity.
 */
public class TurtleComposite implements TurtleInterface {
  private final List<TurtleInterface> turtles = new ArrayList<>();

  /**
   * Adds a turtle to the composite group and sorts the group by turtle ID.
   *
   * @param turtle The turtle to add.
   */

  public void addTurtle(TurtleInterface turtle) {
    turtles.add(turtle);
    turtles.sort(Comparator.comparingInt(TurtleInterface::getId));
  }

  /**
   * Unsupported in composite context. Throws UnsupportedOperationException if called.
   */
  @Override
  public int getId() {
    throw new UnsupportedOperationException("Composite does not support getId");
  }

  /**
   * Unsupported in composite context. Throws UnsupportedOperationException if called.
   */
  @Override
  public Point2D.Double getPosition() {
    throw new UnsupportedOperationException("Composite does not support getPosition directly");
  }

  /**
   * Sets the position for all turtles in the composite.
   *
   * @param position The new position as a {@link Point2D.Double} object.
   */
  @Override
  public void setPosition(Point2D.Double position) {
    turtles.forEach(turtle -> turtle.setPosition(position));
  }

  /**
   * Unsupported in composite context. Throws UnsupportedOperationException if called.
   */
  @Override
  public double getHeading() {
    throw new UnsupportedOperationException("Composite does not support getHeading directly");
  }

  /**
   * Unsupported in composite context. Throws UnsupportedOperationException if called.
   */
  @Override
  public void setHeading(double heading) {
    turtles.forEach(turtle -> turtle.setHeading(heading));
  }

  /**
   * Unsupported in composite context. Throws UnsupportedOperationException if called.
   */
  @Override
  public boolean isPenDown() {
    throw new UnsupportedOperationException("Composite does not support isPenDown directly");
  }

  /**
   * Sets the pen down state for all turtles in the composite.
   *
   * @param penDown True to put the pen down, false to lift it up.
   */
  @Override
  public void setPenDown(boolean penDown) {
    turtles.forEach(turtle -> setPenDown(penDown));
  }

  /**
   * Unsupported in composite context. Throws UnsupportedOperationException if called.
   */
  @Override
  public boolean isVisible() {
    throw new UnsupportedOperationException("Composite does not support isVisible directly");
  }

  /**
   * Sets the visibility for all turtles in the composite.
   *
   * @param visible True to make the turtles visible, false to hide them.
   */
  @Override
  public void setVisible(boolean visible) {
    turtles.forEach(turtle -> setVisible(visible));

  }

  /**
   * Executes a given action on each turtle in the composite.
   *
   * @param action A {@link Consumer<TurtleInterface>} specifying the action to be performed.
   */
  @Override
  public void executeAction(Consumer<TurtleInterface> action) {
    for(TurtleInterface turtle: turtles) {
      turtle.executeAction(action);
    }
  }

  /**
   * Provides access to a copy of the internal list of turtles for external manipulation or queries.
   *
   * @return A copy of the list of turtles in the composite.
   */
  // Provides access to the internal list of turtles for external manipulation or queries
  public List<TurtleInterface> getTurtles() {
    return new ArrayList<>(turtles); // Return a copy to prevent external modification
  }
}
