package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

public class PiCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 0) {
            throw new IllegalArgumentException("Pi command does not expect any parameters.");
        }

        // Get the value of pi
        double piValue = Math.PI;

        // Notify the environment about the value of pi
        observerNotifier.notifyInformationUpdateDouble(piValue);
    }
}
