package slogo.model.api;

import slogo.model.Environment;
import slogo.model.parsing.ParsedCommand;

import java.util.List;
import java.util.Map;


public interface ModelAPI {

    ParsedCommand parseCommand(String input) throws Exception;
    List<String> getHistory();
    Map<String, Double> getVariables();
    List<String> getUserDefinedCommands();
    void setLanguage(String language);
    Environment getEnvironment();
}

