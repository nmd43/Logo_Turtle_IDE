package slogo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.controller.ApplicationController;
import slogo.model.api.ModelAPI;
import slogo.model.SlogoModel;
import slogo.view.Scene;
import slogo.view.SceneSwitcher;
import slogo.view.SplashScreen;

/**
 * Main class that runs the SlOGO program
 */
public class Main extends Application implements SceneSwitcher {

  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  private static final int LANGUAGE_UPDATE_INTERVAL = 3;
  private final int framesPerSecond = 60;
  private final double secondsDelay = 1.0 / framesPerSecond;
  private Scene currentScene;
  private Stage stage;
  private ApplicationController controller;

  /**
   * Start function for JavaFX program
   *
   * @param stage: current stage
   */
  @Override
  public void start(Stage stage) {
    ModelAPI model = new SlogoModel(); // Create an instance of the model
    controller = new ApplicationController(model);
    this.stage = stage;
    initGameLoop();
    switchToScene(new SplashScreen(this, controller));
    stage.setTitle("SlOGO Team 7");
    stage.setResizable(false);
    stage.show();
  }

  /**
   * Initialize Timeline game loop
   */
  private void initGameLoop() {
    Timeline gameLoop = new Timeline();
    gameLoop.setCycleCount(Timeline.INDEFINITE);
    gameLoop.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(secondsDelay), e -> step(secondsDelay)));
    gameLoop.getKeyFrames()
        .add(new KeyFrame(Duration.seconds(LANGUAGE_UPDATE_INTERVAL),
            e -> SplashScreen.updateLanguage()));

    gameLoop.play();
  }

  /**
   * Step function that runs in JavaFX, continuously
   *
   * @param elapsedTime: elapsed time for frame rates
   */
  private void step(double elapsedTime) {
    if (currentScene != null) {
      currentScene.update(elapsedTime);
    }
  }

  /**
   * Runs the program
   */
  public static void main(String[] args) {
    System.getProperty("javafx.runtime.version");
    launch(args);
  }

  /**
   * Switch from current scene to a new Scene
   *
   * @param scene: new AbstractScene that will now be displayed
   */
  @Override
  public void switchToScene(Scene scene) {
    currentScene = scene;
    scene.initScene(WIDTH, HEIGHT);
    stage.setScene(scene.getScene());
  }

  /**
   * Loads a previous simulation - Implementation still to be determined
   */
  @Override
  public void loadSimulation(String action) {

  }
}
