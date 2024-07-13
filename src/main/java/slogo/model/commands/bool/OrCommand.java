package slogo.model.commands.bool;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

/**
 * Command to perform logical OR operation on two numeric parameters.
 */
public class OrCommand implements CommandInterface {

    /**
     * Executes the logical OR operation on two numeric parameters and notifies the environment with the result.
     *
     * @param observerNotifier Notifier for updating observers about changes in the environment.
     * @param environment      The environment containing the state of the turtle and other information.
     * @param parameters       The two numeric parameters to perform the OR operation.
     */
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // Check if the correct number of parameters is provided and if they are numeric
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("Or command expects two numeric parameters.");
        }

        // Extract the numeric values from the parameters
        double test1 = ((Number) parameters[0]).doubleValue();
        double test2 = ((Number) parameters[1]).doubleValue();

        // Perform the logical OR operation
        int result = (test1 != 0 || test2 != 0) ? 1 : 0;

        // Notify the environment about the result
        observerNotifier.notifyInformationUpdateInteger(result);
    }
}
