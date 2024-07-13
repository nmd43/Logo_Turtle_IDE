package slogo.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.misc.MakeCommand;
import slogo.model.infoLists.VariableManager;

import static org.junit.jupiter.api.Assertions.*;

public class MakeCommandTest {
    private MakeCommand makeCommand;
    private VariableManager variableManager;
    private MockObserverNotifier observerNotifier;

    @BeforeEach
    void setUp() {
        variableManager = VariableManager.getInstance();
        makeCommand = new MakeCommand();
        observerNotifier = new MockObserverNotifier();
    }

    @Test
    void testMakeNewVariable() {
        String variableName = "x";
        double value = 10.0;
        try {
            makeCommand.execute(observerNotifier, null, variableName, value);
            assertTrue(variableManager.getVariables().containsKey(variableName),
                    "Variable should be created");
            assertEquals(value, variableManager.getVariableValue(variableName),
                    "Variable value should be set correctly");
            assertEquals(value, observerNotifier.getLastNotifiedDouble(),
                    "Observer should be notified with the correct value");
        } catch (Exception e) {
            fail("Exception should not be thrown for a valid command execution");
        }
    }

    @Test
    void testUpdateExistingVariable() {
        String variableName = "y";
        double initialValue = 5.0;
        double updatedValue = 15.0;
        variableManager.setVariable(variableName, initialValue);
        try {
            makeCommand.execute(observerNotifier, null, variableName, updatedValue);
            assertEquals(updatedValue, variableManager.getVariableValue(variableName),
                    "Existing variable value should be updated");
        } catch (Exception e) {
            fail("Exception should not be thrown for a valid command execution");
        }
    }

    @Test
    void testInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            makeCommand.execute(null, null, "z"); // Missing value parameter
        });
        assertThrows(IllegalArgumentException.class, () -> {
            makeCommand.execute(null, null, "w", "abc"); // Invalid value parameter
        });
    }
}
