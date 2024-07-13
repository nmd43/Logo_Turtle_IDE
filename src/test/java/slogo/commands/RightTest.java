package slogo.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Environment;
import slogo.model.SingleTurtle;
import slogo.model.TurtleInterface;
import slogo.model.commands.CommandInterface;
import slogo.model.commands.movement.RightCommand;
import slogo.model.parsing.ParsedCommand;

import static org.junit.jupiter.api.Assertions.*;

public class RightTest {
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
  void testRightCommand() {
    CommandInterface rightCommand = new RightCommand();
    double ogHeading = turtle.getHeading() + 360;
    double degrees = 90.0;
    double expectedDegrees = -90.0; // Expected position after moving forward
    ParsedCommand parsedCommand = new ParsedCommand(rightCommand, new Object[]{degrees}); // Command to move the turtle forward
    try {
      parsedCommand.getCommand().execute(observerNotifier, environment, parsedCommand.getParameters()); // Execute the command
      double actualDegrees = -1*(ogHeading - turtle.getHeading());
      assertEquals(expectedDegrees, actualDegrees, 0.01, "returned degrees should match change in heading");
    } catch (Exception e) {
      fail("Exception should not be thrown for a valid command execution");
    }
  }

  @Test
  void testMultipleRightCommands() {
    CommandInterface rightCommand1 = new RightCommand();
    CommandInterface rightCommand2 = new RightCommand();
    double ogHeading = turtle.getHeading() + 360;
    double degrees1 = 90.0;
    double degrees2 = 137.0;
    double expectedDegrees1 = -90.0; // Expected position after moving forward
    double expectedDegrees2 = -137.0;
    ParsedCommand parsedCommand1 = new ParsedCommand(rightCommand1, new Object[]{degrees1}); // Command to move the turtle forward
    ParsedCommand parsedCommand2 = new ParsedCommand(rightCommand2, new Object[]{degrees2});
    try {
      parsedCommand1.getCommand().execute(observerNotifier, environment, parsedCommand1.getParameters());
      double nextHeading = turtle.getHeading();
      double actualDegrees1 = -1*(ogHeading - turtle.getHeading());
      parsedCommand2.getCommand().execute(observerNotifier, environment, parsedCommand2.getParameters());
      double actualDegrees2 = -1*(nextHeading - turtle.getHeading());
      assertEquals(expectedDegrees1, actualDegrees1, 0.01, "returned degrees should match change in heading");
      assertEquals(expectedDegrees2, actualDegrees2, 0.01, "returned degrees should match change in heading");
    } catch (Exception e) {
      fail("Exception should not be thrown for a valid command execution");
    }
  }


}
