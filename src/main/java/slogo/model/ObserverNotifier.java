package slogo.model;

/**
 * Interface for notifying observers about changes in the turtle's state and environment information.
 */
public interface ObserverNotifier {

    /**
     * Notifies observers about a change in the turtle's position.
     * @param turtleId The ID of the turtle.
     * @param x The x-coordinate of the turtle's position.
     * @param y The y-coordinate of the turtle's position.
     */
    void notifyPositionUpdate(int turtleId, double x, double y);

    /**
     * Notifies observers about an update in numeric information.
     * @param info The updated numeric information.
     */
    void notifyInformationUpdateDouble(double info);

    /**
     * Notifies observers about a change in the turtle's heading.
     * @param turtleId The ID of the turtle.
     * @param heading The new heading of the turtle.
     */
    void notifyHeadingUpdate(int turtleId, double heading);

    /**
     * Notifies observers about a change in the pen state (up/down) of the turtle.
     * @param turtleId The ID of the turtle.
     * @param penDown The pen state (true if down, false if up).
     */
    void notifyPenStateChange(int turtleId, boolean penDown);

    /**
     * Notifies observers about a change in the visibility of the turtle.
     * @param turtleId The ID of the turtle.
     * @param turtleOn The visibility state of the turtle (true if visible, false if hidden).
     */
    void notifyTurtleStateChange(int turtleId, boolean turtleOn);

    /**
     * Notifies observers about an update in integer information.
     * @param info The updated integer information.
     */
    void notifyInformationUpdateInteger(int info);


    /**
     * Notifies observers to clear the screen for the specified turtle.
     * @param turtleId The ID of the turtle.
     * @param clearScreen Specifies whether to clear the screen (true for clear, false otherwise).
     */
    void notifyClearScreen(int turtleId, boolean clearScreen);


    void notifyVariableScrollPane();
    void notifyHistoryScrollPane();
    void notifyTurtleAdded(int turtleId, double x, double y);

}
