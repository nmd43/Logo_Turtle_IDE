package slogo.model.commands.bool;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

/**
 * Command to check if two numeric values are not equal.
 */
public class NotEqualCommand implements CommandInterface {

    /**
     * Executes the inequality comparison between two numeric parameters and notifies the environment with the result.
     *
     * @param observerNotifier Notifier for updating observers about changes in the environment.
     * @param environment      The environment containing the state of the turtle and other information.
     * @param parameters       The parameters for the inequality comparison (two numeric values).
     */
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // Check if the correct number of parameters are provided and if they are numeric
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("NotEqual command expects two numeric parameters.");
        }

        // Extract the numeric values from the parameters
        double expr1 = ((Number) parameters[0]).doubleValue();
        double expr2 = ((Number) parameters[1]).doubleValue();

        // Perform the inequality comparison
        int result = (expr1 != expr2) ? 1 : 0;

        // Notify the environment about the result
        observerNotifier.notifyInformationUpdateInteger(result);
    }
}
