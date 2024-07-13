package slogo.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Environment;
import slogo.model.TurtleComposite;
import slogo.model.TurtleInterface;
import slogo.model.commands.CommandInterface;
import slogo.model.commands.movement.ForwardCommand;
import slogo.model.commands.movement.LeftCommand;
import slogo.model.parsing.ParsedCommand;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

public class ForwardTest {
  private Environment environment;
  private TurtleInterface activeTurtle;
  private MockObserverNotifier observerNotifier;

  @BeforeEach
  void setUp() {
    environment = new Environment();
    environment.addTurtle(); // Add initial turtle to the environment
    // Assume the first turtle added is the active one
    activeTurtle = environment.getTurtle(1); // Assuming IDs start at 1
    observerNotifier = new MockObserverNotifier();
  }

  @Test
  void testForwardCommand() {
    CommandInterface forwardCommand = new ForwardCommand();
    double distance = 50.0;
    Point2D.Double expectedPosition = new Point2D.Double(50.0, 0.0);
    ParsedCommand parsedCommand = new ParsedCommand(forwardCommand, new Object[]{distance});

    try {
      parsedCommand.getCommand().execute(observerNotifier, environment, parsedCommand.getParameters());
      Point2D.Double actualPosition = activeTurtle.getPosition();
      assertEquals(expectedPosition.getX(), actualPosition.getX(), 0.01);
      assertEquals(expectedPosition.getY(), actualPosition.getY(), 0.01);
    } catch (Exception e) {
      fail("Should not throw exception");
    }
  }

  @Test
  void testMultipleForwardCommands() {
    CommandInterface forwardCommand1 = new ForwardCommand();
    CommandInterface forwardCommand2 = new ForwardCommand();
    double distance1 = 25.0;
    double distance2 = 45.0;
    Point2D.Double expectedPosition = new Point2D.Double(70.0, 0.0); // Expected position after moving forward
    ParsedCommand parsedCommand1 = new ParsedCommand(forwardCommand1, new Object[]{distance1}); // Command to move the turtle forward
    ParsedCommand parsedCommand2 = new ParsedCommand(forwardCommand2, new Object[]{distance2});
    try {
      parsedCommand1.getCommand().execute(observerNotifier, environment, parsedCommand1.getParameters()); // Execute the command
      parsedCommand2.getCommand().execute(observerNotifier, environment, parsedCommand2.getParameters());
      Point2D.Double actualPosition = activeTurtle.getPosition();
      assertEquals(expectedPosition.getX(), actualPosition.getX(), 0.01, "X coordinate should match expected position");
      assertEquals(expectedPosition.getY(), actualPosition.getY(), 0.01, "Y coordinate should match expected position");
    } catch (Exception e) {
      fail("Exception should not be thrown for a valid command execution");
    }
  }

  @Test
  void testForwardCommandYDirection() {
    CommandInterface forwardCommand = new ForwardCommand();
    CommandInterface leftCommand = new LeftCommand();
    double distance = 50.0;
    double degrees = 90.0;
    Point2D.Double expectedPosition = new Point2D.Double(0.0, 50.0); // Expected position after moving forward
    ParsedCommand parsedCommandForward = new ParsedCommand(forwardCommand, new Object[]{distance}); // Command to move the turtle forward
    ParsedCommand parsedCommandLeft = new ParsedCommand(leftCommand, new Object[]{degrees});
    try {
      parsedCommandLeft.getCommand().execute(observerNotifier, environment, parsedCommandLeft.getParameters());
      parsedCommandForward.getCommand().execute(observerNotifier, environment, parsedCommandForward.getParameters()); // Execute the command
      Point2D.Double actualPosition = activeTurtle.getPosition();
      assertEquals(expectedPosition.getX(), actualPosition.getX(), 0.01, "X coordinate should match expected position");
      assertEquals(expectedPosition.getY(), actualPosition.getY(), 0.01, "Y coordinate should match expected position");
    } catch (Exception e) {
      fail("Exception should not be thrown for a valid command execution");
    }
  }
}
