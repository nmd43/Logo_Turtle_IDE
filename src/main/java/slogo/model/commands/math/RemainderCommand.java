package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class RemainderCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("Remainder command expects two numeric parameters.");
        }

        double dividend = ((Number) parameters[0]).doubleValue();
        double divisor = ((Number) parameters[1]).doubleValue();

        double remainder = dividend - Math.floor(dividend / divisor) * divisor;

        // Notify the environment about the remainder
        observerNotifier.notifyInformationUpdateDouble(remainder);
    }
}
