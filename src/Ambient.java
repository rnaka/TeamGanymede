
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JTextField;


public class Ambient extends JFrame {
   
  JSlider redSlider, greenSlider, blueSlider, opacitySlider;
  JLabel redLabel, greenLabel, blueLabel, opacityLabel, string;
  JPanel colorPanel, sliders, labels;
  //JTextField input= new JTextField("Enter Valuesfrom 0-99", 30);
  //tried to make text work but ran out of time
  
  public Ambient(){
      redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0); //change last vaule to user input
      greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
      blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
      opacitySlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
      

      redLabel = new JLabel("Red: 0");
      greenLabel = new JLabel("Green: 0");
      blueLabel = new JLabel("Blue: 0");
      opacityLabel = new JLabel("Brightness: 99");
      string = new JLabel();
      
      events e = new events();
      redSlider.addChangeListener(e);
      greenSlider.addChangeListener(e);
      blueSlider.addChangeListener(e);
      opacitySlider.addChangeListener(e);
   
      
      colorPanel = new JPanel();
      colorPanel.setBackground(Color.BLACK);
      
      //container that sets everything in a grid
      
      Container pane = this.getContentPane();
      pane.setLayout(new GridLayout(1, 3, 2, 2));
      
      sliders = new JPanel();
      labels = new JPanel();
      
      //pane.add(input);
      pane.add(sliders);
      pane.add(labels);
      pane.add(colorPanel);
      

      
      //layout of sliders
      sliders.setLayout(new GridLayout(4, 1, 2, 2));
      sliders.add(redSlider);
      sliders.add(greenSlider);
      sliders.add(blueSlider);
      sliders.add(opacitySlider);
      
      //layout of labels
      labels.setLayout(new GridLayout(4, 1, 2, 2));
      labels.add(redLabel);
      labels.add(greenLabel);
      labels.add(blueLabel);
      labels.add(opacityLabel);
      
  }
  
  public class events implements ChangeListener{
      public void stateChanged(ChangeEvent e){
          int r =redSlider.getValue();
          int g =greenSlider.getValue();
          int b =blueSlider.getValue();
          int o =opacitySlider.getValue();
          
          redLabel.setText("Red: " + r); //minus some vaule to get under 99
          greenLabel.setText("Green: " + g);
          blueLabel.setText("Blue: " + b);
          opacityLabel.setText("Brightness: " + o);
          
          colorPanel.setBackground(new Color(r,g,b,o));
      }
  }

  
   public static void main (String[] args) 
    {
        Ambient ambient = new Ambient();
        ambient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ambient.setVisible(true);
        ambient.setTitle("Ambient");
        ambient.setSize(800, 150);
       // ambient.paint(null);
    }
    
   
}
