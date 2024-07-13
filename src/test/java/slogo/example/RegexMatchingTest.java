package slogo.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Feel free to completely change this code or delete it entirely. 
 */
class RegexMatchingTest {
    private RegexMatching myApp;

    // create new instance of test object before each test is run
    @BeforeEach
    void setup () {
        myApp = new RegexMatching();
    }


    @Test
    void testTokenizeLanguageCommand () {
        myApp.setPatterns("English");
        // try different case options
        assertEquals("Forward", myApp.getSymbol("fd"));
        assertEquals("Forward", myApp.getSymbol("FD"));
        assertEquals("Forward", myApp.getSymbol("Fd"));
        assertEquals("Forward", myApp.getSymbol("forward"));
        assertEquals("Forward", myApp.getSymbol("Forward"));
        assertEquals("Forward", myApp.getSymbol("ForwarD"));
        assertEquals("Forward", myApp.getSymbol("FORWARD"));
        // spelling errors, or wrong language, are treated as a user defined command
        assertEquals("Command", myApp.getSymbol("fdd"));
        assertEquals("Command", myApp.getSymbol("ava"));
    }

    @Test
    void testTokenizeSyntax () {
        myApp.setPatterns("Syntax");
        // try different decimal placements, positive and negative
        // note, must have a digit before decimal point
        assertEquals("Constant", myApp.getSymbol("100"));
        assertEquals("Constant", myApp.getSymbol("-100"));
        assertEquals("Constant", myApp.getSymbol("100."));
        assertEquals("Constant", myApp.getSymbol("100.0"));
        assertEquals("Constant", myApp.getSymbol("100.001"));
        assertEquals("Constant", myApp.getSymbol("0.001"));
        assertEquals("Constant", myApp.getSymbol("-0.001"));
    }

    @Test
    void testEmptyStringsProduceErrors () {
        myApp.setPatterns("English");

        assertThrows(IllegalArgumentException.class, () -> myApp.getSymbol(null));
        assertThrows(IllegalArgumentException.class, () -> myApp.getSymbol(""));
        assertThrows(IllegalArgumentException.class, () -> myApp.getSymbol(" "));
    }

    @Test
    void testReadLanguageProperties () {
        final String COMMAND = "Forward";

        assertEquals("forward|fd", myApp.getCommand("English", COMMAND));
        assertEquals("avanzar|ava", myApp.getCommand("Spanish", COMMAND));
    }
}
