//Importing Java Libraries
import java.awt.Font;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
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
			
	
			//Main Window
			
			//set layout of program to VBox
			VBox root = new VBox();
			root.setSpacing(10);
			root.setAlignment(Pos.CENTER);
			
			//Creating Labels
			Label label1 = new Label("Which Information Would You Like To See?");			
			
			//creating group for radio buttons
			final ToggleGroup group = new ToggleGroup();
			
			//creating radio buttons
			RadioButton rb1 = new RadioButton("Pine Flat Inflow/Outflow");
			rb1.setToggleGroup(group);
			rb1.setSelected(true);
			
			RadioButton rb2 = new RadioButton("Incidents In Hawaii");
			rb2.setToggleGroup(group);
			
			//Creating button
			Button btn1 = new Button();
			btn1.setText("Submit");
			
			//Adding Elements to layout
			root.getChildren().add(label1);
			root.getChildren().add(rb1);
			root.getChildren().add(rb2);
			root.getChildren().add(btn1);
			
			//creates a new scene object with parameters layout of 400, 500
			Scene scene = new Scene(root, 300, 400);
			
			//primary stage sets the scene to the scene object
			primaryStage.setScene(scene);
			
			//shows the primary stage
			primaryStage.show();
			
			
			
			//Pine Flat Window
			
			//set layout of program to VBox
			VBox root1 = new VBox();
			root1.setSpacing(10);
			root1.setAlignment(Pos.CENTER);
			Stage stage1 = new Stage();
			
			//use JSoup to grab out-flow and in-flow values from web site
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
			
			//Values needed from web site
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
			stage1.setTitle("Pine Flat");
			
			//Create a Circle to represent out-flow vs in-flow
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
			Label info = new Label("Outflow is currently at: " + outflow);
			Label info2 = new Label("Inflow is currently at: " + inflow);
			
			//List of TextField
	        TextField ans1 = new TextField();
	        
			//Create a Button
			Button btn = new Button();
			btn.setText("Main Menu");
			
			//Button Action
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	primaryStage.setScene(scene);
	            	primaryStage.show();
	            }
	        });
	        
	        //Adding elements to root layout
	        root1.getChildren().add(info);
	        root1.getChildren().add(info2);
			root1.getChildren().add(circle);
			root1.getChildren().add(btn);
			
			//creates a new scene object with parameters layout of 400, 500
			Scene scene1 = new Scene(root1, 300, 400);
			
			
			//Incidents Page
			
			//set layout of program to VBox
			VBox root2 = new VBox(20);
			root2.setAlignment(Pos.CENTER);
			Stage stage2 = new Stage();
			
			//Sets the program title
			stage2.setTitle("Incidents");
			
			//use JSoup to grab values from web site
			Document doc2 = Jsoup.connect("http://goakamai.org/icx/pages/incidentlist.aspx").get();
			Elements elements = doc2.getElementsByTag("IMG");
			
			//Values retrieved from website
			int incident = 0;
			int congestion = 0;
			int construction = 0;
			int weather = 0;
			int specialEvents = 0;
			
			for(int i=7;i<elements.size()-4;i++)
	        {
				String type = elements.get(i).attr("title");
	            if( type == "Incident"){
	            	incident++;
	            }
	            else if( type == "Congestion"){
	            	congestion++;
	            }
	            else if( type == "Construction"){
	            	construction++;
	            }
	            else if( type == "Weather") {
	            	weather++;
	            }
	            else if( type == "Special Events"){
	            	specialEvents++;
	            }
	        }
			
			//set layout of Hboxes
			HBox set1 = new HBox(20);
			set1.setAlignment(Pos.CENTER);
			
			//set layout of Hboxes
			HBox set2 = new HBox(20);
			set2.setAlignment(Pos.CENTER);
			
			//labels
			Label l1 = new Label("Incidents");
			Label l2 = new Label("Congestions");
			Label l3 = new Label("Construction");
			Label l4 = new Label("Weather");
			Label l5 = new Label("Special Events");
			
			//Creating circles for the incident types
			Circle circle1 = new Circle(40);
			Circle circle2 = new Circle(40);
			Circle circle3 = new Circle(40);
			Circle circle4 = new Circle(40);
			Circle circle5 = new Circle(40);
			
			//VBox Sets
			VBox a = new VBox(10);
			a.setAlignment(Pos.CENTER);
			VBox b = new VBox(10);
			b.setAlignment(Pos.CENTER);
			VBox c = new VBox(10);
			c.setAlignment(Pos.CENTER);
			VBox d = new VBox(10);
			d.setAlignment(Pos.CENTER);
			VBox e = new VBox(10);
			e.setAlignment(Pos.CENTER);
			
			a.getChildren().add(l1);
			a.getChildren().add(circle1);
			b.getChildren().add(l2);
			b.getChildren().add(circle2);
			c.getChildren().add(l3);
			c.getChildren().add(circle3);
			d.getChildren().add(l4);
			d.getChildren().add(circle4);
			e.getChildren().add(l5);
			e.getChildren().add(circle5);
			
			//Changing colors to be ambient
			if(incident==0){
				circle1.setFill(Color.GREEN);
			}
			else if(incident < 2){
				circle1.setFill(Color.YELLOW);
			}
			else{
				circle1.setFill(Color.RED);
			}
			
			if(congestion==0){
				circle2.setFill(Color.GREEN);
			}
			else if(congestion < 2){
				circle2.setFill(Color.YELLOW);
			}
			else{
				circle2.setFill(Color.RED);
			}
			
			if(construction==0){
				circle3.setFill(Color.GREEN);
			}
			else if(construction < 2){
				circle3.setFill(Color.YELLOW);
			}
			else{
				circle3.setFill(Color.RED);
			}
			
			if(weather==0){
				circle4.setFill(Color.GREEN);
			}
			else if(weather < 2){
				circle4.setFill(Color.YELLOW);
			}
			else{
				circle4.setFill(Color.RED);
			}
			
			if(specialEvents ==0){
				circle5.setFill(Color.GREEN);
			}
			else if(specialEvents < 2){
				circle5.setFill(Color.YELLOW);
			}
			else{
				circle5.setFill(Color.RED);
			}

			//Create a Button
			Button btn2 = new Button();
			btn2.setText("Main Menu");
			
			//Button Action
	        btn2.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	primaryStage.setScene(scene);
	            	primaryStage.show();
	            }
	        });
			
			//adding circles to Hbox
			set1.getChildren().add(a);
			set1.getChildren().add(b);
			set2.getChildren().add(c);
			set2.getChildren().add(d);
			
			//adding elements to scene
			root2.getChildren().add(set1);
			root2.getChildren().add(set2);
			root2.getChildren().add(e);
			root2.getChildren().add(btn2);
			
			Scene scene2 = new Scene(root2, 300, 500);
			
			//Button Action
	        btn1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	if (rb1.isSelected() == true){
	            		primaryStage.setScene(scene1);
	            		primaryStage.show();
	            	}
	            	if(rb2.isSelected() == true){
	            		primaryStage.setScene(scene2);
	            		primaryStage.show();
	            	}
	            }
	        });
		}	
	}
