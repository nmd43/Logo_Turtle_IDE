package slogo.view;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.*;

public class HelpDocumentation {

    public HelpDocumentation(Label label, String fp){
        label.setOnMouseClicked(e->setFilePath(fp));
    }

    private void setFilePath(String filepath){
        File textFile = new File(filepath);
        displayFilePath(textFile);
    }
    private void displayFilePath(File file) {
        if (file.exists()) {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            System.out.println(file.getName());
            webEngine.load(file.toURI().toString());
            showWebView(file.getName(), webView);
        } else {
            System.out.println("Text file not found!");
        }
    }
    private void showWebView(String fileName, WebView webView) {
        Stage stage = new Stage();
        //stage.setTitle("File Viewer: " + fileName);
        stage.setScene(new Scene(webView, 600, 400));
        stage.show();
    }

}
