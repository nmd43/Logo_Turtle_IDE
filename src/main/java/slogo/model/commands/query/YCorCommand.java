package slogo.model.commands.query;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.SingleTurtle;
import slogo.model.commands.CommandInterface;

public class YCorCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // No parameters expected for YCor command

       environment.getActiveTurtles().executeAction(turtle -> {
         double yCoordinate = turtle.getPosition().getY();

         // Return the y-coordinate of the turtle's current position
         observerNotifier.notifyInformationUpdateDouble(yCoordinate);
       });

    }
}
