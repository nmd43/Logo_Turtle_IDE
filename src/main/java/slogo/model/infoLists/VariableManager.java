package slogo.model.infoLists;

import java.util.HashMap;
import java.util.Map;

public class VariableManager {
    private final Map<String, Double> variableMap;
    private static VariableManager instance;

    public VariableManager() {
        variableMap = new HashMap<>();

    }

    public void setVariable(String name, double value) {
        variableMap.put(name, value);
    }

    public double getVariableValue(String name) {
        Double value = variableMap.get(name);
        return value != null ? value : 0.0; // Return 0.0 if variable not found
    }

    public static synchronized VariableManager getInstance() {
        if (instance == null) {
            instance = new VariableManager();
        }
        return instance;
    }

    public Map<String, Double> getVariables() {
        return variableMap;
    }
}

