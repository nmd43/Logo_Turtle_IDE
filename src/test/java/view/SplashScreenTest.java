package view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.Main;
import util.DukeApplicationTest;

public class SplashScreenTest extends DukeApplicationTest {

  private Main mainApp;
  @Override
  public void start(Stage stage) throws Exception {
    mainApp = new Main();
    mainApp.start(stage);
    stage.show();
  }

  @Test
  void testSplashScreenButton() {
    //GIVEN the initial splash screen
    MenuButton button = lookup("Language").query();
    //WHEN the user attempts to interact with the scene
    //THEN there is a select language button
    assertNotNull(button);
  }

  @Test
  void testSplashScreenButtonOptions() {
    //GIVEN the initial splash screen
    MenuButton button = lookup("Language").query();
    //WHEN the language button is clicked
    List<MenuItem> options = button.getItems();

    //THEN there are three language options to choose from
    assertFalse(options.isEmpty(), "No language options");
    assertEquals(3, options.size(), "Expected number of languages is 3");
  }

  @Test
  void testClickOnEnglish() {
    //GIVEN the initial splash screen
    MenuButton button = lookup("Language").query();
    clickOn(button);
    clickDown();
    clickDown();
    //WHEN the English option is clicked
    press(KeyCode.ENTER);
    Button button2 = lookup("Start New Session").query();
    Button button3 = lookup("Load").query();
    //THEN the new splash screen has the correct language displayed on the buttons
    assertNotNull(button2);
    assertNotNull(button3);
  }

  @Test
  void testClickOnSpanish() {
    //GIVEN the initial splash screen
    MenuButton button = lookup("Language").query();
    clickOn(button);
    clickDown();
    clickDown();
    clickDown();
    //WHEN the Spanish option is clicked
    press(KeyCode.ENTER);
    Button button2 = lookup("Empezar Nueva Sesion").query();
    Button button3 = lookup("Cargar").query();
    //THEN the new splash screen has the correct language displayed on the buttons
    assertNotNull(button2);
    assertNotNull(button3);
  }

  @Test
  void testClickOnYoruba() {
    //GIVEN the initial splash screen
    MenuButton button = lookup("Language").query();
    clickOn(button);
    clickDown();
    clickDown();
    clickDown();
    clickDown();
    //WHEN the Yoruba option is clicked
    press(KeyCode.ENTER);
    Button button2 = lookup("Beere Sesssion Titun").query();
    Button button3 = lookup("Load").query();
    //THEN the new splash screen has the correct language displayed on the buttons
    assertNotNull(button2);
    assertNotNull(button3);
  }

  @Test
  void testClickStart() {

    //GIVEN the initial splash screen
    MenuButton button = lookup("Language").query();
    //WHEN the English option is clicked
    clickOn(button);
    clickDown();
    clickDown();
    press(KeyCode.ENTER);
    Button button2 = lookup("Start New Session").query();
    //AND the Start New Session Button is clicked
    clickOn(button2);
    //THEN the main screen should be loaded and a FILE button should exist
    MenuButton button3 = lookup("File").query();
    clickOn(button3);
  }

  private void clickDown() {
    press(KeyCode.DOWN).release(KeyCode.DOWN);
  }

}
