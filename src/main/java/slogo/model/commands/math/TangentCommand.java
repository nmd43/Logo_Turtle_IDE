package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class TangentCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("Tan command expects a numeric parameter.");
        }

        double degrees = ((Number) parameters[0]).doubleValue();

        // Convert degrees to radians since Math.tan() accepts radians
        double radians = Math.toRadians(degrees);

        // Calculate tangent of the angle
        double tangentValue = Math.tan(radians);

        // Notify the environment about the tangent value
        observerNotifier.notifyInformationUpdateDouble(tangentValue);
    }
}
