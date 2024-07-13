package slogo.view;


import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;
import slogo.controller.ApplicationController;
public abstract class CustomScrollPane {
    protected ScrollPane myPane;
    protected VBox myVbox;
    private final ApplicationController controller;

    public CustomScrollPane(ScrollPane pane, VBox vbox, ApplicationController controllerrr) {
        this.myPane = pane;
        this.myVbox = vbox;
        this.controller = controllerrr;
    }

    public void updateVariables(ObservableMap<String, Double> myMap) {
        myVbox.getChildren().removeIf(node -> node instanceof TextField);

        // Assuming myMap is an instance variable or passed as a parameter to the update method
        for (ObservableMap.Entry<String, Double> entry : myMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.println(key);
            TextField textField = new TextField(key +" " + String.valueOf(value));
            textField.setEditable(false);
            textField.setOnMouseClicked(e -> doubleClick(e, textField, 1));
            myVbox.getChildren().add(textField);
        }
        myPane.setContent(myVbox);
    }



    public void updateHistory(ObservableList<String> list){
        myVbox.getChildren().removeIf(node -> node instanceof TextField);
        for(String element : list){
            TextField textField = new TextField(element);
            textField.setEditable(false);
            textField.setOnMouseClicked(e -> doubleClick(e, textField, 0));
            myVbox.getChildren().add(textField);
        }

        myPane.setContent(myVbox);
    }


    public ScrollPane getMyPane(){
        return myPane;
    }

    private void doubleClick(MouseEvent e, TextField textField, int temp) {
        if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
            textField.setEditable(!textField.isEditable());
        }

        // Handle Enter key press
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if(temp == 0){
                    try{
                        handleEnterKeyPressed(textField.getText());
                    }
                    catch(Exception c){
                        System.out.println("can't handle this command");
                    }
                }
                else{
                    String Action = "make " + textField.getText();
                    try{
                        handleEnterKeyPressed(Action);
                    }
                    catch(Exception b){
                        System.out.println("can't handle this command");
                    }
                }

            }
        });
    }

    private void handleEnterKeyPressed(String text) throws Exception {
        controller.handleCommandInput(text);
    }
}

