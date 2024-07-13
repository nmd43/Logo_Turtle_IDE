package slogo.model;

import java.awt.geom.Point2D;
import java.util.function.Consumer;

public class SingleTurtle implements TurtleInterface {
  private Point2D.Double position;
  private double heading;
  private boolean penDown;
  private boolean visible;
  private final int id;

  public SingleTurtle(int id) {
    this.id = id;
    this.position = new Point2D.Double(0, 0); // Start at the center
    this.heading = 0; // Start facing North
    this.penDown = true; // Start with pen down
    this.visible = true; //Start with visible turtle
  }

  // Getters and setters for position, heading, penDown, and visible
  @Override
  public int getId() {
    return id;
  }

  // Getters and setters for position, heading, and penDown
  @Override
  public Point2D.Double getPosition() {
    return position;
  }

  @Override
  public void setPosition(Point2D.Double position) {
    this.position = position;
  }

  @Override
  public double getHeading() {
    return heading;
  }

  @Override
  public void setHeading(double heading) {
    this.heading = heading;
  }

  @Override
  public boolean isPenDown() {
    return penDown;
  }

  @Override
  public void setPenDown(boolean penDown) {
    this.penDown = penDown;
  }

  @Override
  public boolean isVisible() {return visible; }

  @Override
  public void setVisible(boolean visible) {this.visible = visible; }

  @Override
  public void executeAction(Consumer<TurtleInterface> action) {
    action.accept(this);
  }

}
