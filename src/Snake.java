import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



import javax.swing.Timer; 


public class Snake extends JPanel{
	protected final int width = 650;
	protected final int height = 600;
	protected final int dot_size=20;
	protected final int all_dots=(width*height)/(dot_size*dot_size);
	
	protected final int rand_pos=29;
	protected int delay=120;
	
	protected int x[]=new int[all_dots];
	protected int y[]=new int[all_dots];
	
	protected int dots;
	protected int apple_x;
	protected int apple_y;
	
	protected boolean left=false;
	protected boolean right=true;
	protected boolean up=false;
	protected boolean down=false;
	protected boolean inGame=true;
	
	protected int countLong=0;
	protected int changeCount=0;
	
	protected boolean iSlow=false;
	protected boolean iFast=false;
	protected boolean iLonger=false;
	protected boolean iShorter=false;
	protected boolean iReverse=false;
	protected boolean iControl=false;
	protected boolean iChange=false;
	
	protected Timer gameTimer;
	protected Timer slowTimer;
	protected Timer fastTimer;
	
	protected Image head;
	protected Image normal;
	protected Image body;
	
	protected Image slow;
	protected Image fast;
	protected Image longer;
	protected Image shorter;
	protected Image reverse;
	protected Image controls;
	protected Image change;

	protected Image apple;
	
	protected String appleText;
	protected int applesCount;
	protected int seconds;
	protected int minutes;
	protected Timer timertimer;
	
	public Snake(){
			System.out.println("Starting");
	        addKeyListener(new Keys());
	        
	        setBackground(Color.BLACK);
	        setPreferredSize (new Dimension(650, 600));

	        ImageIcon iid = new ImageIcon(this.getClass().getResource("snake.jpg"));
	        body = iid.getImage();

	        ImageIcon iia = new ImageIcon(this.getClass().getResource("normal.jpg"));
	        normal = iia.getImage();

	        ImageIcon iih = new ImageIcon(this.getClass().getResource("snake.jpg"));
	        head = iih.getImage();
	        
	        ImageIcon iiex = new ImageIcon(this.getClass().getResource("change.jpg"));
	        change = iiex.getImage();
	        
	        ImageIcon iicc = new ImageIcon(this.getClass().getResource("controls.jpg"));
	        controls = iicc.getImage();

	        ImageIcon iif = new ImageIcon(this.getClass().getResource("fast.jpg"));
	        fast = iif.getImage();
	        
	        ImageIcon iil = new ImageIcon(this.getClass().getResource("longer.jpg"));
	        longer = iil.getImage();
	        
	        ImageIcon iir = new ImageIcon(this.getClass().getResource("reverse.jpg"));
	        reverse = iir.getImage();
	        
	        ImageIcon iis = new ImageIcon(this.getClass().getResource("shorter.jpg"));
	        shorter = iis.getImage();
	        
	        ImageIcon iisw = new ImageIcon(this.getClass().getResource("slow.jpg"));
	        slow = iisw.getImage();
	        
	        setFocusable(true);
	        
	        dots = 7;

	        for (int z = 0; z < dots; z++) {
	            x[z] = 200 - z*20;
	            System.out.println("x["+z+"]="+x[z]);
	            y[z] = 100;
	            System.out.println("y["+z+"]="+y[z]);
	        }

	        locateApple();

	        gameTimer = new Timer(delay, new gameTimerListener());
	        gameTimer.start(); 
	        timertimer = new Timer(1000, new timerTimerListener());
	        timertimer.start();
		} 
	
	public void pause(){
		gameTimer.stop();
	}	
	public void resume(){
		gameTimer.start();
	}
	public void restart(){
		dots = 7;
        for (int z = 0; z < dots; z++) {
            x[z] = 200 - z*20;
            System.out.println("x["+z+"]="+x[z]);
            y[z] = 100;
            System.out.println("y["+z+"]="+y[z]);
        }
        locateApple();
        gameTimer=new Timer(delay,new gameTimerListener());
        gameTimer.start();
        timertimer = new Timer(1000, new timerTimerListener());
        timertimer.start();
        inGame = true;
	}

	    public void paint(Graphics g) {
	        this.setFocusable(true);
	        System.out.println("In paint");
	        super.paint(g);
	        if (inGame) {

	            g.drawImage(apple, apple_x, apple_y, this);

	            for (int z = 0; z < dots; z++) {
	                if (z == 0)
	                    g.drawImage(head, x[z], y[z], this);
	                else g.drawImage(body, x[z], y[z], this);
	            }

	            Toolkit.getDefaultToolkit().sync();
	            g.dispose();

	        } 
	        else {
	            gameOver(g);
	        }
	    }  
	    
	    public void gameOver(Graphics g) {
	    	System.out.println("Game Over");
	        String msg = "Game Over";
	        Font small = new Font("Helvetica", Font.BOLD, 14);
	        FontMetrics metr = this.getFontMetrics(small);

	        g.setColor(Color.white);
	        g.setFont(small);
	        g.drawString(msg, (width - metr.stringWidth(msg)) / 2,
	                     height / 2);
	        gameTimer.stop();
	        timertimer.stop();
	        applesCount = 0;
	        seconds = 0;
	        minutes = 0;
	    }
	    
