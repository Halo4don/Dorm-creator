//////////////////////////////////////////
//
// Title:    Dorm maker
// Author:   Don Hoat
// Email:    halodon234@gmail.com
//
///////////////////////////////////////////////////////////////////////////////
import processing.core.PImage;
import java.io.File;

/**
 * This class is a program that creates a dorm and is able to add objects, delete them, and saves them.
 */
public class DormDraw {
    private static PImage backgroundImage; // PImage object that represents the
    // background image
    private static Symbol[] symbols; // non-compact perfect size array storing


    // dorm symbols added to the display window
    // Adds a new Symbol (toAdd) to the perfect size array symbols.
    // The toAdd Symbol must be added to the first null position in the array.
    // If the array is full, the method does nothing.
    public static void addSymbol(Symbol[] symbols, Symbol toAdd) {
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] == null) {
                symbols[i] = toAdd;
                return;
            }
        }
    }

    /**
     * Checks if a key is pressed by a user and performs an action if there should be one
     */
    public static void keyPressed() {
        char userInput = Utility.key();
        userInput = Character.toLowerCase(userInput);

        switch (userInput) {
            case 'b':
                addSymbol(symbols, new Symbol("bed.png", Utility.mouseX(), Utility.mouseY()));
                break;

            case 'c':
                addSymbol(symbols, new Symbol("chair.png", Utility.mouseX(), Utility.mouseY()));
                break;

            case 'd':
                addSymbol(symbols, new Symbol("dresser.png", Utility.mouseX(), Utility.mouseY()));
                break;

            case 'k':
                addSymbol(symbols, new Symbol("desk.png", Utility.mouseX(), Utility.mouseY()));
                break;
            case 'f':
                addSymbol(symbols, new Symbol("sofa.png", Utility.mouseX(), Utility.mouseY()));
                break;

            case 'g':
                addSymbol(symbols, new Symbol("rug.png", Utility.mouseX(), Utility.mouseY()));
                break;
            case 'p':
                addSymbol(symbols, new Symbol("plant.png", Utility.mouseX(), Utility.mouseY()));
                break;
            case 'r':
                rotateHelper();
                break;
            case Utility.BACKSPACE:
                deleteHelper();
                break;
            case 's':
                Utility.save("dormDraw.png");
                break;
        }
    }

    /**
     * deletes an object if the mouse is hovered over it
     */
    private static void deleteHelper(){
        for (int i = 0; i < symbols.length; i++){
            if (isMouseOver(symbols[i])) {
                symbols[i] = null;
                return;
            }
        }
    }

    /**
     * rotates the object if the mouse is hovered over it
     */
    private static void rotateHelper(){
        for (int i = 0; i < symbols.length; i++){
            if (isMouseOver(symbols[i])){
                symbols[i].rotate();
                return;
            }
        }
    }
    // This method returns true if the method is over the input symbol
    public static boolean isMouseOver(Symbol symbol) {
        //It checks if the mouse is hovered over an object by checking from left to right endpoints and top to bottom endpoints
        if ((symbol != null) && (Utility.mouseX() < (symbol.x() + symbol.width() / 2) && Utility.mouseX() > (symbol.x()
                - symbol.width() / 2)) && ((Utility.mouseY() < (symbol.y() + symbol.height() / 2)) && (Utility.mouseY() > (symbol.y() - symbol.height() / 2)))) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Callback method called each time the user presses the mouse
     */
    public static void mousePressed() {
        for (int i = 0; i < symbols.length; i++){
            if (isMouseOver(symbols[i])){
                symbols[i].startDragging();
                return;
            }
        }
    }
    /**
     * Callback method called each time the mouse is released
     */
    public static void mouseReleased() {
        for (int i = 0; i < symbols.length; i++){
            if (symbols[i] != null){
                symbols[i].stopDragging();
            }
        }
    }

    /**
     * This method is automatically called by Utility.runApplication() when
     * the program begins. It creates and initializes the different data fields defined in your
     * program, and configures the different graphical settings of your application, such as
     * loading the background image, setting the dimensions of the display window, etc.
     */
    public static void setup() {
        // set the background image
        backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

        symbols = new Symbol[12];

    }

    /**
     * This method continuously executes the lines of code contained inside
     * its block until the program is stopped. It continuously draws the application display
     * 8
     * window and updates its content with respect to any change or any event which affects its
     * appearance
     */
    public static void draw() {
        Utility.background(Utility.color(255, 250, 250)); // snow color

        // Draw the background image at the center of the screen
        Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i] != null) {
                symbols[i].draw(); // where i is a valid index within the array symbols.
            }
        }
    }

    /**
     *  Just starts the app
     * @param args
     */
    public static void main(String[] args) {
        Utility.runApplication(); // starts the application
    }
}
