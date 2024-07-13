package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class RightCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
      throw new IllegalArgumentException("Right command expects a single numeric parameter.");
    }

    environment.getActiveTurtles().executeAction(turtle -> {
      double degrees = ((Number) parameters[0]).doubleValue();
      // Turning right (clockwise) decreases the angle
      double newHeading = (turtle.getHeading() - degrees) % 360;
      if (newHeading < 0) {
        newHeading += 360; // Normalize the heading
      }

      // Update the turtle's heading
      turtle.setHeading(newHeading);

      // Notify observers of the heading update
      observerNotifier.notifyHeadingUpdate(turtle.getId(), -1 * degrees);
    });


  }
}
