package slogo.view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import slogo.controller.ApplicationController;

/**
 * Scene after language selection for SLOGO
 *
 * @author Jonathan Esponda
 */
public class SplashScreen2 implements Scene, SimulationLoader{

  private final SceneSwitcher sceneSwitcher;
  private final String language;
  private final ApplicationController controller;
  private javafx.scene.Scene scene;
  private Stage stage;

  public SplashScreen2(SceneSwitcher sceneSwitcher, String action,
      ApplicationController controller) {
    this.sceneSwitcher = sceneSwitcher;
    this.language = action;
    this.controller = controller;
  }

  /**
   * Initialize second splash screen
   *
   * @param width  width of screen
   * @param height height of screen
   */
  @Override
  public void initScene(int width, int height) {
    VBox parentBox = new VBox(25);
    parentBox.setAlignment(Pos.CENTER);
    initTitleText(parentBox);
    initSplashButtons(parentBox);
    this.scene = new javafx.scene.Scene(parentBox, width, height);
    scene.getStylesheets().add(getClass().getResource("/SplashScreens.css").toExternalForm());
  }

  /**
   * Text of second splash screen are initialized
   *
   * @param parentBox Vertical box storing children nodes
   */
  private void initTitleText(VBox parentBox) {
    Text title = new Text("SLOGO");
    title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
    Text subtitle = new Text("Konur Nordberg, Nikita Daga, Joseph Ogunbadewa, "
        + "Jonathan Esponda");
    subtitle.setStyle("-fx-font-size: 16px;");
    parentBox.getChildren().addAll(title, subtitle);
  }

  /**
   * Buttons of second splash screen are initialized
   *
   * @param parentBox Vertical box storing children nodes
   */
  private void initSplashButtons(VBox parentBox) {
    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER);

    String startString = ResourceBundle.getBundle("slogo.example." + language)
        .getString("start_New_Session");
    Button start = new Button(startString);
    String loadString = ResourceBundle.getBundle("slogo.example." + language)
        .getString("Load");

    start.setOnAction(event -> sceneSwitcher.switchToScene(new
        slogo.view.MainScene(sceneSwitcher, language, controller)));

    Button load = new Button(loadString);

    load.setOnAction(event -> {
      loadSimulation(controller, sceneSwitcher);
    });

    buttonBox.getChildren().addAll(start, load);
    parentBox.getChildren().addAll(buttonBox);
  }

  /**
   * Update scene
   *
   * @param elapsedTime elapsed time for frame rates
   */
  @Override
  public void update(double elapsedTime) {
    // Implementation of update method if needed
  }

  /**
   * Returns current scene
   *
   * @return the Current Scene
   */
  @Override
  public javafx.scene.Scene getScene() {
    return scene;
  }

}
