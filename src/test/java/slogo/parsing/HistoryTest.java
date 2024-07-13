package slogo.parsing;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.ApplicationController;
import slogo.model.api.ModelAPI;
import slogo.model.infoLists.HistoryManager;
import java.util.List;
import slogo.model.parsing.CommandParser;
import slogo.model.parsing.ParsingException;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryTest {
    private HistoryManager historyManager;
    private CommandParser commandParser;
    private ApplicationController controller;
    private ModelAPI model;

    @BeforeEach
    public void setUp() {
        historyManager = HistoryManager.getInstance();
        commandParser = new CommandParser();
        model = new MockModel();
        controller = new ApplicationController(model);
    }

    @Test
    public void testHistoryUpdate() {
        List<String> history = historyManager.getHistory();
        history.clear(); // Clear the history before testing

        // Add commands to the history
        historyManager.addToHistory("forward 50");
        historyManager.addToHistory("right 90");

        // Check if the history list contains the added commands in the correct order
        assertEquals(2, history.size());
        assertEquals("forward 50", history.get(0));
        assertEquals("right 90", history.get(1));
    }

    @Test
    public void testEmptyHistory() {
        List<String> history = historyManager.getHistory();
        history.clear(); // Clear the history before testing

        // Check if history is empty initially
        assertEquals(0, history.size());
    }

    @Test
    public void testValidCommandAddedToHistory() throws ParsingException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> history = historyManager.getHistory();
        history.clear(); // Clear the history before testing
        commandParser.parseCommand("forward 50");
        // Check that the parsed command is added to the history
        assertEquals(1, history.size());
        assertEquals("forward 50", history.get(0));
    }

    @Test
    public void testValidCommandsAddedToHistory() throws ParsingException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> history = historyManager.getHistory();
        history.clear(); // Clear the history before testing
        commandParser.parseCommand("forward 50");
        commandParser.parseCommand("left 90");
        // Check that the parsed command is added to the history
        assertEquals(2, history.size());
        assertEquals("forward 50", history.get(0));
        assertEquals("left 90", history.get(1));
    }

    @Test
    public void testInvalidCommandNotAdded() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> history = historyManager.getHistory();
        history.clear(); // Clear the history before testing
        try {
            // Attempt to parse an invalid command
            commandParser.parseCommand("invalid_command");
        } catch (ParsingException e) {
            // Parsing exception caught, continue with the test
        }
        // Check that the history remains unchanged
        assertEquals(0, history.size());
    }

    @Test
    public void testInvalidAndValidCommandNotAdded() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> history = historyManager.getHistory();
        history.clear(); // Clear the history before testing

        try {
            // Attempt to parse a valid command
            commandParser.parseCommand("forward 50");
        } catch (ParsingException e) {
            // Parsing exception caught, continue with the test
        }
        try {
            // Attempt to parse an invalid command
            commandParser.parseCommand("invalid command");
        } catch (ParsingException e) {
            // Parsing exception caught, continue with the test
        }
        // Check that the history contains only the valid command
        assertEquals(1, history.size());
        assertEquals("forward 50", history.get(0));
    }



    @Test
    public void testCommentNotAdded() throws ParsingException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> history = historyManager.getHistory();
        history.clear(); // Clear the history before testing
        commandParser.parseCommand("# comment");
        commandParser.parseCommand("forward 50");
        // Check that the history remains unchanged
        assertEquals(1, history.size());
        assertEquals("forward 50", history.get(0));
    }
}
