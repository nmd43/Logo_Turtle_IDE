package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class LogCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("NaturalLog command expects a numeric parameter.");
        }

        double number = ((Number) parameters[0]).doubleValue();

        // Check if the number is valid for logarithm
        if (number <= 0) {
            throw new IllegalArgumentException("Natural logarithm is undefined for non-positive numbers.");
        }

        // Calculate natural logarithm of the number
        double naturalLogValue = Math.log(number);

        // Notify the environment about the natural logarithm value
        observerNotifier.notifyInformationUpdateDouble(naturalLogValue);
    }
}
