package slogo.model.commands.movement;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class HideTurtleCommand implements CommandInterface {

  @Override
  public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
    environment.getActiveTurtles().executeAction(turtle -> {
      boolean isVisible = turtle.isVisible(); // Assume there's a method to check visibility

      // If the turtle is not visible, show it
      if (isVisible) {
        turtle.setVisible(false); // Assume there's a method to set visibility
        observerNotifier.notifyTurtleStateChange(turtle.getId(), false);
      }
    });

  }
}
