package slogo.model.commands.query;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class ShowingCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // No parameters expected for SHOWING command

        environment.getActiveTurtles().executeAction(turtle -> {
          int visible = turtle.isVisible() ? 1 : 0;

          // Return 1 if turtle is visible, 0 if it is not
          observerNotifier.notifyInformationUpdateInteger(visible);
        });

    }
}
