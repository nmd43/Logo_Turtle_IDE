package slogo.model.commands;

import slogo.model.Environment;
import slogo.model.ObserverNotifier;

public interface CommandInterface {
  /**
   * Executes the command with the given parameters in the specified environment.
   *
   * @param observerNotifier The observerNotifier in which the command is executed.
   * @param parameters The parameters required for executing the command.
   */
  void execute(ObserverNotifier observerNotifier, Environment environment, Object... parameters);
}
