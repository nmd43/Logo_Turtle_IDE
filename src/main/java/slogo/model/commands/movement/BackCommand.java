package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

import java.awt.geom.Point2D;

public class BackCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    //If any other amount than 1 parameter is entered or if the parameter is not a number,
    // throw exception
    if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
      throw new IllegalArgumentException("Back command expects a single numeric parameter.");
    }


    double distance = ((Number) parameters[0]).doubleValue();

    environment.getActiveTurtles().executeAction(turtle -> {
      // Calculate the new position based on the current heading and distance but in the opposite direction
      double radians = Math.toRadians(turtle.getHeading());
      double deltaX = distance * Math.cos(radians) * -1; // Reverse the direction
      double deltaY = distance * Math.sin(radians) * -1; // Reverse the direction
      Point2D.Double newPosition = new Point2D.Double(turtle.getPosition().getX() + deltaX,
          turtle.getPosition().getY() + deltaY);

      // Update the turtle's position
      turtle.setPosition(newPosition);

      // Notify all observers of the position update
      observerNotifier.notifyPositionUpdate(turtle.getId(), newPosition.getX(), newPosition.getY());

    });

  }
}
