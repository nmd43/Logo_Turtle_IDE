package slogo.model.commands.bool;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

/**
 * Command to check if the first numeric parameter is less than or equal to the second numeric parameter.
 */
public class LessEqualCommand implements CommandInterface {

    /**
     * Executes the less than or equal comparison of two numeric parameters and notifies the environment with the result.
     *
     * @param observerNotifier Notifier for updating observers about changes in the environment.
     * @param environment      The environment containing the state of the turtle and other information.
     * @param parameters       The two numeric parameters for comparison.
     */
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // Check if the correct number of parameters is provided and if they are numeric
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("LessThanOrEqual command expects two numeric parameters.");
        }

        // Extract the numeric values from the parameters
        double expr1 = ((Number) parameters[0]).doubleValue();
        double expr2 = ((Number) parameters[1]).doubleValue();

        // Perform the less than or equal comparison
        int result = (expr1 <= expr2) ? 1 : 0;

        // Notify the environment about the result
        observerNotifier.notifyInformationUpdateInteger(result);
    }
}
