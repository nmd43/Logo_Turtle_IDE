package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class SineCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("Sine command expects a numeric parameter.");
        }

        double degrees = ((Number) parameters[0]).doubleValue();

        // Convert degrees to radians since Math.sin() accepts radians
        double radians = Math.toRadians(degrees);

        // Calculate sine of the angle
        double sineValue = Math.sin(radians);

        // Notify the environment about the sine value
        observerNotifier.notifyInformationUpdateDouble(sineValue);
    }
}
