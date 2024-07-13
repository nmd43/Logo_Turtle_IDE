package view;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.Main;
import util.DukeApplicationTest;


public class MainSceneTest extends DukeApplicationTest {
    private Main mainApp;
    private final double startingX = 282.5;
    private final double startingY = 232.75;

    @Override
    public void start(Stage stage) throws Exception {
        mainApp = new Main();
        mainApp.start(stage);
        stage.show();
    }

    @BeforeEach
    public void start() {
        MenuButton button = lookup( "Language").query();
        clickOn(button);
        clickDown();
        clickDown();
        press(KeyCode.ENTER);
        Button button2 = lookup( "Start New Session").query();
        clickOn(button2);
    }
    private void clickDown() {
        press(KeyCode.DOWN).release(KeyCode.DOWN);
    }

    @Test
    void leftButtonsPresent() {
        MenuButton a = lookup("Commands").query();
        MenuButton b = lookup("Variables").query();
        MenuButton c = lookup("History").query();
        Button d = lookup("HELP").query();
        assertNotNull(a);
        assertNotNull(b);
        assertNotNull(c);
        assertNotNull(d);
    }

    @Test
    void commandOptions(){
        MenuButton command = lookup("Commands").query();
        List<MenuItem> options = command.getItems();
        assertEquals(3, options.size());
    }

    @Test
    void variablesOptions(){
        MenuButton Variables = lookup("Variables").query();
        List<MenuItem> options = Variables.getItems();
        assertEquals(3, options.size());
    }

    @Test
    void HistoryOptions(){
        MenuButton History = lookup("History").query();
        List<MenuItem> options = History.getItems();
        assertEquals(3, options.size());
    }
    @Test
    void HELP(){
        Button a = lookup("HELP").query();
        assertNotNull(a);
    }

    @Test
    void rightButtonsPresent() {
        Button a = lookup( "FILE").query();
        Button b = lookup( "SAVE").query();
        Button c = lookup( "TURTLE").query();
        Button d = lookup( "PEN").query();
        Button e = lookup( "LANGUAGE").query();
        Button f = lookup( "PAUSE").query();
        Button g = lookup( "REPLAY").query();
        Button h = lookup( "SPEED").query();
        assertNotNull(a);
        assertNotNull(b);
        assertNotNull(c);
        assertNotNull(d);
        assertNotNull(e);
        assertNotNull(f);
        assertNotNull(g);
        assertNotNull(h);
    }
    @Test
    public void testImageViewIsVisible() {
        //GIVEN that user has made it to the MainScene

        //WHEN the user looks at the screen

        //THEN the turtle-image view should be visible
        verifyThat(".image-view", isVisible());
    }

    @Test
    public void testTextField() {
        //GIVEN that the user is on the MainScene

        //WHEN the user clicks on the text field
        String command = "fd 50";
        clickOn(".text-field").write(command);
        //THEN they should be able to type in a command
    }

    @Test
    public void testForwrd() {
        //GIVEN that the user is on the MainScene

        //WHEN the user clicks on the text field AND types in the command
        //AND hits enter
        String command = "fd 50";
        clickOn(".text-field").write(command);
        clickOn(".image-view");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);
        //THEN the turtle should be at (332.5, 232.75)
        ImageView turtleImageView = lookup(".image-view").query();
        assertEquals(332.5, turtleImageView.getX());
        assertEquals(232.75, turtleImageView.getY());
    }
    @Test
    public void testTurnRight() {
        // GIVEN that the user is on the MainScene

        // WHEN the user types the command to turn right
        String command = "right 90"; // Turn right by 90 degrees
        clickOn(".text-field").write(command);
        clickOn(".image-view");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);

        // THEN the turtle should be facing the new direction (considering initial direction is 0 degrees)
        ImageView turtleImageView = lookup(".image-view").query();
        assertEquals(0, turtleImageView.getRotate() % 360, 0.001); // Check if the turtle is facing the right direction
    }
    @Test
    public void testTurnLeft() {
        // GIVEN that the user is on the MainScene

        // WHEN the user types the command to turn left
        String command = "left 45"; // Turn left by 45 degrees
        clickOn(".text-field").write(command);
        clickOn(".image-view");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);

        // THEN the turtle should be facing the new direction (considering initial direction is 0 degrees)
        ImageView turtleImageView = lookup(".image-view").query();
        assertEquals(315, turtleImageView.getRotate() % 360, 0.001); // Check if the turtle is facing the left direction
    }

    @Test
    public void testBackward() {
        // GIVEN that the user is on the MainScene

        // WHEN the user types the command to move backward
        String command = "back 30"; // Move backward by 30 units
        clickOn(".text-field").write(command);
        clickOn(".image-view");
        press(KeyCode.ENTER);
        release(KeyCode.ENTER);

        // THEN the turtle should be at the new position considering backward movement
        ImageView turtleImageView = lookup(".image-view").query();
        assertEquals(startingX - 30, turtleImageView.getX(), 0.001);
        assertEquals(startingY, turtleImageView.getY(), 0.001);
    }





    //test the turtle moving around
    //test if the pen is drawing
    //make sure chaning color reflects in the ine
    //set language, and check if the label button is on the language


}
