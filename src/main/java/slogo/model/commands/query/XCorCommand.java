package slogo.model.commands.query;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class XCorCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // No parameters expected for XCor command

        environment.getActiveTurtles().executeAction(turtle -> {
          double xCoordinate = turtle.getPosition().getX();

          // Return the x-coordinate of the turtle's current position
          observerNotifier.notifyInformationUpdateDouble(xCoordinate);
        });

    }
}
