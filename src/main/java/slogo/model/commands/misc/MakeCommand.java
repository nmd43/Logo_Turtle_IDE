package slogo.model.commands.misc;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.commands.CommandInterface;
import slogo.model.infoLists.VariableManager;


public class MakeCommand implements CommandInterface {

    private VariableManager variableManager;

    public MakeCommand() {
    }


    @Override
    public void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters) {
        VariableManager variableManager = VariableManager.getInstance();
        if (parameters.length != 2 || !(parameters[0] instanceof String) || !(parameters[1] instanceof Number)) {
            throw new IllegalArgumentException("MAKE command expects a variable name and a numeric value.");
        }

        String variableName = ((String) parameters[0]).toLowerCase();
        double value = ((Number) parameters[1]).doubleValue();

        // Add or update the variable in the variable manager
        variableManager.setVariable(variableName, value);

        // Return the value of the variable
        observerNotifier.notifyInformationUpdateDouble(value);

        observerNotifier.notifyVariableScrollPane();
    }
}
