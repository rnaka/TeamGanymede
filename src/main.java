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
			     
				//
				Document doc = Jsoup.connect("http://www.spk-wc.usace.army.mil/fcgi-bin/hourly.py?report=pnf").get();
				Elements body = doc.select("pre");

				String str = body.text();
				
				Scanner s = new Scanner(str);
				
				for (int i = 0; i < 619; i++){
					s.next();
				}
				String valA = s.next();
				String valB = s.next();
				System.out.println("Outflow: " + valA);
				int outflow = Integer.parseInt(valA);
				System.out.println("Inflow: " + valB);
				int inflow = Integer.parseInt(valB);
				
				s.close();
				
				//Sets the program title
				primaryStage.setTitle("Pine Flat Outflow and Inflow");
				
				//set layout of program to VBox
				VBox root = new VBox();
				root.setSpacing(20);
				root.setAlignment(Pos.CENTER);
				
				//Create a Circle to represent outflow vs inflow
				Circle circle = new Circle(70);
				if (outflow > inflow){
					circle.setFill(Color.LIMEGREEN);
				}
				
				//Create a Circle to represent lake percentage fullness
				Circle circle2 = new Circle(70);
				if (outflow > inflow){
					circle.setFill(Color.LIMEGREEN);
				}
				
				//List of Labels 
				Label label1 = new Label("Outflow vs Inflow");
				Label label2 = new Label("");
				
				//Create a Button
				Button btn = new Button();
				btn.setText("Refresh");
				
				//Button Action
		        btn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	
		            }
		        });
				
		        //Adding elements to layout
				root.getChildren().add(label1);
				root.getChildren().add(circle);
				root.getChildren().add(circle2);
				root.getChildren().add(btn);
				
				//creates a new scene object with parameters layout of 400, 500
				Scene scene = new Scene(root, 300, 500);
				//primary stage sets the scene to the scene object
				primaryStage.setScene(scene);
				//shows the primary stage
				primaryStage.show();
			}	
		}
