
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JTextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Ambient extends JFrame {
   
  JSlider redSlider, greenSlider, blueSlider, opacitySlider;
  JLabel redLabel, greenLabel, blueLabel, opacityLabel;
  JPanel colorPanel, sliders, labels;
  //JTextField script= new JTextField(30);
  //String input = script.getText();
  
  //JTextField  input = new JTextField(TextIO.getInt());
  
  
  public Ambient(){
      
      redLabel = new JLabel("Red: 0");
      greenLabel = new JLabel("Green: 0");
      blueLabel = new JLabel("Blue: 0");
      opacityLabel = new JLabel("Brightness: 99");
      colorPanel = new JPanel();
      Container pane = this.getContentPane();
      pane.setLayout(new GridLayout(1, 3, 2, 2));
      labels = new JPanel();
      pane.add(labels);
      pane.add(colorPanel);
      labels.setLayout(new GridLayout(4, 1, 2, 2));
      labels.add(redLabel);
      labels.add(greenLabel);
      labels.add(blueLabel);
      labels.add(opacityLabel);
      
  }
  public int getData() throws IOException{
      String url = "http://www.spk-wc.usace.army.mil/fcgi-bin/hourly.py?report=pnf";
        Document document = Jsoup.connect(url).get();
        int diff=0, outnum, innum;
        String question = document.select("#content").text();
        BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
        out.write(question);  //Replace with the string im trying to write  
        out.close();
        BufferedReader in = new BufferedReader(new FileReader("test.txt"));
        //String line;
        int lineNum=0;
            Scanner scanner = new Scanner(in);
            while(scanner.hasNextLine())
                {
                    String line =scanner.nextLine();
                    lineNum++;
                    if (lineNum==54){ //one line before last hour because sometime data is not availible
        String outflow = line.substring(42,46);
        String inflow = line.substring(51,55);
        outnum = Integer.parseInt(outflow);
        innum = Integer.parseInt(inflow);
         diff= outnum-innum;
      System.out.println("outflow: " + outnum);
      System.out.println("inflow: " + innum);
      System.out.println("difference: " + diff);
                    }
                }
                in.close();

    return diff;
  }

      public void stateChanged(int num){
          int r=0, g=0, b=0, o=0;
          double tempSmall=-10, tempBig=3000, realSmall=0, realBig=99, newNum;
           newNum =  ((num-tempSmall)/(tempBig-tempSmall))*(realBig-realSmall)+realSmall ;
           int yum=  (int)newNum;
                 System.out.print("conversion:" + yum);
          if((yum>=0 && yum <= 25)){
               r =255;
               o=64;


          }
          else if((yum>=26 && yum <= 50)){
               g =255;
               o=128;


          }
           else if((yum>=51 && yum <= 75)){
               b =255;
               o=192;


          }
           else if((yum>=76 && yum <= 99)){
               r =255;
               b=255;
               o=255;
          }
          else
               System.out.print("You did not enter a valid number.");
          redLabel.setText("Red: " + r);
          greenLabel.setText("Green: " + g);
          blueLabel.setText("Blue: " + b);
          opacityLabel.setText("Brightness: " + o);
          colorPanel.setBackground(new Color(r,g,b,o));
      }
  

  
   public static void main (String[] args) throws IOException 
    {
        Ambient ambient = new Ambient();
        int data = ambient.getData();
        ambient.stateChanged( data );
        ambient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ambient.setVisible(true);
        ambient.setTitle("Ambient");
        ambient.setSize(300, 150);
     
    }
}
