package slogo.view;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;


public interface ModelObserver {
    void onPositionUpdate(int turtleId, double x, double y);
    void onInformationUpdateDouble(double info);
    void onInformationUpdateInteger(int info);
    void onHeadingUpdate(int turtleId, double heading);
    void onPenStateChange(int turtleId, boolean penDown);

    void updateVSP(ObservableMap<String, Double> map);
    void updateHSP(ObservableList<String> list);

    void onError(String errorMessage);
    void onTurtleStateChange(int turtleId, boolean turtleOn);

    void onTurtleAdded(int turtleId, double x, double y);

    void onClearScreen(boolean clearScreen);

}
