package slogo.model.infoLists;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private final List<String> history;
    private static HistoryManager instance;

    public HistoryManager() {
        history = new ArrayList<>();
    }

    public static synchronized HistoryManager getInstance() {
        if (instance == null) {
            instance = new HistoryManager();
        }
        return instance;
    }

    public List<String> getHistory() {
        return history;
    }

    public void addToHistory(String command) {
        history.add(command);
    }
}
