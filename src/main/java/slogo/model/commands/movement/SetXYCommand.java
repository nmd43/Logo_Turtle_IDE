package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

import java.awt.geom.Point2D;

public class SetXYCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
      throw new IllegalArgumentException("SETXY command expects two numeric parameters.");
    }

    double newX = ((Number) parameters[0]).doubleValue();
    double newY = ((Number) parameters[1]).doubleValue();


    environment.getActiveTurtles().executeAction(turtle -> {
      Point2D.Double currentPosition = turtle.getPosition();

      // Calculate distance moved
      double distanceMoved = currentPosition.distance(newX, newY);

      // Update the turtle's position
      turtle.setPosition(new Point2D.Double(newX, newY));
      Point2D.Double updatedPos = turtle.getPosition();
      System.out.println("New Point:" + updatedPos);

      // Notify observers about the position update
      observerNotifier.notifyPositionUpdate(turtle.getId(), newX, newY);

      // notify observers about the distance moved
      observerNotifier.notifyInformationUpdateDouble(distanceMoved);

    });

  }
}
