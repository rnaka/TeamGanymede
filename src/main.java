//Importing Java Libraries
import java.awt.Font;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.event.*;
import javafx.geometry.Pos;


//JavaFX class
public class main extends Application {
	
	//main method
	public static void main(String[] args) throws IOException {
		
		//launches the GUI
		launch(args);
	}
		
		//start method 
		public void start(Stage primaryStage) throws Exception{
		     
			//use JSoup to grab outflow and inflow values from website
			Document doc = Jsoup.connect("http://www.spk-wc.usace.army.mil/fcgi-bin/hourly.py?report=pnf").get();
			Elements body = doc.select("pre");
			String str = body.text();
			Scanner s = new Scanner(str);
			for (int i = 0; i < 607; i++){
				s.next();
			}
			String valA = s.next();
			String valB = s.next();
			s.close();
			
			//Values needed from website
			int outflow = Integer.parseInt(valA);
			int inflow = Integer.parseInt(valB);
			int diff = outflow - inflow;
			
			//Conversion to values 0-99
			double tempSmall=-10, tempBig=3000, realSmall=0, realBig=99;
			double tempa =  (((double)diff-tempSmall)/(tempBig-tempSmall))*(realBig-realSmall)+realSmall;
			int newDiff =  (int)tempa;
			
			//Value printed
			System.out.println("Outflow: " + outflow);
			System.out.println("Inflow: " + inflow);
			System.out.println("Difference: " + newDiff);
			
			//Sets the program title
			primaryStage.setTitle("Pine Flat");
			
			//set layout of program to VBox
			VBox root = new VBox();
			root.setSpacing(10);
			root.setAlignment(Pos.CENTER);
			
			//Create a Circle to represent outflow vs inflow
			Circle circle = new Circle(70);
			if (outflow > inflow){
				circle.setFill(Color.LIMEGREEN);
			}
			else{
				if(newDiff < 75){
					circle.setFill(Color.YELLOW);
				}
				else{
					circle.setFill(Color.RED);
				}
			}
						
			//List of Labels 
	        Label label3 = new Label("Max Value: ");
			Label label4 = new Label("User Settings");
			Label info = new Label("Outflow is currently at: " + outflow);
			Label info2 = new Label("Inflow is currently at: " + inflow);
			
			//List of TextField
	        TextField ans1 = new TextField();

			//Settings Area
	        HBox max = new HBox();
	        max.setSpacing(20);
	        max.setAlignment(Pos.CENTER);
	        
			//Create a Button
			Button btn = new Button();
			btn.setText("Settings");
			
			//Button Action
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	
	            }
	        });
			
	        
	        //Adding elements to max layout
	        max.getChildren().add(label3);
	        max.getChildren().add(ans1);
	        
	        //Adding elements to root layout
	        root.getChildren().add(info);
	        root.getChildren().add(info2);
			root.getChildren().add(circle);
			root.getChildren().add(label4);
			root.getChildren().add(max);
			root.getChildren().add(btn);
			
			//creates a new scene object with parameters layout of 400, 500
			Scene scene = new Scene(root, 300, 400);
			
			//primary stage sets the scene to the scene object
			primaryStage.setScene(scene);
			
			//shows the primary stage
			primaryStage.show();
		}	
	}
