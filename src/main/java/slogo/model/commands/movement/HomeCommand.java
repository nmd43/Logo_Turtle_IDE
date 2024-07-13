package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

import java.awt.geom.Point2D;

public class HomeCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    environment.getActiveTurtles().executeAction(turtle -> {
      Point2D.Double currentPosition = turtle.getPosition();

      // Calculate the distance to home (0,0) from the current position
      double distanceToHome = currentPosition.distance(0, 0);

      // Move the turtle to home
      turtle.setPosition(new Point2D.Double(0, 0));

      // Notify the observer about the position update
      observerNotifier.notifyPositionUpdate(turtle.getId(), 0, 0);

      //Just in case the observer wants the distance returned instead
      //observerNotifier.notifyInformationUpdateDouble(distanceToHome);

    });
  }
}