	    public void locateApple() {
	    	System.out.println("In locateApple");
	    	double rand=Math.random();
	    	if(iControl && rand>=.8 && rand<=.9)
	    		rand=.1;
	    	if(rand>=0 && rand<.3)
	    	{
	    		apple=normal;
	    		appleText = "NORMAL";
	    	}
	    	else if(rand>=.3 && rand<.4)
	    	{
	    		apple=slow;
	    		appleText = "SLOW";
	    	}
	    	else if(rand>=.4 && rand<.5)
	    	{
	    		apple=fast;
	    		appleText = "FAST";
	    	}
	    	else if(rand>=.5 && rand<.65)
	    	{
	    		apple=longer;
	    		appleText = "LONGER";
	    	}
	    	else if(rand>=.65 && rand<.8)
	    	{
	    		apple=shorter;
	    		appleText = "SHORTER";
	    	}
	    	else if(rand>=.8 && rand<.88)
	    	{
	    		apple=reverse;
	    		appleText = "REVERSE";
	    	}
	    	else if(rand>=.88 && rand<.9)
	    	{
	    		apple=controls;
	    		appleText = "CONTROLS";
	    	}
	    	else if(rand>=.9 && rand<=1.0)
	    	{
	    		apple=change;
	    		appleText = "CHANGE";
	    	}
	    	else{
	    		apple=normal;
	    		appleText = "Normal";
	    	}
	        int r = (int) (Math.random() * rand_pos);
	        apple_x = ((r * dot_size));
	        r = (int) (Math.random() * rand_pos);
	        apple_y = ((r * dot_size));
	    	int z=0;
	    	while(z<dots)
	    	{
	        	if(apple_x==x[z] && apple_y==y[z])
	        	{
	                r = (int) (Math.random() * rand_pos);
	                apple_x = ((r * dot_size));
	                r = (int) (Math.random() * rand_pos);
	                apple_y = ((r * dot_size));
	                z=0;
	        	}
	        	else
	        		z++;
	        }
	    }
	    
	    public void checkApple() {
	    	System.out.println("In checkApple");
	        if ((x[0] == apple_x) && (y[0] == apple_y)) {
		    	applesCount = applesCount + 1;
	        	if(iControl)
	        	{
	        		iControl=false;
	        		if(left)
	        		{
	        			left=false;
	        			right=true;
	        			up=false;
	        			down=false;
	        		}
	        		else if(right)
	        		{
	        			left=true;
	        			right=false;
	        			up=false;
	        			down=false;
	        		}
	        		else if(up)
	        		{
	        			left=false;
	        			right=false;
	        			up=false;
	        			down=true;
	        		}
	        		else if(down)
	        		{
	        			left=false;
	        			right=false;
	        			up=true;
	        			down=false;
	        		}
	        	}
	        	if(apple==normal)
	        	{
	        		dots++;
	        		locateApple();
	        	}
	        	else if(apple==slow)
	        	{
	           		int delay_slow=700;
	        		int time=13000;
	        		gameTimer.stop();
	        		gameTimer=new Timer(delay_slow, new gameTimerListener());
	        		gameTimer.start();
	        		if(iSlow)
	        		{
	        			slowTimer.stop();
	        		}
	        		slowTimer=new Timer(time,new slowTimerListener());
	        		slowTimer.start();
	        		iSlow=true;
	        		dots++;
	        		locateApple();
	        	}
	        	else if(apple==fast)
	        	{
	        		int delay_fast=40;
	        		int time=13000;
	        		gameTimer.stop();
	        		gameTimer=new Timer(delay_fast, new gameTimerListener());
	        		gameTimer.start();
	        		if(iFast)
	        		{
	        			fastTimer.stop();
	        		}
	        		fastTimer=new Timer(time,new fastTimerListener());
	        		fastTimer.start();
	        		iFast=true;
	        		dots++;
	        		locateApple();
	        	}
	        	else if(apple==longer)
	        	{
	        		countLong=5;
	        		dots++;
	        		locateApple();
	        	}
	        	else if(apple==shorter)
	        	{
	        		if(dots>5)
	        		{
	        			dots=dots-5;
	        			locateApple();
	        		}
	        		else
	        			locateApple();
	        	}
	        	else if(apple==reverse)
	        	{
	        		int[] temp_x=new int[all_dots];
	        		temp_x=x.clone();
	        		int[] temp_y=new int[all_dots];
	        		temp_y=y.clone();
	        		int p=0;
	        		for(int z=dots-1;z>=0;z--)
	        		{
	        			x[z]=temp_x[p];
	        			y[z]=temp_y[p];
	        			p++;
	        		}
	        		if(x[1]>x[0])
	        		{
	        			up=false;
	        			left=true;
	        			right=false;
	        			down=false;
	        		}
	        		else if(x[1]<x[0])
	        		{
	        			up=false;
	        			left=false;
	        			right=true;
	        			down=false;
	        		}
	        		else if(y[1]>y[0])
	        		{
	        			up=true;
	        			left=false;
	        			right=false;
	        			down=false;
	        		}
	        		else if(y[1]<y[0])
	        		{
	        			up=false;
	        			left=false;
	        			right=false;
	        			down=true;
	        		}
	        		locateApple();
	        	}
	        	else if(apple==controls)
	        	{
	        		System.out.println("Just ran into CONTROL REVERSE");
	        		iControl=true;
	        		if(left)
	        		{
	        			System.out.print("You were going left");
	        			left=false;
	        			right=true;
	        			up=false;
	        			down=false;
	        		}
	        		else if(right)
	        		{
	        			System.out.print("You were going right");
	        			left=true;
	        			right=false;
	        			up=false;
	        			down=false;
	        		}
	        		else if(up)
	        		{
	        			System.out.print("You were going up");
	        			left=false;
	        			right=false;
	        			up=false;
	        			down=true;
	        		}
	        		else if(down)
	        		{
	        			System.out.print("You were going down");
	        			left=false;
	        			right=false;
	        			up=true;
	        			down=false;
	        		}
	        		dots++;
	        		locateApple();
	        	}
	        	else if(apple==change)
	        	{
	        		changeCount++;
	        		dots++;
	        		locateApple();
	        	}
	        }
	    }


