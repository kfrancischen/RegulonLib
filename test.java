import java.awt.*;
import java.io.*;
import javax.swing.*;
public class test {
	public static void main(String[] args) {  
        // TODO Auto-generated method stub  
   
        Runnable runner = new Runnable() {  
            public void run() {  
 
   
                Box verticalBox = Box.createVerticalBox();  
                verticalBox.add(new JLabel("Top"));  
                verticalBox.add(new JTextField("Middle"));  
                verticalBox.add(new JButton("Bottom"));  
 
   
                JFrame horizontalFrame = new JFrame("Horizontal");  
                horizontalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
   
                Box horizontalBox = Box.createHorizontalBox();  
                horizontalBox.add(new JLabel("Left"));  
                horizontalBox.add(new JTextField("Middle"));  
                horizontalBox.add(new JButton("Right"));  
                horizontalFrame.add(horizontalBox, BorderLayout.EAST);  
                horizontalFrame.add(verticalBox,BorderLayout.WEST);
                horizontalFrame.setSize(300, 300);  
                horizontalFrame.setVisible(true);  
            }  
        };  
   
        EventQueue.invokeLater(runner);  
    }  
}
