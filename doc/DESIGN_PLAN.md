# Logo Turtle Design Plan

### NAMES: Nikita, Joseph, Konnur, Jonathan

## Introduction

The primary design goal of the project is
to maintain a clear separation between the backend (Model) and the frontend (View), adhering to the
Model-View-Controller
(MVC) architectural pattern. This separation allows for modularity, extensibility, and ease of
maintenance. The backend
(Model) encapsulates the core logic for parsing commands, executing actions, and managing program
state, while the
frontend (View) handles user interactions and displays graphical output.

We want to provide flexibility in extending the language and commands available in SLogo, while
ensuring a robust and
user-friendly IDE that supports interactive command execution and visualization of programming
constructs through turtle
graphics. Our system is "open" for extension through, but "closed" in terms of modification,
ensuring stability and
reliability.

## Features

* The IDE can distinctly recognize and process constants, variables, commands, a list of commands,
  and comments
    * Perform 13 different turtle movement or visibility commands (eg: forward, pen-up, hide turtle,
      clear screen)
    * Perform 5 different queries (eg: x coordinate? , pendown?)
    * Perform 16 different math operations (eg: product, cosine, random, log)
    * Perform 9 different boolean operations (eg: and, not, less than)
    * Allow users to make their own variables and give it a value for future use
* Has a splash screen that allows users to choose color theme, language, or load a previous session
* Show a history of all previous run commands and user-defined variables
* Create help documentation allowing users to see command details and aliases
* Save a program and load it into a new session
* Customize pen color, UI theme, and turtle images

## Configuration File Format

XML 1:

```xml

<Command>
  <CanonicalName>FORWARD</CanonicalName>
  <Description>Move the turtle forward by a specified distance</Description>
  <Example>forward 50</Example>
  <HelpDocumentation>
    Moves the turtle forward in its current direction by the number of units specified in the
    parameter. The turtle's pen state determines whether a line is drawn.
  </HelpDocumentation>
  <Parameters>
    <Parameter name="distance" type="double" description="The distance to move forward"/>
  </Parameters>
  <ReturnValue type="void" description="This command does not return a value."/>
  <Category>Movement</Category>
  <NumberOfExpectedParameters>1</NumberOfExpectedParameters>
  <ImplementingClassName>slogo.model.commands.movement.ForwardCommand</ImplementingClassName>
  <Aliases>
    <Alias>FD</Alias>
    <Alias>forward</Alias>
    <Alias>fd</Alias>
    <Alias>fwd</Alias>
  </Aliases>
</Command>



```

XML 2:

```xml

<Command>
  <CanonicalName>and</CanonicalName>
  <Description>Returns 1 if test1 and test2 are non-zero, otherwise 0</Description>
  <Example>and 0 0</Example>
  <HelpDocumentation>
    Returns 1 if test1 and test2 are non-zero, otherwise 0
  </HelpDocumentation>
  <Parameters>
    <Parameter name="test1" type="double" description="First number to check"/>
    <Parameter name="test2" type="double" description="Second number to check"/>
  </Parameters>
  <ReturnValue type="int" description="Whether both numbers are non-zero"/>
  <Category>Bool</Category>
  <NumberOfExpectedParameters>2</NumberOfExpectedParameters>
  <ImplementingClassName>slogo.model.commands.bool.AndCommand</ImplementingClassName>
  <Aliases>
    <Alias/>
    <Alias/>
  </Aliases>
</Command>
```

## Test Plan

### MODEL

#### Project Feature 1: Parsing a Command

* Parsing a Valid Command
    * Scenario:  User enters a valid command "forward 50".
    * Step 1: Call executeCommand("forward 50") from the external API.
    * Step 2: Expect the command to be parsed and executed successfully.
    * Expected Outcome: The turtle should move forward by 50 units, and the history should be
      updated accordingly.

