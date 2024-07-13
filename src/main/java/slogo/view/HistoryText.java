package slogo.view;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 * Class that allows for the history to be saved within the MainScene and
 * updated by the controller.
 *
 * @author Jonathan Esponda
 */
public class HistoryText {

  private List<String> historyText;

  /**
   * Creates a new list based on the updated history.
   *
   * @param list updated history list provided by the model
   */
  public void updateHistorySave(ObservableList<String> list) {
    historyText = new ArrayList<>();
    historyText.addAll(list);
  }

  /**
   * Returns the list.
   */
  public List<String> getHistory() {
    return historyText;
  }
}
