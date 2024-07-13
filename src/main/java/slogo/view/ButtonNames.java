package slogo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

abstract class ButtonNames {
    private final String PFILE;
    protected String File = "File";
    protected String Save = "Save";
    protected String Turtle = "Turtle";
    protected String Pen = "Pen";
    protected String Language = "Language";
    protected String Pause = "Pause";
    protected String Replay = "Replay";
    protected String Speed = "Speed";

    protected String Commands = "Commands";

    protected String Variables = "Variables";

    protected String History = "History";

    protected String Help = "Help";

    protected String text_Field = "text_Field";

    protected String Theme = "Theme";

    protected String FORWARD = "FORWARD";
    protected String LEFT = "LEFT";
    protected String BACK = "BACK";
    protected String RIGHT = "RIGHT";
    protected String PENUP = "PENUP";
    protected String PENDOWN = "PENDOWN";

    protected String FORWARDlabel = "FORWARDln";
    protected String LEFTlabel = "LEFTln";
    protected String BACKlabel = "BACKln";
    protected String RIGHTlabel = "RIGHTln";

    protected String PENUPlabel = "PENUPln";
    protected String PENDOWNlabel = "PENDOWNln";
    protected String SHOWTURTLELabel = "SHOWTURTLEln";
    protected String HIDETURTLELabel = "HIDETURTLEln";

    protected String SHOWTURTLE = "SHOWTURTLE";
    protected String HIDETURTLE = "HIDETURTLE";

    protected String ADDTURTLE = "ADDTURTLE";



    protected String PEN = "PEN";






    ArrayList<String> protectedStrings = new ArrayList<>();
    HashMap<String, String> map = new HashMap<>();

    ButtonNames(String PropertiesFile) {
        this.PFILE = PropertiesFile;

        protectedStrings.add(File);
        protectedStrings.add(Save);
        protectedStrings.add(Turtle);
        protectedStrings.add(Pen);
        protectedStrings.add(Language);
        protectedStrings.add(Pause);
        protectedStrings.add(Replay);
        protectedStrings.add(Speed);
        protectedStrings.add(Commands);
        protectedStrings.add(Variables);
        protectedStrings.add(History);
        protectedStrings.add(Help);
        protectedStrings.add(Theme);
        protectedStrings.add(text_Field);
        protectedStrings.add(FORWARD);
        protectedStrings.add(LEFT);
        protectedStrings.add(RIGHT);
        protectedStrings.add(BACK);
        protectedStrings.add(FORWARDlabel);
        protectedStrings.add(LEFTlabel);
        protectedStrings.add(RIGHTlabel);
        protectedStrings.add(BACKlabel);
        protectedStrings.add(PENUP);
        protectedStrings.add(PENDOWN);
        protectedStrings.add(PENUPlabel);
        protectedStrings.add(PENDOWNlabel);
        protectedStrings.add(SHOWTURTLE);
        protectedStrings.add(HIDETURTLE);
        protectedStrings.add(SHOWTURTLELabel);
        protectedStrings.add(HIDETURTLELabel);

        protectedStrings.add(PEN);
        protectedStrings.add(ADDTURTLE);


        for (String key : protectedStrings) {
            String value = ResourceBundle.getBundle("slogo.example." + PFILE).getString(key);
            map.put(key, value);
        }


    }

    protected HashMap<String, String> getTranslationMap() {
        return map;
    }
}