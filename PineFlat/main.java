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
import javafx.scene.effect.*;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//JavaFX class
public class main extends Application {
	
	//main method
	public static void main(String[] args){
		
		//launches the GUI
		launch(args);
	}
	
	//start method 
	public void start(Stage primaryStage) throws Exception{
		
		//Sets the program title
		primaryStage.setTitle("Pine Flat Outflow and Inflow");
		
		//set layout of program to VBox
		VBox root = new VBox();
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		
		//Create a Circle to represent ambient object
		Circle circle = new Circle(200,200,100,Color.GRAY);
		
		//List of Labels 
		Label label1 = new Label("Enter a value between 0 and 99 for the color:");
		Label label2 = new Label("Enter a value between 0 and 99 for the brightness:");

		//JSoup
		Document doc = Jsoup.connect("http://www.spk-wc.usace.army.mil/fcgi-bin/hourly.py?report=pnf").get();
		doc.select("p").forEach(System.out::println);
		
		//Create a Button
		Button btn = new Button();
		btn.setText("Enter");
		
		//Button Action
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            }
        });
		
        //Adding elements to layout
		root.getChildren().add(label1);
		root.getChildren().add(label2);
		root.getChildren().add(btn);
		root.getChildren().add(circle);
		
		
		
		//creates a new scene object with parameters layout of 400, 500
		Scene scene = new Scene(root, 400, 500);
		//primary stage sets the scene to the scene object
		primaryStage.setScene(scene);
		//shows the primary stage
		primaryStage.show();
	}	
}