* Parsing an Invalid Command
    * Scenario: User enters an invalid command "mov 50".
    * Step 1: Call the parsing method parseAndExecuteString command) of the CommandParser class with
      the command "mov 50".
    * Step 2: Throw an InvalidParsingException
    * Expected Outcome: The parsing should fail due to the command being invalid and an appropriate
      message should be displayed.

* Parsing a Command with Incorrect Parameters
    * Scenario: User enters a command "forward fifty".
    * Step 1: Call the parsing method parseAndExecuteString command) of the CommandParser class with
      the command "forward fifty".
    * Step 2: Throw an InvalidParsingException
    * Expected Outcome: The parsing should fail due to incorrect parameter format and an appropriate
      message should be displayed.

#### Project Feature 2: Retrieving Command History

* Retrieving Command History Correctly
    * Scenario: User requests command history by pressing a button
    * Step 1: Call the getInfo("history") method from the external API.
    * Step 2: Display the history on the main screen.
    * Expected Outcome: The command history should be successfully retrieved without errors. Verify
      that the returned
      command history matches the expected sequence of commands entered by the user.

* Retrieving Command History with Empty History
    * Scenario: User requests command history when there are no previous commands.
    * Step 1: Ensure that the command history is empty.
    * Step 2: Call the getInfo("history") method from the external API.
    * Expected Outcome: The command history should be retrieved successfully, but it should be
      empty.

* Retrieving Command History from Saved File
    * Scenario: User loads a previously saved file containing command history.
    * Step 1: Save a set of commands to a file.
    * Step 2: Load the previously saved file using the loadFile() method from the FileManager class.
    * Step 3: Execute additional commands to modify the command history.
    * Step 4: Retrieve the command history using the getInfo("history") method from the external
      API.
    * Expected Outcome: The system should retrieve and display the command history stored in the
      loaded file, including
      both the originally saved commands and any subsequent modifications.

### VIEW

#### Project Feature 3: Changing Pen Color

* Valid Pen Color Change
    * Scenario: User selects a valid color and changes the pen color.
    * Step 1: User selects a valid color from the color palette.
    * Step 2: User executes a command to change the pen color to the selected color.
    * Expected Outcome: The pen color should successfully change to the selected color, and the
      drawing on the canvas
      should reflect the new color only for future lines.

* Invalid Pen Color Change
    * Scenario: User attempts to change the pen color with an invalid color selection.
    * Step 1: User selects an invalid color (e.g., a color not available in the palette).
    * Step 2: User executes a command to change the pen color to the selected color.
    * Expected Outcome: The system should prevent the pen color from changing to an invalid color
      and provide feedback
      to the user indicating that the selected color is not valid.


* Default Pen Color:
    * Scenario: User attempts to change the pen color without selecting a color.
    * Step 1: User does not select any color from the color palette.
    * Step 2: User executes a command to change the pen color to the selected color.
    * Expected Outcome: The system should maintain the current pen color or revert to a default
      color if applicable,
      and the drawing on the canvas should remain unchanged. Additionally, the system should provide
      feedback to the user
      indicating that no color was selected for the pen.

#### Project Feature 4: Changing Theme/Background

* Applying a New Theme
    * Scenario: User selects a new theme from the available options.
    * Step 1: User selects a new theme from the list of available themes.
    * Expected Outcome: The application interface should update to reflect the selected theme,
      including changes to
      colors, fonts, and other visual elements. The new theme should be applied consistently across
      all parts of the application.

* Reverting to Default Theme:
    * Scenario: User decides to revert back to the default theme after applying a custom theme.
    * Step 1: User selects the option to revert to the default theme.
    * Expected Outcome: The application interface should revert to the default theme, undoing any
      customizations made
      by the user. All visual elements should return to their original appearance as defined by the
      default theme.

* Retrieving Command History Correctly
    * Scenario: User attempts to select a theme that is not available or does not exist.
    * Step 1: User attempts to select a theme that is not included in the list of available themes.
    * Expected Outcome: The application should detect the invalid theme selection and display an
      error message or
      notification informing the user that the selected theme is not valid. The interface should
      remain unchanged,
      retaining the current theme settings.

