   import java.io.*;
   import java.text.*;
   import java.util.*;
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import javax.swing.event.*;
   import javax.swing.text.*;
   import javax.swing.border.*;

 
 class MainMain
 {    
		 public static void main(String args[]){
		Snake snakeNew = new Snake();
         final JFrame jFrame = new SnakeMain(snakeNew); // Main frame
         jFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
         jFrame.pack();
         jFrame.setSize(700,700);
         jFrame.setVisible(true); // Show frame
        
      
      }
}
 
