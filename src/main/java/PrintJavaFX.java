import javafx.application.Application;
import javafx.stage.Stage;

public class PrintJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println("\n\n\n\n");
        System.out.println("JavaFX Version:");
        System.out.println(System.getProperty("javafx.runtime.version"));
    }
}

