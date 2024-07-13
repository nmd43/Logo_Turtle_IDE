package slogo.controller;


import java.awt.geom.Point2D;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import slogo.model.Environment;
import slogo.model.ObserverNotifier;
import slogo.model.TurtleInterface;
import slogo.model.api.ModelAPI;
import slogo.model.parsing.ParsedCommand;
import slogo.view.ModelObserver;
import slogo.model.commands.CommandInterface;
import slogo.model.parsing.ParsingException;


import java.util.Map;
import java.util.List;

public class ApplicationController implements ObserverNotifier {
    private final ModelAPI model;
    private ModelObserver observer;
    private final Environment environment;
    private String currentLanguage;


    public ApplicationController(ModelAPI model) {
        this.model = model;
        this.environment = new Environment(); // Create a new environment
        environment.addTurtle();
        environment.setActiveTurtle(1);
    }

    public void setCurrentLanguage(String language) {
        this.currentLanguage = language;
        model.setLanguage(language);
    }

    public void registerObserver(ModelObserver observer) {
        this.observer = observer;
    }

    public void handleCommandInput(String commandInput) throws Exception {
        try {
            ParsedCommand parsedCommand = model.parseCommand(commandInput);
            if (parsedCommand == null) {
                return; // Ignore if the parsed command is null
            }
            CommandInterface command = parsedCommand.getCommand();
            Object[] parameters = parsedCommand.getParameters();
            command.execute(this, environment, parameters);
        }
        catch (ParsingException e){
            observer.onError(e.getMessage());
        }
    }


    public void addNewTurtle() {
        TurtleInterface newTurtle = environment.addTurtleAndGet();

        // Generate random positions within the specified range
        double randomX = -200 + (400 * Math.random());
        double randomY = -200 + (400 * Math.random());

        // Update the position of the newly added turtle
        newTurtle.setPosition(new Point2D.Double(randomX, randomY));

        // Notify view to update with the new turtle's ID and position
        notifyTurtleAdded(newTurtle.getId(), randomX, randomY);
        System.out.println("We are adding a new turtle with ID: " + newTurtle.getId());
    }

//    public ObservableMap<String, Double> getAllVariables() {
//        Map<String, Double> variableList = model.getVariables();
//        return FXCollections.observableMap(variableList);
//    }
//
//    public ObservableList<String> getCurrentHistory() {
//
//        return FXCollections.observableList(historyList);
//    }


    @Override
    public void notifyPositionUpdate(int turtleId, double x, double y) {
        observer.onPositionUpdate(turtleId, x, y);
    }

    @Override
    public void notifyInformationUpdateDouble(double info) {
        observer.onInformationUpdateDouble(info);
    }

    @Override
    public void notifyHeadingUpdate(int turtleID, double heading) {
        observer.onHeadingUpdate(turtleID, heading);
    }

    @Override
    public void notifyPenStateChange(int turtleId, boolean penDown) {
        observer.onPenStateChange(turtleId, penDown);
    }

    @Override
    public void notifyTurtleStateChange(int turtleId, boolean turtleOn) {
        observer.onTurtleStateChange(turtleId, turtleOn);
    }

    @Override
    public void notifyInformationUpdateInteger(int info) {
        observer.onInformationUpdateInteger(info);
    }

    @Override
    public void notifyClearScreen(int turtleId, boolean clearScreen) {
        observer.onClearScreen(clearScreen);
    }

    @Override
    public void notifyVariableScrollPane() {
        Map<String, Double> temp = model.getVariables();
        ObservableMap<String, Double> variableList = FXCollections.observableMap(temp);
        observer.updateVSP(variableList);
    }

    @Override
    public void notifyHistoryScrollPane() {
        List<String> temp = model.getHistory();
        System.out.println("List: ");
        for (String command: temp){
            System.out.println(command);
        }
        ObservableList<String> historyList = FXCollections.observableList(temp);
        System.out.println("ObservableList: ");
        for (String command: historyList){
            System.out.println(command);
        }
        observer.updateHSP(historyList);
    }


    @Override
    public void notifyTurtleAdded(int turtleId, double x, double y) {
        // Logic to notify the view about the new turtle addition
        // This could involve calling a method on the observer (ModelObserver) instance
        if (observer != null) {
            observer.onTurtleAdded(turtleId, x, y);
        }
    }


    public void givePenColor(int color_number){

    }

}

