package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle; //going to get rid of this when done
import slogo.model.TurtleInterface;
import slogo.model.commands.CommandInterface;

import java.awt.geom.Point2D;

public class ForwardCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
      throw new IllegalArgumentException("Forward command expects a single numeric parameter.");
    }

    double distance = ((Number) parameters[0]).doubleValue();
    System.out.println("Distance: " + distance);

    // Apply forward movement to all turtles (or a single turtle, depending on the environment's setup)
    environment.getActiveTurtles().executeAction(turtle -> {
      // The movement logic remains the same, now encapsulated within a lambda expression
      double radians = Math.toRadians(turtle.getHeading());
      double deltaX = distance * Math.cos(radians);
      double deltaY = distance * Math.sin(radians);
      Point2D.Double newPosition = new Point2D.Double(turtle.getPosition().getX() + deltaX,
          turtle.getPosition().getY() + deltaY);
      turtle.setPosition(newPosition);
      observerNotifier.notifyPositionUpdate(turtle.getId(), newPosition.getX(), newPosition.getY());
    });
  }
}