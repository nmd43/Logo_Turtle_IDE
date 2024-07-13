package slogo.parsing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.parsing.CommandParser;
import slogo.model.parsing.ParsedCommand;
import slogo.model.parsing.ParsingException;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {
  private CommandParser parser;

  @BeforeEach
  void setUp() {
    parser = new CommandParser();
    parser.setLanguage("English"); // Ensure tests run with a known language setting
  }

 //NOTE: Test for a command with incorrect number of parameters is not included here because the
  //commands check for it and will throw errors if incorrect number of parameters

  @Test
  void testParseUnknownCommand() {
    String input = "unknownCommand";
    assertThrows(ParsingException.class, () -> parser.parseCommand(input),
        "Parsing should fail for an unknown command.");
  }

  @Test
  void testParseCommandWithAlias() {
    // Setup to include a command with alias 'fd' equivalent to 'forward'
    String input = "fd 50";
    assertDoesNotThrow(() -> {
      ParsedCommand command = parser.parseCommand(input);
      assertNotNull(command, "The command should not be null for a known alias.");
    });
  }

  @Test
  void testParseEmptyInput() {
    String input = "";
    assertNull(assertDoesNotThrow(() -> parser.parseCommand(input)),
        "Empty input should result in null parsed command.");
  }

  @Test
  void testParseCommandIgnoringCase() {
    // Assuming command parsing is case-insensitive and "left" is a known command
    String input = "LeFt 90";
    assertDoesNotThrow(() -> {
      ParsedCommand command = parser.parseCommand(input);
      assertNotNull(command, "The command should not be null for a case-insensitive match.");
    });
  }

  @Test
  void testParseCommandWithParameters() {
    // Assuming "forward" is a known command that takes one parameter
    String input = "forward 100";
    assertDoesNotThrow(() -> {
      ParsedCommand command = parser.parseCommand(input);
      assertNotNull(command, "Command should not be null when parsed with correct parameters.");
      assertEquals(1, command.getParameters().length, "Forward command should have exactly one parameter.");
    });
  }

  @Test
  void testParseCommandWithIncorrectNumberOfParameters() {
    // Assuming "left" command expects only one parameter
    String input = "left 90 extraParam";
    assertThrows(ParsingException.class, () -> parser.parseCommand(input),
        "Parsing should fail due to incorrect number of parameters.");
  }

  @Test
  void testParseCommandWithSpacesOnly() {
    String input = "   ";
    assertNull(assertDoesNotThrow(() -> parser.parseCommand(input)),
        "Input with spaces only should result in null parsed command.");
  }
}
