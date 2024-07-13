package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class ArctangentCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("ArcTan command expects a numeric parameter.");
        }

        double value = ((Number) parameters[0]).doubleValue();

        // Calculate arctangent of the value
        double arctanValue = Math.atan(value);

        // Convert arctangent to degrees
        double degrees = Math.toDegrees(arctanValue);

        // Notify the environment about the arctangent value in degrees
        observerNotifier.notifyInformationUpdateDouble(degrees);
    }
}
