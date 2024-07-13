package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;
import java.util.Random;


public class RandomCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 1 || !(parameters[0] instanceof Number)) {
            throw new IllegalArgumentException("Random command expects a numeric parameter.");
        }

        double max = ((Number) parameters[0]).doubleValue();

        // Ensure max is positive
        if (max < 0) {
            throw new IllegalArgumentException("Max must be non-negative.");
        }

        // Generate a random non-negative number strictly less than max
        int randomValue = generateRandom((int) max);

        // Notify the environment about the random value
        observerNotifier.notifyInformationUpdateInteger(randomValue);
    }

    private int generateRandom(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
