package slogo.parsing;

import slogo.model.Environment;
import slogo.model.api.ModelAPI;
import slogo.model.parsing.ParsedCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockModel implements ModelAPI {
    @Override
    public ParsedCommand parseCommand(String input) throws Exception {
        return null;
    }

    @Override
    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        history.add("forward 50");
        history.add("right 90");
        return history;
    }

    @Override
    public Map<String, Double> getVariables() {
        return null;
    }

    @Override
    public List<String> getUserDefinedCommands() {
        return null;
    }

    @Override
    public void setLanguage(String language) {

    }

    @Override
    public Environment getEnvironment() {
        return null;
    }
}
