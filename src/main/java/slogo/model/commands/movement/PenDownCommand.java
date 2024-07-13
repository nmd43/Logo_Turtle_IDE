package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class PenDownCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    // Get the active turtle from the environment
    environment.getActiveTurtles().executeAction(activeTurtle -> {
      if (activeTurtle == null) {
        throw new IllegalStateException("No active turtle found in the environment.");
      }

      // Set the turtle's pen state to down
      activeTurtle.setPenDown(true);

      // Notify observers about the pen state change
      observerNotifier.notifyPenStateChange(activeTurtle.getId(), true);
    });

  }
}
