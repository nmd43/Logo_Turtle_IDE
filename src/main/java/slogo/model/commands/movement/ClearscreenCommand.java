package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

import java.awt.geom.Point2D;

public class ClearscreenCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {

    environment.getActiveTurtles().executeAction(turtle -> {
      // Calculate the distance to home (0,0) from the current position for any possible use
      Point2D.Double currentPosition = turtle.getPosition();
      double distanceToHome = currentPosition.distance(0, 0);

      // Move the turtle to home (0,0)
      turtle.setPosition(new Point2D.Double(0, 0));

      // Notify the observer about the position update
      observerNotifier.notifyPositionUpdate(turtle.getId(), 0, 0);

      // Clear the screen or trails
      observerNotifier.notifyClearScreen(turtle.getId(), true);


      // Just in case, here is the distance to home
      //observerNotifier.notifyInformationUpdateDouble(distanceToHome);

    });

  }
}
