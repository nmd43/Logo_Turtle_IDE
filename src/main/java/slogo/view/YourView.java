package slogo.view;


import javafx.application.Platform;
import java.lang.reflect.Field;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import slogo.controller.ApplicationController;

public class YourView implements ModelObserver{
    private final ApplicationController controller;
    private TurtleImage turtle;
    private VariableScrollPane varPane;
    private HistoryScrollPane histPane;
    private VariablePane console;
    private HistoryText saveHistory;
    private Pane turtleArea;

    public YourView(ApplicationController controller, TurtleImage turtleImage,
        VariablePane consolePane, VariableScrollPane variablePane, HistoryScrollPane historyPane
    , HistoryText history, Pane turtlePane) {
        this.controller = controller;
        // Register as observer
        controller.registerObserver(this);
        turtle = turtleImage;
        varPane = variablePane;
        histPane = historyPane;
        console = consolePane;
        saveHistory = history;
        turtleArea = turtlePane;
    }

    @Override
    public void onPositionUpdate(int turtleID, double x, double y) {
        // Update view with new position
        MainScene.updateTurtlePosition(turtleID, x, y);

    }

    @Override
    public void onInformationUpdateDouble(double info) {
        console.updateDouble(info);
    }

    @Override
    public void onInformationUpdateInteger(int info) {
        // Print this on the screen
        console.updateDouble(info);
    }


    @Override
    public void onHeadingUpdate(int turtleId, double heading) {
        MainScene.updateTurtleHeading(turtleId, heading);
    }


    @Override
    public void onPenStateChange(int turtleId, boolean penDown) {
        try {
            Field field = turtle.getClass().getDeclaredField("penDown");
            field.setAccessible(true);
            field.setBoolean(turtle, penDown);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            onError("No field penDown");
        }
    }

    @Override
    public void onError(String errorMessage) {
        // Show the error message in a pop-up dialog
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @Override
    public void onTurtleStateChange(int turtleId, boolean turtleOn) {
        turtle.updateVisibility(0, turtleOn);
    }

    @Override
    public void onClearScreen(boolean clearScreen) {
        if (clearScreen) {
            // Iterate over all children of turtleArea
            for (Node node : turtleArea.getChildren()) {
                // Set visibility of each node to false
                node.setVisible(false);
            }
            // Set visibility of turtle to true
            turtle.updateVisibility(0, true);
            turtle.setHeading(0, 90);
        }
    }

    @Override
    public void updateVSP(ObservableMap<String, Double> map) {
        varPane.updateVariables(map);
    }
    @Override
    public void updateHSP(ObservableList<String> list){
        histPane.updateHistory(list);
        saveHistory.updateHistorySave(list);
    }

    @Override
    public void onTurtleAdded(int turtleId, double x, double y) {
        Platform.runLater(() -> MainScene.addNewTurtleImageToView(turtleId, x, y));
    }
}
