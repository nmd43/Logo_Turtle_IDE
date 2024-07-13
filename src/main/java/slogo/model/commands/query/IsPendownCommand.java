package slogo.model.commands.query;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class IsPendownCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // No parameters expected for PENDOWN command

        environment.getActiveTurtles().executeAction(turtle -> {
          int penDown = turtle.isPenDown() ? 1 : 0;

          // Return 1 if pen is down, 0 if it is up
          observerNotifier.notifyInformationUpdateInteger(penDown);
        });

    }
}
