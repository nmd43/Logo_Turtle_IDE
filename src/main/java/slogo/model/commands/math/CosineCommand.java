package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class CosineCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("Cosine command expects a numeric parameter.");
        }

        double degrees = ((Number) parameters[0]).doubleValue();

        // Convert degrees to radians since Math.cos() accepts radians
        double radians = Math.toRadians(degrees);

        // Calculate cosine of the angle
        double cosineValue = Math.cos(radians);

        // Notify the environment about the cosine value
        observerNotifier.notifyInformationUpdateDouble(cosineValue);
    }
}
