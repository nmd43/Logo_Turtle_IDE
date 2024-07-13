package slogo.model.commands.math;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;

import java.util.Random;

public class RandomRangeCommand implements CommandInterface {
    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        if (parameters.length != 2 || !(parameters[0] instanceof Number) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("RandomRange command expects two numeric parameters.");
        }

        double min = ((Number) parameters[0]).doubleValue();
        double max = ((Number) parameters[1]).doubleValue();

        // Ensure min <= max
        if (min > max) {
            throw new IllegalArgumentException("Min must be less than or equal to max.");
        }

        // Generate a random number within the range [min, max]
        int randomValue = generateRandom((int) min, (int) max);

        // Notify the environment about the random value
        observerNotifier.notifyInformationUpdateInteger(randomValue);
    }

    private int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
