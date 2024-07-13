package slogo.model.parsing;

import slogo.model.commands.CommandInterface;

public class ParsedCommand {
    private final CommandInterface command;
    private final Object[] parameters;

    public ParsedCommand(CommandInterface command, Object[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    // Getters
    public CommandInterface getCommand() {
        return command;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
