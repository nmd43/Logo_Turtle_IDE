package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class PowerCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("Power command expects two numeric parameters.");
        }

        double base = ((Number) parameters[0]).doubleValue();
        double exponent = ((Number) parameters[1]).doubleValue();

        // Calculate the result of base raised to the power of exponent
        double result = Math.pow(base, exponent);

        // Notify the environment about the result
        observerNotifier.notifyInformationUpdateDouble(result);
    }
}
