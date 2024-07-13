package slogo.model.commands.bool;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

/**
 * Command to negate a numeric value.
 */
public class NotCommand implements CommandInterface {

    /**
     * Executes the negation of a numeric parameter and notifies the environment with the result.
     *
     * @param observerNotifier Notifier for updating observers about changes in the environment.
     * @param environment      The environment containing the state of the turtle and other information.
     * @param parameters       The parameter for the negation (a numeric value).
     */
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        // Check if the correct number of parameters is provided and if it is numeric
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("Not command expects a numeric parameter.");
        }

        // Extract the numeric value from the parameter
        double test = ((Number) parameters[0]).doubleValue();

        // Perform the negation
        int result = (test == 0) ? 1 : 0;

        // Notify the environment about the result
        observerNotifier.notifyInformationUpdateInteger(result);
    }
}
