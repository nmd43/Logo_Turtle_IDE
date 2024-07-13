package slogo.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * An example class to show how to animate objects over time.
 *
 * Disclaimer: The code here for the GUI is poorly written (everything is done in this class),
 * and is merely to demonstrate sequencing animations and the power possible using properties.
 *
 * NOTE: some methods are package friendly to allow for testing more directly
 *
 * @author Robert C. Duvall
 */
public class AnimatedShape extends Application {
    public static final String TITLE = "JavaFX Animation Example";
    public static final String CONFIGURATION_RESOURCE_PATH = AnimatedShape.class.getPackageName() + ".";

    private Node myActor;
    private ResourceBundle myResources;


    public AnimatedShape () {
        super();
	setResources(CONFIGURATION_RESOURCE_PATH + "Animation");
    }

    /**
     * @see Application#start(Stage)
     */
    @Override
    public void start (Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(makeScene(getResourceNumber("ScreenWidth"), getResourceNumber("ScreenHeight")));
        primaryStage.show();

        // create and start animation, could be used in separate contexts
        Animation animation = makeAnimation(myActor, getResourceNumber("EndX"), getResourceNumber("EndY"),
                                            getResourceNumber("RotateDegrees"));
        animation.play();
    }

    /**
     * Allow different animations based on settings
     */
    public void setResources (String filename) throws IllegalArgumentException {
        try {
            myResources = ResourceBundle.getBundle(filename);
        }
        catch (NullPointerException | MissingResourceException e) {
            throw new IllegalArgumentException(String.format("Invalid resource file: %s", filename));
        }
    }


    // create a simple scene
    Scene makeScene (int width, int height) {
        Group root = new Group();
        myActor = makeActor(getResourceNumber("StartX"), getResourceNumber("StartY"),
                            getResourceNumber("ShapeWidth"), getResourceNumber("ShapeHeight"),
                            getResourceColor("ShapeColor"));
        root.getChildren().add(myActor);
        return new Scene(root, width,  height);
    }

    // create something to animate
    Node makeActor (int x, int y, int width, int height, Paint color) {
//        Shape result = new Rectangle(x, y, width, height);
        Shape result = getResourceShape("ShapeClass", x, y, width, height);
        result.setFill(color);
        result.setId("actor");
        return result;
    }

    // create sequence of animations
    Animation makeAnimation (Node agent, int endX, int endY, int rotateDegrees) {
        // create something to follow
        Path path = new Path();
        path.getElements().addAll(new MoveTo(agent.getBoundsInParent().getMinX(), agent.getBoundsInParent().getMinY()),
                                  new LineTo(endX, endY));
        // create an animation where the shape follows a path
        PathTransition pt = new PathTransition(Duration.seconds(2), path, agent);
        // create an animation that rotates the shape
        RotateTransition rt = new RotateTransition(Duration.seconds(1));
        rt.setByAngle(rotateDegrees);
        // put them together in order
        return new SequentialTransition(agent, pt, rt);
    }

    // helper methods that error check resource value
    int getResourceNumber (String key) throws InputMismatchException {
        // regular expression that matches a complete string containing one or more digits
        final String POSITIVE_NUMBER_PATTERN = "^\\d+$";

        String value = myResources.getString(key).trim();
        if (value.matches(POSITIVE_NUMBER_PATTERN)) {
            return Integer.parseInt(value);
        }
        else {
            throw new InputMismatchException(String.format("Property %s is not a number: %s", key, value));
        }
    }

    Paint getResourceColor (String key) throws InputMismatchException {
        String color = myResources.getString(key).trim();
        try {
            // note, get() parameter is null because it is a static value (no instance needed)
            return (Paint)Color.class.getField(color).get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new InputMismatchException(String.format("Property %s is not a color: %s", key, color));
        }
    }

    Shape getResourceShape (String key, int x, int y, int width, int height) throws InputMismatchException {
        String shape = myResources.getString(key).trim();
        try {
            Class<?> clazz = Class.forName(shape);
            Constructor<?> ctor = clazz.getDeclaredConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE);
            return (Shape)ctor.newInstance(x, y, width,height);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            // for debugging
            e.printStackTrace();
            throw new InputMismatchException(String.format("Property %s is not a shape: %s", key, shape));
        }
    }

    /**
     * Start the program, give complete control to JavaFX.
     *
     * Default version of main() is actually included within JavaFX, so this is not technically necessary!
     */
    public static void main (String[] args) {
        launch(args);
    }
}
