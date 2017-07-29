//Importing Java Libraries
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
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
		
	int yellowValue;
	int redValue;
	Label result = new Label("");
	int yellowValue3;
	int redValue3;
	Label results3 = new Label("");
	
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
			
			RadioButton rb3 = new RadioButton("Surf Forecast");
			rb3.setToggleGroup(group);
			
			//Creating button
			Button btn1 = new Button();
			btn1.setText("Submit");
			
			//Adding Elements to layout
			root.getChildren().add(label1);
			root.getChildren().add(rb1);
			root.getChildren().add(rb2);
			root.getChildren().add(rb3);
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
			
			s.next();
			s.next();
			
			String valC = s.next();
			s.close();
			
			//Values needed from web site
			int outflow = Integer.parseInt(valA);
			int inflow = Integer.parseInt(valB);
			int diff = outflow - inflow;
			double temp = Double.parseDouble (valC);
			
			//Conversion to values 0-99
			double tempSmall=-10, tempBig=3000, realSmall=0, realBig=99;
			double tempa =  (((double)diff-tempSmall)/(tempBig-tempSmall))*(realBig-realSmall)+realSmall;
			int newDiff =  (int)tempa;
			
			//Value printed
			System.out.println("Outflow: " + outflow);
			System.out.println("Inflow: " + inflow);
			System.out.println("Difference: " + newDiff);
			System.out.println("Water Temp: " + temp);
			
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
						
			//Setting Brightness
			ColorAdjust brightness = new ColorAdjust();
			brightness.setBrightness(0);
			
			if ((temp > 80 )) {
			brightness.setBrightness(-.8);
			} else if (temp > 65 ) {
			brightness.setBrightness(0);
			} else if (temp <=65) {
			brightness.setBrightness(-.8);
			} 
			circle.setEffect(brightness);
			
			//List of Labels 
			Label info = new Label("Outflow is currently at: " + outflow);
			Label info2 = new Label("Inflow is currently at: " + inflow);
			
			//List of TextField
	        TextField ans1 = new TextField();
	        
			//Create a Button
			Button btn = new Button();
			btn.setText("Main Menu");
	        
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
	            if( type.contains("Incident")){
	            	incident++;
	            }
	            else if( type.contains("Congestion")){
	            	congestion++;
	            }
	            else if( type.contains("Construction")){
	            	construction++;
	            }
	            else if( type.contains("Weather")) {
	            	weather++;
	            }
	            else if( type.contains("Special Events")){
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
			
			if(yellowValue == 0){
				yellowValue = 1;
			}
			if(redValue == 0){
				redValue = 3;
			}
			
			//Changing colors to be ambient
			if(incident<yellowValue){
				circle1.setFill(Color.GREEN);
			}
			else if(incident < redValue){
				circle1.setFill(Color.YELLOW);
			}
			else{
				circle1.setFill(Color.RED);
			}
			
			if(congestion < yellowValue){
				circle2.setFill(Color.GREEN);
			}
			else if(congestion <redValue){
				circle2.setFill(Color.YELLOW);
			}
			else{
				circle2.setFill(Color.RED);
			}
			
			if(construction < yellowValue){
				circle3.setFill(Color.GREEN);
			}
			else if(construction < redValue){
				circle3.setFill(Color.YELLOW);
			}
			else{
				circle3.setFill(Color.RED);
			}
			
			if(weather < yellowValue){
				circle4.setFill(Color.GREEN);
			}
			else if(weather < redValue){
				circle4.setFill(Color.YELLOW);
			}
			else{
				circle4.setFill(Color.RED);
			}
			
			if(specialEvents < yellowValue){
				circle5.setFill(Color.GREEN);
			}
			else if(specialEvents < redValue){
				circle5.setFill(Color.YELLOW);
			}
			else{
				circle5.setFill(Color.RED);
			}

			//Create a Button
			Button btn2 = new Button();
			btn2.setText("Main Menu");
			
			//adding circles to Hbox
			set1.getChildren().add(a);
			set1.getChildren().add(b);
			set1.getChildren().add(c);
			set2.getChildren().add(d);
			set2.getChildren().add(e);
			
			//adding elements to scene
			root2.getChildren().add(set1);
			root2.getChildren().add(set2);
			
			Scene scene2 = new Scene(root2, 400, 700);
			
			
			//Incidents Settings
			
			//set layout of program to VBox
			VBox root2a = new VBox(20);
			root2a.setAlignment(Pos.CENTER);
			Stage stage2a = new Stage();
			
			//set layout of program to VBox
			VBox desc2 = new VBox();
			desc2.setAlignment(Pos.CENTER);
			
			//Sets the program title
			stage2a.setTitle("Settings");
			
			//Creating Labels
			Label desc2a = new Label("By default, all the circles will be green when");
			Label desc2b = new Label("there are no incidents, congestions, etc.");
			Label desc2c = new Label("If there is 1 or 2 instances, it will be yellow.");
			Label desc2d = new Label("Any more will warrant a red circle.");
			Label yellowLabel = new Label("When would you like to see the YELLOW circle?");
			Label redLabel = new Label("When would you like to see the RED circle?");
			
			//adding elements to desc2
			desc2.getChildren().add(desc2a);
			desc2.getChildren().add(desc2b);
			desc2.getChildren().add(desc2c);
			desc2.getChildren().add(desc2d);

			
			//Creating TextFields
			TextField yellow = new TextField();
			yellow.setPromptText("e.i. 1");
			yellow.setMaxWidth(100);
			TextField red = new TextField();
			red.setPromptText("e.i. 4");
			red.setMaxWidth(100);
			
			Button btn2b = new Button();
			btn2b.setText("Submit");
			
			//adding elements to scene
			root2a.getChildren().add(desc2);
			root2a.getChildren().add(yellowLabel);
			root2a.getChildren().add(yellow);
			root2a.getChildren().add(redLabel);
			root2a.getChildren().add(red);
			root2a.getChildren().add(btn2b);
			
			root2.getChildren().add(root2a);
			root2.getChildren().add(result);
			root2.getChildren().add(btn2);

			
			
			//Surf Forecast Page
			
			//set layout of program to VBox
			VBox root3 = new VBox(20);
			root3.setAlignment(Pos.CENTER);
			Stage stage3 = new Stage();
			
			//Sets the program title
			stage2.setTitle("Surf Forecast");
			
			//use JSoup to grab values from web site
			Document doc3 = Jsoup.connect("http://www.surfline.com/surf-report/ala-moana-bowls-oahu_5538/").get();
			String range = doc3.select("#observed-wave-range").text();
	        BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
	        out.write(range);  
	        //Replace with the string im trying to write
	        out.close();
	        BufferedReader in = new BufferedReader(new FileReader("test.txt"));
	        System.out.println("surf report: " + range);
	        Circle c1 = new Circle(60);
	        VBox forecast1 = new VBox(10);
	        forecast1.setAlignment(Pos.CENTER);
	        Label f1 = new Label("Ala Moana Bowl");
	        forecast1.getChildren().add(f1);
	        forecast1.getChildren().add(c1);
	        
	        switch (range) {
	          case "2-3 ft":
	          case "3-4 ft":

	              c1.setFill(Color.DARKGREEN);
	              break;
	          case "4-5 ft":
	          case "5-6 ft":
	          case "7-8 ft":

	              c1.setFill(Color.YELLOW);
	              break;
	          case "9-10 ft":
	          case "10-11 ft":
	          case "12+ ft":

	              c1.setFill(Color.RED);
	              break;
	          case "FLAT":

	              c1.setFill(Color.WHITE);
	              break;
	          default:
	              break;
	        }
	        
	        root3.getChildren().add(forecast1);
	        Button btn4 = new Button("Main Menu");
	       
	        VBox desc3 = new VBox();
	        desc3.setAlignment(Pos.CENTER);
	        
			//Creating Labels
			Label desc3a = new Label("By default, if the surf is between 2-4 ft,");
			Label desc3b = new Label("the circle will be green. If the surf is ");
			Label desc3c = new Label("between 4-6 ft, the circle will be yellow.");
			Label desc3d = new Label("Anything higher will be red. If there is no surf");
			Label desc3e = new Label("then the circle will be white.");
			Label yellowLabel3 = new Label("When would you like to see the YELLOW circle?");
			Label redLabel3 = new Label("When would you like to see the RED circle?");
			
			//Creating TextFields
			TextField yellow3 = new TextField();
			yellow3.setMaxWidth(100);
			yellow3.setPromptText("e.i. 1");
			yellow3.setMaxWidth(100);
			TextField red3 = new TextField();
			red3.setMaxWidth(100);
			red3.setPromptText("e.i. 4");
			red3.setMaxWidth(100);
			
			Button btn3 = new Button("Submit");
			
			
			//adding elements to desc2
			desc3.getChildren().add(desc3a);
			desc3.getChildren().add(desc3b);
			desc3.getChildren().add(desc3c);
			desc3.getChildren().add(desc3d);
			desc3.getChildren().add(desc3e);
			
			root3.getChildren().add(desc3);
			root3.getChildren().add(yellowLabel3);
			root3.getChildren().add(yellow3);
			root3.getChildren().add(redLabel3);
			root3.getChildren().add(red3);
			root3.getChildren().add(btn3);
			root3.getChildren().add(results3);
	        root3.getChildren().add(btn4);
			
			//creates a new scene object with parameters layout of 400, 500
			Scene scene3 = new Scene(root3, 350, 600);
			
			
			
			//Button Actions

	        btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	primaryStage.setScene(scene);
	            	primaryStage.show();
	            }
	        });
	        
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
	            	if(rb3.isSelected() == true){
	            		primaryStage.setScene(scene3);
	            		primaryStage.show();
	            	}
	            }
	        });
	        
	        btn2.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	primaryStage.setScene(scene);
	            	primaryStage.show();
	            }
	        });

	        
	        btn2b.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	yellowValue = Integer.parseInt(yellow.getText());
	            	redValue = Integer.parseInt(red.getText());
	            	result.setText("Changes Completed!"); 
	            	primaryStage.show();
	            }
	        });
	        
	        btn3.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	yellowValue3 = Integer.parseInt(yellow.getText());
	            	redValue3 = Integer.parseInt(red.getText());
	            	results3.setText("Changes Completed!"); 
	            	primaryStage.show();
	            }
	        });
	        
	        btn4.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	primaryStage.setScene(scene);
	            	primaryStage.show();
	            }
	        });
			
		}	
	}