	    public void move() {
	    	System.out.println("In move");
	        for (int z = dots; z > 0; z--) {
	            x[z] = x[(z - 1)];
	            y[z] = y[(z - 1)];
	        }
	        System.out.println("left= "+left);
	        System.out.println("right= "+right);
	        System.out.println("up= "+up);
	        System.out.println("down= "+down);
	        if(!iControl)
	        {
		        if (left) {
		        	System.out.println("went left");
		            x[0] -= dot_size;
		        }
		
		        if (right) {
		        	System.out.println("went right");
		            x[0] += dot_size;
		        }
		
		        if (up) {
		        	System.out.println("went up");
		            y[0] -= dot_size;
		        }
		
		        if (down) {
		        	System.out.println("went down");
		            y[0] += dot_size;
		        } 
	        }
	        else
	        {
	        	if(left)
	        	{
	        		System.out.println("went right iControl");
	        		x[0] += dot_size;
	        	}
	        	if(right)
	        	{
	        		System.out.println("went left iContrl");
	        		x[0] -= dot_size;
	        	}
	        	if(up)
	        	{
	        		System.out.println("went down iContrl");
	        		y[0] += dot_size;
	        	}
	        	if(down)
	        	{
	        		System.out.println("went up iControl");
	        		y[0] -= dot_size;
	        	} 
	        }
	    }


	    public void checkCollision() {
	    	System.out.println("In checkCollision");
	          for (int z = dots; z > 0; z--) {

	              if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
	                  inGame = false;
	                  System.out.println("In the forloop?");
	              }
	          }

	        if (y[0] > height) {
	            inGame = false;
	            System.out.println("Too high");
	        }

	        if (y[0] < 0) {
	            inGame = false;
	            System.out.println("Too low");
	        }

	        if (x[0] > width) {
	            inGame = false;
	            System.out.println("Too right");
	        }

	        if (x[0] < 0) {
	            inGame = false;
	            System.out.println("Too left");
	        }
	        System.out.println(inGame);
	    }
	    
	class Keys extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
	    	System.out.println("Key Pressed");
	        int key = e.getKeyCode();

	        if ((key == KeyEvent.VK_LEFT) && (!right)) {
	            left = true;
	            up = false;
	            down = false;
	        }

	        if ((key == KeyEvent.VK_RIGHT) && (!left)) {
	            right = true;
	            up = false;
	            down = false;
	        }

	        if ((key == KeyEvent.VK_UP) && (!down)) {
	            up = true;
	            right = false;
	            left = false;
	        }

	        if ((key == KeyEvent.VK_DOWN) && (!up)) {
	            down = true;
	            right = false;
	            left = false;
	        }
	        if((key == KeyEvent.VK_SPACE) && changeCount>0)
	        {
	        	changeCount--;
	        	locateApple();
	        }
		}
	}

	class gameTimerListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("Game Timer finished");
	    	System.out.println(inGame);
	        if (inGame && countLong==0) {
	            checkApple();
	            checkCollision();
	            move();
	        }
	        else if(countLong>0)
	        {
	        	checkApple();
	        	checkCollision();
	        	dots++;
	        	move();
	        	countLong--;
	        }
	        repaint();
	    }
	}

	class slowTimerListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("Slow Timer finished");
	        if (inGame) {
	        	slowTimer.stop();
	        	gameTimer.stop();
	        	gameTimer=new Timer(delay,new gameTimerListener());
	        	gameTimer.start();
	        	iSlow=false;
	        }
	    }
	}

	class fastTimerListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("Fast Timer finished");
	        if (inGame) {
	        	fastTimer.stop();
	        	gameTimer.stop();
	        	gameTimer=new Timer(delay,new gameTimerListener());
	        	gameTimer.start();
	        	iFast=false;
	        }
	    }
	}
	
	class timerTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			seconds = seconds + 1;
			if(seconds >= 60){
				minutes = minutes + 1;
				seconds= seconds - 60;	
			}
		}
	}
}