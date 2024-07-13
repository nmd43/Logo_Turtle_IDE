package slogo.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.controller.ApplicationController;

public class HistoryScrollPane extends CustomScrollPane{

    public HistoryScrollPane(ScrollPane pane, VBox vbox, ApplicationController controller) {
        super(pane, vbox, controller);
    }

}
