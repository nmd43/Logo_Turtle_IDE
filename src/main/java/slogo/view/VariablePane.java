package slogo.view;

import javafx.scene.control.TextField;

public class VariablePane {
    private final TextField myText;


    public VariablePane(TextField text) {
        this.myText = text;
    }

    public void updateDouble(double newValue) {
        myText.clear();
        myText.setText("Value : " + newValue);
    }

    public void updateInt(int newValue) {
        myText.clear();
        myText.setText("Value : " + newValue);
    }
    public TextField getPane(){
        return this.myText;
    }
}
