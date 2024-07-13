package slogo.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * An example class to show the basics of tokenizing a program file: identifying the purpose of individual words.
 *
 * Disclaimer: The code here is merely to demonstrate regular expressions to match input strings.
 *
 * NOTE: some methods are package friendly to allow for testing more directly
 *
 * @author Robert C. Duvall
 */
public class RegexMatching extends Application {
    // kind of data files to look for
    public static final String DATA_FILE_EXTENSION = "*.slogo";
    // default to start in the data folder to make it easy on the user to find
    public static final String DATA_FILE_FOLDER = System.getProperty("user.dir") + "/data";
    // NOTE: make ONE chooser since generally accepted behavior is that it remembers where user left it last
    private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);
    // command variations per class
    private static final String LANGUAGE_RESOURCE_PATH = RegexMatching.class.getPackageName() + ".languages.";


    // token types and the regular expression patterns that recognize those types
    // note, it is a list because order matters (some patterns may be more generic and so should be added last)
    private List<Entry<String, Pattern>> myTokens = new ArrayList<>();


    /**
     * @see Application#start(Stage)
     */
    @Override
    public void start (Stage primaryStage) {
        // show results from some example data
        showMessage(AlertType.INFORMATION, String.format("FORWARD = %s", getCommand("English", "Forward")));

        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
        if (dataFile != null) {
            String firstLine = readFile(dataFile).get(0).trim();
            List<String> tokens = tokenize(firstLine);
            showMessage(AlertType.INFORMATION, tokens.toString());
        }
    }

    /**
     * Get command pattern in a given language.
     */
    public String getCommand (String language, String command) throws IllegalArgumentException {
        setPatterns(language);
        for (Entry<String, Pattern> token : myTokens) {
            if (token.getKey().equalsIgnoreCase(command)) {
                return token.getValue().toString();
            }
        }
        throw new IllegalArgumentException(String.format("Invalid command given: %s", command));
    }

    /**
     *  Returns token type associated with given text.
     */
    public String getSymbol (String text) throws IllegalArgumentException {
        for (Entry<String, Pattern> e : myTokens) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        throw new IllegalArgumentException(String.format("Invalid command given: %s", text));
    }

    // given some text, prints results of parsing it using the given language
    // note, this simple "algorithm" will not handle SLogo comments
    private List<String> tokenize (String program) {
        // regular expression representing one or more whitespace characters (space, tab, or newline)
        final String WHITESPACE = "\\s+";

        List<String> tokens = new ArrayList<>();
        for (String symbol : program.split(WHITESPACE)) {
            tokens.add(String.format("%s : %s", symbol, getSymbol(symbol)));
        }
        return tokens;
    }

    // Returns true only if given text matches given regular expression pattern
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return text != null && regex.matcher(text.trim()).matches();
    }

    // Set up matchers, which checks in order given
    void setPatterns(String language) {
        // language specific matches are more specific, so add first to ensure they are checked first
        myTokens = getPatterns(language);
        // general checks, added last
        myTokens.addAll(getPatterns("Syntax"));
    }

    // Add given resource file to this language's recognized types
    private List<Entry<String, Pattern>> getPatterns (String language) {
        List<Entry<String, Pattern>> tokens = new ArrayList<>();
        ResourceBundle resources = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PATH + language);
        for (String key : Collections.list(resources.getKeys())) {
            tokens.add(new SimpleEntry<>(key,
                                         // THIS IS THE OTHER IMPORTANT LINE
                                         Pattern.compile(resources.getString(key), Pattern.CASE_INSENSITIVE)));
        }
        return tokens;
    }

    // this code is dense, hard to read, and throws exceptions so better to wrap in method
    private List<String> readFile (File file) {
        // regular expression representing one or more whitespace characters (space, tab, or newline)
        final String NEWLINE = "\\n+";

        try {
            String data = new String(Files.readAllBytes(Path.of(file.toURI())));
            return Arrays.asList(data.split(NEWLINE));
        }
        catch (IOException | NullPointerException e) {
            // NOT ideal way to handle exception, but not expected to happen since user selected the file
            System.out.println("ERROR: Unable to read input file " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // display given message to user using the given type of Alert dialog box
    private void showMessage (AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    // set some sensible defaults when the FileChooser is created
    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle("Open Data File");
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(DATA_FILE_FOLDER));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Data Files", extensionAccepted));
        return result;
    }

    /**
     * Start the program, give complete control to JavaFX.
     *
     * Default version of main() is actually included within JavaFX, so this is not technically necessary!
     */
    public static void main(String[] args) {
        launch(args);
    }
}
