package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class MinusCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("Negate command expects a numeric parameter.");
        }

        double expr = ((Number) parameters[0]).doubleValue();

        double negatedValue = -expr;

        // Notify the environment about the negated value
        observerNotifier.notifyInformationUpdateDouble(negatedValue);
    }
}
