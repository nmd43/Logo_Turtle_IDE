package slogo.model;

import slogo.model.infoLists.HistoryManager;
import slogo.model.infoLists.VariableManager;
import slogo.model.parsing.CommandParser;
import slogo.model.parsing.ParsedCommand;

import java.util.List;
import java.util.Map;

/**
 * Implementation of the ModelAPI interface for the Slogo application.
 */
public class SlogoModel implements slogo.model.api.ModelAPI {
    private final CommandParser commandParser;
    private final VariableManager variableManager;
    private final HistoryManager historyManager;
    private final Environment environment;

    /**
     * Constructs a new SlogoModel instance.
     */
    public SlogoModel() {
        // Initialize command parser, variable manager, history manager, and environment
        this.commandParser = new CommandParser();
        this.environment = new Environment();
        this.variableManager =  VariableManager.getInstance();
        this.historyManager =  HistoryManager.getInstance();
    }

    /**
     * Parses the given input command string.
     * @param input The command input string to parse.
     * @return The parsed command.
     * @throws Exception If an error occurs during parsing.
     */
    @Override
    public ParsedCommand parseCommand(String input) throws Exception {
        // Parse the command using the command parser
        ParsedCommand parsedCommand = commandParser.parseCommand(input);
        // Optionally, store the command string in history for UI display or logging
        return parsedCommand;
    }

    /**
     * Retrieves the command history.
     * @return The list of commands in the history.
     */
    @Override
    public List<String> getHistory() {
        return historyManager.getHistory();
    }

    /**
     * Retrieves the map of variables and their values.
     * @return The map of variables.
     */
    @Override
    public Map<String, Double> getVariables() {
        return variableManager.getVariables();
    }

    /**
     * Retrieves the list of user-defined commands.
     * @return The list of user-defined commands.
     */
    @Override
    public List<String> getUserDefinedCommands() {
        // Implementation required
        return null;
    }

    /**
     * Sets the language of the application.
     * @param language The language to set.
     */
    @Override
    public void setLanguage(String language) {
        commandParser.setLanguage(language);
    }

    /**
     * Retrieves the environment instance.
     * @return The environment.
     */
    @Override
    public Environment getEnvironment() {
        return environment;
    }
}
