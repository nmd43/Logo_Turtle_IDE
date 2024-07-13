package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class LeftCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
      throw new IllegalArgumentException("Left command expects a single numeric parameter.");
    }


    double degrees = ((Number) parameters[0]).doubleValue();
    System.out.println("Degrees:" + degrees);

    // Get the active turtle from the environment
    environment.getActiveTurtles().executeAction(activeTurtle -> {
      if (activeTurtle == null) {
        throw new IllegalStateException("No active turtle found in the environment.");
      }

      // Calculate the new heading by subtracting the degrees (since it's a left/counter-clockwise turn)
      double newHeading = (activeTurtle.getHeading() + degrees) % 360;
      System.out.println("New Heading: " + newHeading);

      // Normalize the heading to be within 0-360 degrees
      newHeading = (newHeading < 0) ? 360 + newHeading : newHeading;
      System.out.println("NewHeading Normalized" + newHeading);

      // Set the turtle's new heading
      activeTurtle.setHeading(newHeading);

      // Notify observers about the heading update

      observerNotifier.notifyHeadingUpdate(activeTurtle.getId(), degrees);
    });

  }
}
