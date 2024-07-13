package slogo.view;


import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.controller.ApplicationController;

/**
 * Start Scene for our SLOGO
 *
 * @author Jonathan Esponda
 */
public class SplashScreen implements Scene {

  private final ApplicationController controller;
  private javafx.scene.Scene scene;
  private final SceneSwitcher sceneSwitcher;
  private static Text languageInstruction;
  private static final String[] languages = {"Bundle_en_us", "Bundle_yo_ng", "Bundle_es_es"};
  private static int currentLanguageIndex = 0;
  private static String languageCode;
  private VBox parentBox;


  public SplashScreen(SceneSwitcher sceneSwitcher, ApplicationController controller) {
    this.sceneSwitcher = sceneSwitcher;
    this.controller = controller;
  }

  /**
   * Initialize splash screen
   *
   * @param width:  width of screen
   * @param height: height of screen
   */
  @Override
  public void initScene(int width, int height) {
    parentBox = new VBox(25);
    parentBox.setAlignment(Pos.CENTER);
    initTitleText(parentBox);
    initLanguageInstruction(parentBox);
    initSplashButtons(parentBox);
    this.scene = new javafx.scene.Scene(parentBox, width, height);
    scene.getStylesheets().add(getClass().getResource("/SplashScreens.css").toExternalForm());
  }

  /**
   * Buttons of splash screen are initialized
   *
   * @param parentBox: Vertical box storing children nodes
   */
  private void initSplashButtons(VBox parentBox) {
    HBox buttonBox = new HBox(10);
    buttonBox.setAlignment(Pos.CENTER);
    MenuButton languageSelector = new MenuButton("Language");

    MenuItem english = new MenuItem("English");
    MenuItem spanish = new MenuItem("Español");
    MenuItem yoruba = new MenuItem("Yoruba");

    english.setOnAction(event -> {
      controller.setCurrentLanguage("English");
      sceneSwitcher.switchToScene(new SplashScreen2(sceneSwitcher,
          "Bundle_en_us", controller));
    });
    spanish.setOnAction(event -> {
      controller.setCurrentLanguage("Spanish");
      sceneSwitcher.switchToScene(new SplashScreen2(sceneSwitcher,
          "Bundle_es_es", controller));
    });
    yoruba.setOnAction(event -> {
      controller.setCurrentLanguage("Yoruba");
      sceneSwitcher.switchToScene(new SplashScreen2(sceneSwitcher,
          "Bundle_yo_ng", controller));
    });

    languageSelector.getItems().addAll(english, spanish, yoruba);
    languageSelector.setOnAction(e -> sceneSwitcher.loadSimulation("load"));
    buttonBox.getChildren().addAll(languageSelector);
    parentBox.getChildren().add(buttonBox);
  }

  /**
   * Text of splash screen is initialized
   *
   * @param parentBox: Vertical box storing children nodes
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
   * Initializes the language instructions so that users know to use the drop down to select a
   * language
   *
   * @param parentBox the vertical box that makes up the splash screen info
   */
  private void initLanguageInstruction(VBox parentBox) {
    languageInstruction = new Text();
    updateLanguage();
    languageInstruction.setStyle("-fx-font-size: 14px;");
    parentBox.getChildren().addAll(languageInstruction);
  }

  /**
   * Updates language selection instructions so that they can be seen in the different language
   * options
   */
  public static void updateLanguage() {
    languageCode = languages[currentLanguageIndex];
    String selectLanguageText = ResourceBundle.getBundle("slogo.example." + languageCode).
        getString("intro_Language");
    languageInstruction.setText(selectLanguageText);

    // Cycle through the different languages
    currentLanguageIndex = (currentLanguageIndex + 1) % languages.length;
  }

  /**
   * Update scene
   *
   * @param elapsedTime: elapsed time for frame rates
   */
  @Override
  public void update(double elapsedTime) {

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
