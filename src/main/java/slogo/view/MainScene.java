package slogo.view;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo.config.SaveGame;
import slogo.controller.ApplicationController;
import slogo.Exceptions.NoCssFileException;

import java.lang.reflect.Field;
import java.util.HashMap;

import slogo.config.FileSelector;



public class MainScene extends slogo.view.ButtonNames implements slogo.view.Scene,
    SimulationLoader{

    private javafx.scene.Scene scene;
    private Stage primaryStage;
    private final SceneSwitcher sceneSwitcher;
    private double centerX;
    private double centerY;

    private TurtleImage turtle;
    private VariablePane variablepane;
    private final ApplicationController controller;
    private static final String TITLE = "SLOGO, TEAM 07";
    private static final int SCENE_DIMENSION = 650;
    private final String [] POSSIBLE_COMMANDS = {"FORWARD", "LEFT", "RIGHT", "BACK", "PENUP", "PENDOWN", "SHOWTURTLE", "HIDETURTLE", "FORWARD", "LEFT", "RIGHT", "FORWARD", "LEFT", "RIGHT", "BACK", "PENUP", "PENDOWN", "SHOWTURTLE", "HIDETURTLE", "FORWARD", "LEFT", "RIGHT"};

    public static final String TURTLE_IMAGE = "/view/Turtle.png";
    private static final String DUKE_MODE = "/MainSceneStyleDUKE.css";

    private static final String LIGHT_MODE = "/LightMode.css";

    private static final String DARK_MODE = "/DarkMode.css";
    private static final String UNC_MODE = "/MainSceneStyleUNC.css";
    private static final String DATA_FILE_EXTENSION_PNG = "*.png";
    private StringBuilder saveText = new StringBuilder();
    private String lang;

    private VariableScrollPane VSP;
    private HistoryScrollPane HSP;
    private static Pane turtleArea;
    private HistoryText history = new HistoryText();
    private static List<TurtleImage> turtles = new ArrayList<>();
    private static Map<Integer, TurtleImage> turtleImages = new HashMap<>();


    public MainScene (SceneSwitcher sceneSwitcher, String action, ApplicationController controller)
    {
        super(action);
        this.sceneSwitcher = sceneSwitcher;
        this.controller = controller;
        this.lang = action;

    }

    private void getCSSFile(Scene myScene, String CSSfile) throws NoCssFileException {
        try{
            myScene.getStylesheets().add(MainScene.class.getResource(CSSfile).toExternalForm());
        }
        catch(Exception e){
            throw new NoCssFileException("no CSS file found");
        }

    }

    private void switchTheme(String CSSfile){
        try {
            scene.getStylesheets().clear();
            getCSSFile(scene, CSSfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initScene(int width, int height) {
        try {
            //Group root = new Group();
            BorderPane borderPane = new BorderPane();
            this.scene = new Scene(borderPane, width, height);
            getCSSFile(scene, "/MainSceneStyler.css");
            HashMap<String, String> mainMap = super.getTranslationMap();


            TextField text = new TextField();
            text.setPromptText(mainMap.get(text_Field));
            text.getStyleClass().add("custom-text-field");

            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        String input = text.getText();
                        controller.handleCommandInput(input);
                        controller.notifyHistoryScrollPane();
                        text.clear();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            TextField text2 = new TextField("Value : ");
            text2.setEditable(false);
            text2.getStyleClass().add("custom-text-field");
            variablepane = new VariablePane(text2);

            HBox bottomItems = new HBox();
            bottomItems.getChildren().add(text);
            bottomItems.getChildren().add(variablepane.getPane());
            HBox.setHgrow(text, Priority.ALWAYS);
            HBox.setHgrow(variablepane.getPane(), Priority.ALWAYS);



            HBox topMenu = new HBox(10);
            topMenu.setAlignment(Pos.CENTER);

            MenuButton penMenuButton = new MenuButton(mainMap.get("PEN"));
            penMenuButton.getStyleClass().add("menu-button");

            MenuItem blackMenuItem = new MenuItem("BLACK");
            MenuItem blueMenuItem = new MenuItem("BLUE");
            MenuItem greenMenuItem = new MenuItem("GREEN");
            MenuItem orangeMenuItem = new MenuItem("ORANGE");
            MenuItem pinkMenuItem = new MenuItem("PINK");

            penMenuButton.getItems().addAll(blackMenuItem, blueMenuItem, greenMenuItem, orangeMenuItem, pinkMenuItem);

            blackMenuItem.setOnAction(e -> switchPenColor(Color.BLACK, 1));
            blueMenuItem.setOnAction(e -> switchPenColor(Color.BLUE, 2));
            greenMenuItem.setOnAction(e -> switchPenColor(Color.GREEN, 3));
            orangeMenuItem.setOnAction(e -> switchPenColor(Color.ORANGE, 4));
            pinkMenuItem.setOnAction(e -> switchPenColor(Color.PINK, 5));

            MenuButton az = new MenuButton(mainMap.get(File));
            MenuItem home = new MenuItem("Home");
            MenuItem loader = new MenuItem("Load File");

            az.getItems().addAll(home, loader);

            home.setOnAction(event -> {
                sceneSwitcher.switchToScene(new SplashScreen2(sceneSwitcher, lang, controller));
            });

            loader.setOnAction(event -> {
                loadSimulation(controller, sceneSwitcher);
            });

            Button bb = new Button(mainMap.get(Save));
            Button cc = new Button(mainMap.get(Turtle));

            MenuButton menuButton = new MenuButton(mainMap.get(Theme));
            menuButton.getStyleClass().add("menu-button");
            MenuItem unc = new MenuItem("UNC");
            MenuItem duke = new MenuItem("DUKE ");
            MenuItem dark = new MenuItem("DARK");
            MenuItem light = new MenuItem("LIGHT");

            duke.setOnAction(e -> switchTheme(DUKE_MODE));
            unc.setOnAction(e -> switchTheme(UNC_MODE));
            dark.setOnAction(e -> switchTheme(DARK_MODE));
            light.setOnAction(e -> switchTheme(LIGHT_MODE));

            menuButton.getItems().addAll(unc, duke, dark, light);
            topMenu.getChildren().addAll(menuButton);
            topMenu.getChildren().addAll(penMenuButton);



            topMenu.getChildren().addAll(az, bb, cc);

            HBox.setHgrow(topMenu, Priority.ALWAYS);

            HBox secondtopMenu = new HBox(10);
            secondtopMenu.setAlignment(Pos.CENTER);
            Button Paus = new Button(mainMap.get(Pause));
            Button ww = new Button(mainMap.get(Replay));
            Button dx = new Button(mainMap.get(Speed));
            secondtopMenu.getChildren().addAll(Paus, ww, dx);

            for(Node child: topMenu.getChildren()){
                child.getStyleClass().add("button");
            }
            for(Node child:secondtopMenu.getChildren()){
                child.getStyleClass().add("button");
            }

            Button addTurtleButton = new Button(mainMap.get(ADDTURTLE));
            addTurtleButton.setOnAction(e -> addNewTurtle());
            secondtopMenu.getChildren().add(addTurtleButton);


            GridPane top = new GridPane();
            top.setVgap(8);

            GridPane.setRowIndex(topMenu, 0);
            GridPane.setRowIndex(secondtopMenu,1);
            top.getChildren().addAll(topMenu, secondtopMenu);
            top.setAlignment(Pos.TOP_CENTER);



            VBox leftMenu = new VBox(40);
            VBox headVBox = new VBox(20);

            ScrollPane scrollPane = new ScrollPane();
            VBox contentBox = new VBox (20);
            for(String com : POSSIBLE_COMMANDS){
                String makedistinct = "ln";  //two distinguish between forward command and label name in properties file
                String result = com + makedistinct;
                Label label = new Label(mainMap.get(result));
                label.getStyleClass().add("label");
                new HelpDocumentation(label, mainMap.get(com));
                contentBox.getChildren().add(label);
            }
            scrollPane.setContent(contentBox);
            scrollPane.getStyleClass().add("scrollPane");



            ScrollPane historyScrollPane = new ScrollPane();
            Label Historylabel = new Label(mainMap.get(History));
            VBox contentBox2 = new VBox ();
            contentBox2.getChildren().add(Historylabel);
            historyScrollPane.setContent(contentBox2);
            historyScrollPane.getStyleClass().add("scrollPane");
            HSP = new HistoryScrollPane(historyScrollPane, contentBox2, this.controller);

            ScrollPane variablesScrollPane = new ScrollPane();
            Label Variableslabel = new Label(mainMap.get(Variables));
            VBox contentBox3 = new VBox ();
            contentBox3.getChildren().add(Variableslabel);
            variablesScrollPane.setContent(contentBox3);
            variablesScrollPane.getStyleClass().add("scrollPane");
            VSP = new VariableScrollPane(variablesScrollPane, contentBox3, this.controller);




            headVBox.getChildren().add(scrollPane);
            headVBox.getChildren().add(leftMenu);
            headVBox.getChildren().add(historyScrollPane);
            headVBox.getChildren().add(VSP.getMyPane());

            headVBox.setSpacing(15);

            for(Node child: leftMenu.getChildren()){
                child.getStyleClass().add("button");
            }

            turtleArea = new Pane();
            turtleArea.getStyleClass().add("turtle_area");

            int originalTurtleID = 1;
            turtle = new TurtleImage(turtleArea);
            turtleImages.put(originalTurtleID, turtle);

            ImageView turtlePicture = turtle.getImageView();

            turtleArea.getChildren().addAll(turtlePicture);


            //Reference:
            //https://stackoverflow.com/questions/38216268/how-to-listen-resize-event-of-stage-in
            //-javafx
            turtleArea.widthProperty().addListener((obs, oldVal, newVal) -> {
                centerX = ((newVal.doubleValue() - turtlePicture.getFitWidth()) / 2);
                turtlePicture.setX(centerX);
                //System.out.println(centerX);
            });

            turtleArea.heightProperty().addListener((obs, oldVal, newVal) -> {
                centerY = (newVal.doubleValue() - turtlePicture.getFitHeight() / 2) / 2;
                turtlePicture.setY(centerY);
                //System.out.println(centerY);
            });

            cc.setOnAction(event -> {

                FileSelector fileSelector = new FileSelector();
                File file = fileSelector.makeChooser(DATA_FILE_EXTENSION_PNG)
                    .showOpenDialog(primaryStage);
                if (file != null) {
                    // Set the CSS style class dynamically to the selected image file path
                    String imagePath = file.toURI().toString();
                    turtlePicture.setStyle("-fx-image: url('" + imagePath + "')");
                    // Optionally, you can also add the style class if needed
                    if (!turtlePicture.getStyleClass().contains("turtle-image")) {
                        turtlePicture.getStyleClass().add("turtle-image");
                    }

                }});

            YourView view = new YourView(controller, turtle, variablepane, VSP, HSP, history,
                turtleArea);

            bb.setOnAction(event -> {
                saveText.append("<slogo>");
                saveText.append("   <language>" + lang + "</language>\n");
                try {
                    for (String call : history.getHistory()) {
                        saveText.append("   <call>" + call + "</call>\n");
                    }
                    saveText.append("</slogo>");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                SaveGame save = new SaveGame(primaryStage);
                save.createXmlFile(saveText.toString());
                saveText.setLength(0);
                System.out.println("I was here");
            });

            borderPane.setTop(top);
            borderPane.setLeft(headVBox);
            borderPane.setBottom(bottomItems);
            borderPane.setCenter(turtleArea);
            borderPane.getStyleClass().add("stage");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchPenColor(Color newcolor, int color_number) {
        try {
            controller.givePenColor(color_number);
            Field[] turtleFields = turtle.getClass().getDeclaredFields();
            for(Field field : turtleFields){
                if(field.getName().equals("penColor")){
                    field.setAccessible(true);
                    field.set(turtle, newcolor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exceptions appropriately
        }
    }

    // Method to add a new turtle image to the scene
    // Modify addNewTurtleImageToView to add to map
    public static void addNewTurtleImageToView(int turtleId, double x, double y) {
        TurtleImage newTurtle = new TurtleImage(turtleArea);
        newTurtle.setPosition(x, y);
        turtleImages.put(turtleId, newTurtle);
        turtleArea.getChildren().add(newTurtle.getImageView());
    }

    private void addNewTurtle() {
        controller.addNewTurtle();
    }

    public static void updateTurtleHeading(int turtleId, double degrees) {
        TurtleImage turtleImage = turtleImages.get(turtleId);
        if (turtleImage != null) {
            Platform.runLater(() -> turtleImage.updateHeading(degrees));
        }
    }

    // New method to update position by turtle ID
    public static void updateTurtlePosition(int turtleId, double x, double y) {
        TurtleImage turtleImage = turtleImages.get(turtleId);
        if (turtleImage != null) {
            Platform.runLater(() -> turtleImage.updatePosition(x, y));
        }
    }

    public static List<TurtleImage> getTurtles() {
        return turtles;
    }


    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public Scene getScene() {
        return scene;
    }

}
