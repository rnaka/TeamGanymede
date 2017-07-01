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
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.event.*;
import javafx.geometry.Pos;

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
		
		VBox root = new VBox();
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		
		Circle circle = new Circle(200,200,100,Color.GRAY);
		
		Label label1 = new Label("Enter a value between 0 and 99:");
		Label label2 = new Label("");
		
		TextField value = new TextField();
		value.setMaxWidth(50);
		Button btn = new Button();
		btn.setText("Enter");
		
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	label2.setText("");
            	int savedValue = Integer.parseInt(value.getText());
                if ((savedValue >= 0 && savedValue <= 25)) {
                    circle.setFill(Color.RED);                    
                } 
                else if ((savedValue >= 26 && savedValue <= 50)) {
                    circle.setFill(Color.PURPLE);
                }
                else if ((savedValue >= 51 && savedValue <= 75)) {
                    circle.setFill(Color.GREEN);
                }
                else if ((savedValue >= 76 && savedValue <= 99)) {
                    circle.setFill(Color.YELLOW);
                }
                else {
                    label2.setText("You have not entered a valid value");
                }
            }
        });
		
        
		root.getChildren().add(label1);
		root.getChildren().add(value);
		root.getChildren().add(btn);
		root.getChildren().add(label2);
		root.getChildren().add(circle);
		
		
		
		//creates a new scene object with parameters layout, 300 and 250
		Scene scene = new Scene(root, 400, 400);
		//primary stage sets the scene to the scene object
		primaryStage.setScene(scene);
		//shows the primary stage
		primaryStage.show();
		
	}
	
}
