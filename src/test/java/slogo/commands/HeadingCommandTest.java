package slogo.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Environment;
import slogo.model.SingleTurtle;
import slogo.model.TurtleInterface;
import slogo.model.commands.CommandInterface;
import slogo.model.commands.movement.LeftCommand;
import slogo.model.commands.query.HeadingCommand;
import slogo.model.parsing.ParsedCommand;

import static org.junit.jupiter.api.Assertions.*;

public class HeadingCommandTest {
    private Environment environment;
    private TurtleInterface turtle;
    private MockObserverNotifier observerNotifier;

    @BeforeEach
    void setUp() {
        environment = new Environment();
        environment.addTurtle(); // Add a turtle to the environment

        turtle = environment.getTurtle(1); // Retrieve the active turtle
        observerNotifier = new MockObserverNotifier(); // Initialize observerNotifier
    }


    @Test
    void testHeadingAtStart() {
        CommandInterface headingCommand = new HeadingCommand(); // Create a new instance of HeadingCommand
        ParsedCommand parsedCommand = new ParsedCommand(headingCommand, new Object[0]); // Create a ParsedCommand with no parameters
        try {
            parsedCommand.getCommand().execute(new MockObserverNotifier(), environment, parsedCommand.getParameters()); // Execute the command
            assertEquals(0.0, environment.getTurtle(turtle.getId()).getHeading(), 0.01, "Heading should be 0.0 at the start"); // Check if the heading of the active turtle is 0.0
        } catch (Exception e) {
            fail("Exception should not be thrown for a valid command execution");
        }
    }


    @Test
    void testHeadingAfterTurn() {
        CommandInterface leftCommand = new LeftCommand();
        ParsedCommand parsedLeftCommand = new ParsedCommand(leftCommand, new Object[]{90.0}); // Turn 90 degrees to the left
        ParsedCommand parsedHeadingCommand = new ParsedCommand(new HeadingCommand(), new Object[0]);
        try {
            parsedLeftCommand.getCommand().execute(observerNotifier, environment, parsedLeftCommand.getParameters()); // Execute the LeftCommand
            parsedHeadingCommand.getCommand().execute(observerNotifier, environment, parsedHeadingCommand.getParameters()); // Get the heading after turning

            // Retrieve the updated heading from the Turtle object
            double heading = environment.getTurtle(turtle.getId()).getHeading();

            assertEquals(90.0, heading, "Heading should be 270.0 after turning 90 degrees to the left");
        } catch (Exception e) {
            fail("Exception should not be thrown for a valid command execution");
        }
    }
}
