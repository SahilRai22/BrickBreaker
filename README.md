
# Developing Maintainable Software - Breakout
- Name: Sahil Rai

## How to compile the code and run the application
- Right click on StartGame then click Run or Click ► icon on top right window
- Tested on MacOS, Java version "17.0.1", JavaFX "17.0.1"

## Where to find the Javadoc documentation
The Javadoc’s can be located following this directory:
- DMS_CW/src/JavaDoc/index-files
- Right click on html file and select browser

## Working features
The following features is implemented in the path src/main/java/ac/uk/nottingham/comp2013_cw/

##### Javafx Menu Screen 
- This implements menu with buttons to start game, end game, and change game screen colour.

##### Music
- Button: Plays music in menu
- Sound effect: When the brick breaks gives sound effects

##### Help
The help button changes stage to insturction stage

##### Colour
- Colour picker in menu changes game screen colour

##### Score 
- ScoreCounter gives 100 points when brick breaks 

##### High Score 
- Permanent high-score list: generates list 
- High-score name: gets a pop-up to enter name for highscore

##### New Level
- Added new levels by modifying 

##### Build file
- Used maven build file 

##### Packages
The packages are organised based on their function in a meaningful manner, i.e.
- Brick package follows a structural pattern based on Inheritance.
- Menu package adopts MVC design pattern using Controller.

##### Documentation
- JavaDocs comments added

##### Testing
- tested classes

## Incorrectly functioning features

#### High score (Javax)
Attempted to read and print the highscore.txt file into the javaFX screen

## Unimplemented features
- Using game Object sprites
- Swing translation to Java

## New Java classes introduced
##### MainMenu
- This the main menu class which is used for the primary stage view using Javafx.

##### Controller
- This class controls menu.fxml, & help.fxml 

##### HsController
- This class controls highscore.fxml

##### Crack
- Crack is extended from Brick class, most of the features was extracted from Brick class.

##### Score 
- Responsible for score responsibilities.

## Modified Java classes

All Classes have been modified to follow the following coding conventions: 

1. Methods less than 75 lines
2. Methods less than five indentations
3. Line of code less than 80 characters
4. Class variables starts with m_ 
5. Getter and Setter used
6. Accessor methods are moved to top of files
7. Class variables are private
9. Methods have less than 5 parameter, Exception: makeChessBoardLevel in Wall Class
10. Symbolic constants replaces magic numbers

#### Brick
- The nested class Crack has been completely removed from Brick class for single responsibility.

#### Wall 
- Added score count, sound effect during impact.
- Implemented new levels.

#### StartGame
- Initialising swing now actiavted in Controller class.

#### GameBoard
- Allows Javafx screen to change swing screen colour.

#### GameFrame 
- Removed instructions on title, it's set as a button in main menu. 

#### DebugPannel 
- Removed int max and min = 4, as it is already set via parameters.

#### Clay, Cement, Steel, Brick
- Removed string NAME, as it is not being utilised.

- In class Controller, cant utilise stage.hide(). This results in main menu still being shown behind.



