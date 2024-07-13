package slogo.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Environment;
import slogo.model.SingleTurtle;
import slogo.model.TurtleInterface;
import slogo.model.commands.CommandInterface;
import slogo.model.commands.movement.LeftCommand;
import slogo.model.parsing.ParsedCommand;

import static org.junit.jupiter.api.Assertions.*;

public class LeftTest {
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
  void testLeftCommand() {
    CommandInterface leftCommand = new LeftCommand();
    double ogHeading = turtle.getHeading();
    double degrees = 90.0;
    double expectedDegrees = 90.0; // Expected position after moving forward
    ParsedCommand parsedCommand = new ParsedCommand(leftCommand, new Object[]{degrees}); // Command to move the turtle forward
    try {
      parsedCommand.getCommand().execute(observerNotifier, environment, parsedCommand.getParameters()); // Execute the command
      double actualDegrees = turtle.getHeading() - ogHeading;
      assertEquals(expectedDegrees, actualDegrees, 0.01, "returned degrees should match change in heading");
    } catch (Exception e) {
      fail("Exception should not be thrown for a valid command execution");
    }
  }

  @Test
  void testMultipleLeftCommands() {
    CommandInterface leftCommand1 = new LeftCommand();
    CommandInterface leftCommand2 = new LeftCommand();
    double ogHeading = turtle.getHeading();
    double degrees1 = 90.0;
    double degrees2 = 137.0;
    double expectedDegrees1 = 90.0; // Expected position after moving forward
    double expectedDegrees2 = 137.0;
    ParsedCommand parsedCommand1 = new ParsedCommand(leftCommand1, new Object[]{degrees1}); // Command to move the turtle forward
    ParsedCommand parsedCommand2 = new ParsedCommand(leftCommand2, new Object[]{degrees2});
    try {
      parsedCommand1.getCommand().execute(observerNotifier, environment, parsedCommand1.getParameters());
      double nextHeading = turtle.getHeading();
      double actualDegrees1 = turtle.getHeading() - ogHeading;
      parsedCommand2.getCommand().execute(observerNotifier, environment, parsedCommand2.getParameters());
      double actualDegrees2 = turtle.getHeading() - nextHeading;
      assertEquals(expectedDegrees1, actualDegrees1, 0.01, "returned degrees should match change in heading");
      assertEquals(expectedDegrees2, actualDegrees2, 0.01, "returned degrees should match change in heading");
    } catch (Exception e) {
      fail("Exception should not be thrown for a valid command execution");
    }
  }


}
