package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class DifferenceCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("Difference command expects two numeric parameters.");
        }

        double expr1 = ((Number) parameters[0]).doubleValue();
        double expr2 = ((Number) parameters[1]).doubleValue();

        double difference = expr1 - expr2;

        // Notify the environment about the difference
        observerNotifier.notifyInformationUpdateDouble(difference);
    }
}
