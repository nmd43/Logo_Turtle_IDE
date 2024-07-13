package slogo.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Environment;
import slogo.model.commands.CommandInterface;
import slogo.model.commands.math.RandomRangeCommand;
import slogo.model.parsing.ParsedCommand;

import static org.junit.jupiter.api.Assertions.*;

public class RandomRangeCommandTest {
    private Environment environment;
    private MockObserverNotifier observerNotifier;

    @BeforeEach
    void setUp() {
        environment = new Environment();
        observerNotifier = new MockObserverNotifier(); // Initialize observerNotifier
    }

    @Test
    void testRandomNumberInRange() {
        double min = 5;
        double max = 10;
        CommandInterface randomRangeCommand = new RandomRangeCommand();
        ParsedCommand parsedCommand = new ParsedCommand(randomRangeCommand, new Object[]{min, max});
        try {
            //parsedCommand.getCommand().execute(null, null, parsedCommand.getParameters());
            parsedCommand.getCommand().execute(observerNotifier, environment, parsedCommand.getParameters());
            int randomNumber = observerNotifier.getLastNotifiedInteger();
            assertTrue(randomNumber >= min && randomNumber <= max, "Generated random number should be within the specified range");
        } catch (Exception e) {
            fail("Exception should not be thrown for a valid command execution");
        }
    }

    @Test
    void testRandomNumberWithEqualMinMax() {
        double min = 5;
        double max = 5;
        CommandInterface randomRangeCommand = new RandomRangeCommand();
        ParsedCommand parsedCommand = new ParsedCommand(randomRangeCommand, new Object[]{min, max});
        try {
            parsedCommand.getCommand().execute(observerNotifier, environment, parsedCommand.getParameters());
            int randomNumber = observerNotifier.getLastNotifiedInteger();
            assertEquals(min, randomNumber, "Generated random number should be equal to min when min and max are equal");
        } catch (Exception e) {
            fail("Exception should not be thrown for a valid command execution");
        }
    }
}
