package slogo.model.commands.bool;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class AndCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("And command expects two numeric parameters.");
        }

        double test1 = ((Number) parameters[0]).doubleValue();
        double test2 = ((Number) parameters[1]).doubleValue();

        int result = (test1 != 0 && test2 != 0) ? 1 : 0;

        // Notify the environment about the result
        observerNotifier.notifyInformationUpdateInteger(result);
    }
}
