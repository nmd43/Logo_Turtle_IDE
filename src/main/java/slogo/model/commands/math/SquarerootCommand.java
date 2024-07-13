package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class SquarerootCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("SquareRoot command expects a numeric parameter.");
        }

        double number = ((Number) parameters[0]).doubleValue();

        // Check if the number is non-negative
        if (number < 0) {
            throw new IllegalArgumentException("Square root is undefined for negative numbers.");
        }

        // Calculate square root of the number
        double squareRootValue = Math.sqrt(number);

        // Notify the environment about the square root value
        observerNotifier.notifyInformationUpdateDouble(squareRootValue);
    }
}
