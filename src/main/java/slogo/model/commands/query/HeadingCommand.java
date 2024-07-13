package slogo.model.commands.query;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class HeadingCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // No parameters expected for Heading command

        environment.getActiveTurtles().executeAction(turtle -> {
          double heading = turtle.getHeading();

          // Return the angle of the turtle's current position
          observerNotifier.notifyInformationUpdateDouble(heading);
        });

    }
}
