package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class SetHeadingCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
      throw new IllegalArgumentException("SetHeading command expects a single numeric parameter.");
    }

    environment.getActiveTurtles().executeAction(turtle -> {
      double newHeading = ((Number) parameters[0]).doubleValue();
      double currentHeading = turtle.getHeading();
      double degreesTurned = Math.abs(currentHeading - newHeading);

      // Update the turtle's heading
      turtle.setHeading(newHeading);

      // Notify observers of the heading update
      observerNotifier.notifyHeadingUpdate(turtle.getId(), newHeading);

    });


  }
}
