package slogo.commands;

import slogo.model.ObserverNotifier;

public class MockObserverNotifier implements ObserverNotifier {
    private double lastNotifiedDouble;
    private int lastNotifiedInteger;

    @Override
    public void notifyPositionUpdate(int turtleId, double x, double y) {
        // Mock implementation for position update notification
    }

    @Override
    public void notifyInformationUpdateDouble(double info) {
        lastNotifiedDouble = info;
    }

    @Override
    public void notifyHeadingUpdate(int turtleId, double heading) {
        // Mock implementation for heading update notification
    }

    @Override
    public void notifyPenStateChange(int turtleId, boolean penDown) {
        // Mock implementation for pen state change notification
    }

    @Override
    public void notifyTurtleStateChange(int turtleId, boolean turtleOn) {

    }

    @Override
    public void notifyInformationUpdateInteger(int info) {
        lastNotifiedInteger = info;
    }

    @Override
    public void notifyClearScreen(int turtleId, boolean clearScreen) {

    }

    @Override
    public void notifyVariableScrollPane() {

    }

    @Override
    public void notifyHistoryScrollPane() {

    }

    @Override
    public void notifyTurtleAdded(int turtleId, double x, double y) {

    }

    public double getLastNotifiedDouble() {
        return lastNotifiedDouble;
    }

    public int getLastNotifiedInteger() {
        return lastNotifiedInteger;
    }
}
