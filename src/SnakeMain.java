import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;




public class SnakeMain extends JFrame{
	protected JButton start;
	protected JButton pause;
	protected JButton resume;
	protected JButton help;
	protected JTextField currentApple;
	protected JLabel currentAppleLabel;
	protected JLabel colon;
	protected JLabel applesEatenLabel;
	protected JTextField currentMiliseconds;
	protected JTextField currentSeconds;
	protected JTextField currentMinutes;
	protected JTextField applesEaten;
	protected JLabel currentTimeLabel;
	protected Snake bobby;
	protected ActionHandler action; 
	
public SnakeMain(Snake bob){    
	 super("Snake Game");
	this.setPreferredSize (new Dimension(600, 800));
	action = new ActionHandler();
	bobby = bob;
	start = new JButton("Restart");
	pause = new JButton("Pause");
	resume = new JButton("Resume");
	help = new JButton("Help");
	currentApple = new JTextField(15);
	currentSeconds = new JTextField(3);
	currentMinutes = new JTextField(3);
	applesEaten = new JTextField(3);
	currentAppleLabel = new JLabel("Current Apple Type:");
	applesEatenLabel = new JLabel("Apples Eaten:");
	currentTimeLabel = new JLabel("Time Played:");
	colon = new JLabel(":");
    
    start.addActionListener(action);
    pause.addActionListener(action);
    resume.addActionListener(action);
    help.addActionListener(action);
    
    JPanel buttonPane = new JPanel();
    buttonPane.add(start);
    buttonPane.add(pause);
    buttonPane.add(resume);
    buttonPane.add(help);
    buttonPane.setBackground(Color.blue);
    JPanel applePane = new JPanel();
    applePane.add(currentAppleLabel);
    applePane.add(currentApple);
    applePane.add(applesEatenLabel);
    applePane.add(applesEaten);
    applePane.add(currentTimeLabel);
    applePane.add(currentMinutes);
    applePane.add(colon);
    applePane.add(currentSeconds);
    applePane.setBackground(Color.green);
    JPanel completePane = new JPanel();
    completePane.setLayout(new BorderLayout ());
    Border redline = BorderFactory.createLineBorder(Color.red);
    completePane.setBorder(redline);
    completePane.add(applePane,BorderLayout.CENTER);
    completePane.add(buttonPane,BorderLayout.SOUTH);
    completePane.setSize(new Dimension(600, 200));
    bobby.setBorder(redline);
    this.setLayout (new BorderLayout ());
    this.add(bobby,BorderLayout.NORTH);
    this.add(completePane,BorderLayout.SOUTH);
 
    
    Timer textTimer = new Timer(1000, new textTimerListener());
    textTimer.start(); 
	}

class textTimerListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		currentApple.setText(bobby.appleText);
		applesEaten.setText(Integer.toString(bobby.applesCount));
		currentSeconds.setText(Integer.toString(bobby.seconds));
		currentMinutes.setText(Integer.toString(bobby.minutes));
	}
}

class ActionHandler implements ActionListener{
	public void actionPerformed(ActionEvent event){
	  
		if(event.getActionCommand().equals("Restart")){
			bobby.restart();
			bobby.requestFocusInWindow(); 
    	}  
		if(event.getActionCommand().equals("Pause")){
			bobby.pause();
			bobby.requestFocusInWindow();
    	}  
		if(event.getActionCommand().equals("Resume")){
			bobby.resume();
			bobby.requestFocusInWindow();
    	}  
		if(event.getActionCommand().equals("Help")){
			JOptionPane.showMessageDialog(null,"Use the Arrow Keys to move the Snake around the Screen. \nTry to collect the most Apples in the Shortest amount of Time before the Snake Eats itself or Goes out of Bounds \nPress Restart to Restart the Game. Pause to Pause the Game. And Resume to Resume the Game.");
    	}  
	}
}

}