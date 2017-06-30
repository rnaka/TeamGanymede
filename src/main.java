/*
 * Raena Naka
 * Classes: JavaFX
 * Core Concept: Ability to add a menu using JavaFX
 * April 6, 2016
 */

/*
 * This program only creates a JavaFX GUI with a stage and a blank scene and 
 * assigns my name to the title of the stage. 
 */

//Importing JavaFX Libraries
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.event.*;

//JavaFX class
public class main extends Application {
	
	//main method
	public static void main(String[] args){
		
		//launches the GUI
		launch(args);
	}
	
	//start method 
	public void start(Stage primaryStage) throws Exception{
		
		//Sets the program title to "Text Editor"
		primaryStage.setTitle("Ambient Product");
		
		//Declare a new VBox object called layout
		VBox layout = new VBox();
		
		//creates a new scene object with parameters layout, 300 and 250
		Scene scene = new Scene(layout, 600, 500);
		//primary stage sets the scene to the scene object
		primaryStage.setScene(scene);
		//shows the primary stage
		primaryStage.show();
	}
	
}
