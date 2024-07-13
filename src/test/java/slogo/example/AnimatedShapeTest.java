package slogo.example;

import java.util.InputMismatchException;
import java.lang.IllegalArgumentException;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import util.DukeApplicationTest;


/**
 * Show TestFX driving GUI for testing.
 *
 * @author Robert C. Duvall
 */
public class AnimatedShapeTest extends DukeApplicationTest {
    public static final double MATCH_TOLERANCE = 0.01;

    // keep in case need to call application methods in tests
    private AnimatedShape myApp;
    // keep GUI components used in multiple tests
    private Rectangle myActor;


    // this method is run BEFORE EACH test to set up application in a fresh state
    @Override
    public void start (Stage stage) {
        // create app and add scene for testing to given stage
        myApp = new AnimatedShape();
        Scene scene = myApp.makeScene(400, 100);
        stage.setScene(scene);
        stage.show();

        // components that will be reused in different tests
        myActor = lookup("#actor").query();
    }


    @Test
    void testAnimation () {
        assertEquals(100, myActor.getX(), MATCH_TOLERANCE);
        assertEquals(50, myActor.getY(), MATCH_TOLERANCE);

        Animation animation = myApp.makeAnimation(myActor, 350, 50, 90);
        animation.play();
        sleep(4, TimeUnit.SECONDS);    // PAUSE: not typically recommended in tests

        assertEquals(325, myActor.getX() + myActor.getTranslateX(), MATCH_TOLERANCE);
        assertEquals(50, myActor.getY(), MATCH_TOLERANCE);
        assertEquals(90, myActor.rotateProperty().get(), MATCH_TOLERANCE);
    }

    @Test
    void testSetResources () {
        assertThrows(IllegalArgumentException.class, () -> myApp.setResources(null));
        assertThrows(IllegalArgumentException.class, () -> myApp.setResources("  "));
        assertThrows(IllegalArgumentException.class, () -> myApp.setResources("DoesNotExist"));
        assertThrows(IllegalArgumentException.class, () -> myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH));
        assertThrows(IllegalArgumentException.class, () -> myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH + "DoesNotExist"));
    }

    @Test
    void testResourceNumbers () {
        myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH + "Numbers");

        assertEquals(13, myApp.getResourceNumber("OK"));
        assertEquals(13, myApp.getResourceNumber("OKwithSpaces"));

        assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Negative"));
        assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Real"));
        assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Word"));
        assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Pre"));
        assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Post"));
        assertThrows(InputMismatchException.class, () -> myApp.getResourceNumber("Mixed"));
    }

    @Test
    void testResourceColors () {
        myApp.setResources(AnimatedShape.CONFIGURATION_RESOURCE_PATH + "Colors");

        assertEquals(Color.GREEN, myApp.getResourceColor("OK"));
        assertEquals(Color.RED, myApp.getResourceColor("OKwithSpaces"));

        assertThrows(InputMismatchException.class, () -> myApp.getResourceColor("DoesNotExist"));
        Exception e = assertThrows(InputMismatchException.class, () -> myApp.getResourceColor("BadCase"));
        assertEquals("Property BadCase is not a color: Green", e.getMessage());
    }
}
