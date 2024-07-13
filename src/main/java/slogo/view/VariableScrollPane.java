package slogo.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.controller.ApplicationController;

public class VariableScrollPane extends CustomScrollPane {

    public VariableScrollPane(ScrollPane pane, VBox vbox, ApplicationController controller) {
        super(pane, vbox, controller);
    }


}
