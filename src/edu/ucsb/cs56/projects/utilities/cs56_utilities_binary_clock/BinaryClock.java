package edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.reflect.Array;


/**
 * Creates a power of 2 binary clock widget that gets the
 * current time and displays it as a set of 2D blocks.
 * @@@ To Do:
 * @@@ - Configuration settings import and GUI
 * @@@ - Support for multiple render styles such as 'tutorial', 'no text',
 *          and maybe a multicolored one with tick animations.
 * @@@ - More efficient thread loop
 * @@@ - Windowless fullscreen support? (ie ctrl+shift+F11)
 * @author Kevin La
 * @author Chantel Chan
 * @version for UCSB CS56, F16, legacy code project
 */
public class BinaryClock extends JFrame implements Runnable
{
    protected JFrame frame;
    protected int frameheight;
    protected int framewidth;

    protected JLabel time, tut;

    protected TimePanel panel;

    protected String hour, minute10s, minute1s, second10s, second1s, AM_PM;
    protected String date;

    protected static Boolean refresh = false;

    private long startTime,runningTime;
    private long secTimer,minTimer,hrTimer,slast,mlast,hlast;
    private long ampmTimer, ampmlast; // you need to implement these for the flickering issue

    protected Color setBackgroundColor = new Color(0xFFFF66);
    protected Color setOnBlockColor = Color.PINK;
    protected Color setOffBlockColor = Color.CYAN;


    /**
        Constructor
    */
    public BinaryClock()
    {
        frameheight = 1160;
        framewidth = 720;
	
        //Make frame and all objects
        frame = new JFrame();
	frame.getContentPane().setBackground(setBackgroundColor);
	frame.setSize(frameheight, framewidth);
	frame.setTitle("Binary Clock");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	time = new JLabel();
	time.setForeground(Color.BLACK);
	tut = new JLabel("Each column represents a digit in time of the form hours:minutes:seconds. Add the values of each column to get the digit's total value.");
	tut.setForeground(Color.BLACK);
	panel = new TimePanel("Tutorial", setBackgroundColor, setOnBlockColor, setOffBlockColor); //No real modes are supported at the moment
    }
  

    void resetAll() {
	frame.getContentPane().removeAll();
	frame.setSize(frameheight,framewidth);
	JLabel tut = new JLabel("Each column represents a digit in time of the form hours:minutes:seconds. Add the values of each column to get the digit's total value.");
	tut.setForeground(Color.BLACK);
	panel = new TimePanel("Tutorial", setBackgroundColor, setOnBlockColor, setOffBlockColor);
	frame. getContentPane().add(BorderLayout.CENTER, panel);
	frame. getContentPane().add(BorderLayout.NORTH, time);
	frame. getContentPane().add(BorderLayout.SOUTH, tut);
	frame.getContentPane().validate();
	frame.getContentPane().repaint();
	refresh = true;
    }

    // Menu bar
    JMenuBar menubar = new JMenuBar();




    //This is the Drop Down Menu Bar, options
    void setFrameBase() {

	//add objects to the frame
         frame. getContentPane().add(BorderLayout.CENTER, panel);
	 frame. getContentPane().add(BorderLayout.NORTH, time);
	 frame. getContentPane().add(BorderLayout.SOUTH, tut);
	 frame. setJMenuBar(menubar);
	
	// Menus
	JMenu file = new JMenu("File");
	menubar.add(file);
	JMenuItem exit = new JMenuItem("Exit");
	file.add(exit);

	// Exit listener for exit menuItem
	class exitaction implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		System.exit(0);
	    }
	}
	exit.addActionListener(new exitaction());

	JMenu help = new JMenu("Help");
	menubar.add(help);
	JMenuItem instructions = new JMenuItem("Instructions");
	help.add(instructions);

		// Instructions under help
	class instructions implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		BinaryClock.helpBox("This is a binary clock. Think of the clock showing the time as 00:00:00 AM/PM, \n which represents hours:minutes:seconds.Find out the time by adding each column \n with its respective value, shown on the left of each row. The first column on \n the left displays if it AM or PM. The next column shows the hours. The next two \n columns represents the minutes, where the third column is the minutes by multiples \n of ten and the fourth column with the minutes in ones. The last column shows the \n seconds.", "Instructions");
	    }
	}
	instructions.addActionListener(new instructions());
    }




    //This is to set the color of the background
    void setBackgroundColor() {
	
	JMenu backgroundColorSelector = new JMenu("Background Color");
	menubar.add(backgroundColorSelector);
	JMenuItem yellowBackground = new JMenuItem("Yellow");
	JMenuItem pinkBackground = new JMenuItem("Pink");
	JMenuItem greenBackground = new JMenuItem("Green");
	JMenuItem redBackground = new JMenuItem("Red");
	JMenuItem blueBackground = new JMenuItem("Blue");
	JMenuItem orangeBackground = new JMenuItem("Orange");
	backgroundColorSelector.add(yellowBackground);
	backgroundColorSelector.add(pinkBackground);
	backgroundColorSelector.add(greenBackground);
	backgroundColorSelector.add(redBackground);
	backgroundColorSelector.add(blueBackground);
	backgroundColorSelector.add(orangeBackground);

	// Yellow Background
	class yellowBackgroundClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		Color newYellow = new Color(0xFFFF66);
		frame.getContentPane().setBackground(newYellow);
		setBackgroundColor = newYellow;
		resetAll();
	    }
	}
	yellowBackground.addActionListener( new yellowBackgroundClass());

	// Pink Background
	class pinkBackgroundClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		frame.getContentPane().setBackground(Color.PINK);
		setBackgroundColor = Color.PINK;
	        resetAll();
	    }
	}
	pinkBackground.addActionListener( new pinkBackgroundClass());

	// Blue Background
	class blueBackgroundClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		Color newBlue = new Color(0x33CCFF);
		frame.getContentPane().setBackground(newBlue);
	        setBackgroundColor = newBlue;
		resetAll();
	    }
	}
	blueBackground.addActionListener( new blueBackgroundClass());

	// Red Background
	class redBackgroundClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		Color newRed = new Color(0xFF5050);
		frame.getContentPane().setBackground(newRed);
	        setBackgroundColor = newRed;
		resetAll();
	    }
	}
	redBackground.addActionListener( new redBackgroundClass());

	// Orange Background
	class orangeBackgroundClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		Color newOrange = new Color(0xFFB84D);
		frame.getContentPane().setBackground(newOrange);
		setBackgroundColor = newOrange;
		resetAll();
	    }
	}
	orangeBackground.addActionListener( new orangeBackgroundClass());

	// Green Background
	class greenBackgroundClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		Color jadeGreen = new Color(0x00CC66);
		frame.getContentPane().setBackground(jadeGreen);
		setBackgroundColor = jadeGreen;
		resetAll();
	    }
	}
	greenBackground.addActionListener( new greenBackgroundClass());
    }



    
    //Set the color of the individual blocks
    void setBlockColor(){
	JMenu onBlockColorSelector = new JMenu("On Block Color");
	menubar.add(onBlockColorSelector);
	JMenuItem redOnBlock = new JMenuItem("Red");
	JMenuItem orangeOnBlock = new JMenuItem("Orange");
	JMenuItem magentaOnBlock = new JMenuItem("Mangenta");
	onBlockColorSelector.add(redOnBlock);
	onBlockColorSelector.add(orangeOnBlock);
	onBlockColorSelector.add(magentaOnBlock);

	JMenu offBlockColorSelector = new JMenu("Off Block Color");
	menubar.add(offBlockColorSelector);
	JMenuItem blueOffBlock = new JMenuItem("Blue");
	JMenuItem greenOffBlock = new JMenuItem("Green");
	JMenuItem whiteOffBlock = new JMenuItem("Grey");
	offBlockColorSelector.add(blueOffBlock);
	offBlockColorSelector.add(greenOffBlock);
	offBlockColorSelector.add(whiteOffBlock);

	
	// Red On Box
	class redOnBoxClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		setOnBlockColor = Color.RED;
		resetAll();
	    }
	}
	redOnBlock.addActionListener( new redOnBoxClass());

	// Orange On Box
	class orangeOnBoxClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		setOnBlockColor = Color.ORANGE;
		resetAll();
	    }
	}
	orangeOnBlock.addActionListener( new orangeOnBoxClass());

	// Magenta On Box
	class magentaOnBoxClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		setOnBlockColor = Color.MAGENTA;
		resetAll();
	    }
	}
	magentaOnBlock.addActionListener( new magentaOnBoxClass());

	// Blue Off Box
	class blueOffBoxClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		setOffBlockColor = Color.CYAN;
		resetAll();
	    }
	}
	blueOffBlock.addActionListener( new blueOffBoxClass());

	// Green Off Box
	class greenOffBoxClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		setOffBlockColor = Color.GREEN;
		resetAll();
	    }
	}
	greenOffBlock.addActionListener( new greenOffBoxClass());

	// Grey Off Box
	class whiteOffBoxClass implements ActionListener{
	    public void actionPerformed(ActionEvent e){
		setOffBlockColor = Color.WHITE;
		resetAll();
	    }
	}
	whiteOffBlock.addActionListener( new whiteOffBoxClass());

    }




    //Method to be able to set the screen size in JMenu 
    void setWindowSize(){
	JMenu screenSettings = new JMenu("Screen Settings");
	menubar.add(screenSettings);
	JMenuItem minimize = new JMenuItem("Minimize");
	screenSettings.add(minimize);
	JMenuItem maximize = new JMenuItem("Maximize");
	screenSettings.add(maximize);

	// Minimize Screen 
	class minimizeScreen implements ActionListener{
	    public void actionPerformed(ActionEvent e){

		frameheight = 1050;
		framewidth = 250;
		frame.getContentPane().removeAll();
		frame.setSize(frameheight,framewidth);

		JLabel tut = new JLabel("Each column represents a digit in time of the form hours:minutes:seconds. Add the values of each column to get the digit's total value.");
		tut.setForeground(Color.BLACK);
		panel = new TimePanel("Tutorial", setBackgroundColor, setOnBlockColor, setOffBlockColor);
		frame. getContentPane().add(BorderLayout.CENTER, panel);
		frame. getContentPane().add(BorderLayout.NORTH, time);
		frame. getContentPane().add(BorderLayout.SOUTH, tut);
		FlowLayout ex = new FlowLayout();
		frame.getContentPane().setLayout(ex);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		refresh = true;
	    }
	}

	minimize.addActionListener(new minimizeScreen());

	// Maximize Screen 
	class maximizeScreen implements ActionListener{
	    public void actionPerformed(ActionEvent e){

		frameheight = 1160;
		framewidth = 720;
		BorderLayout ex = new BorderLayout();
		frame.getContentPane().setLayout(ex);
		resetAll();
	    }
	}

	maximize.addActionListener(new maximizeScreen());
	
    }
       


    /**
        Main Function
        @param args not used
    */
    public static void main(String[] args)
    {
        BinaryClock bc = new BinaryClock();
	bc.setFrameBase();
	bc.setBackgroundColor();
	bc.setBlockColor();
	bc.setWindowSize();	   

        Thread ClockUpdater = new Thread(bc);
	ClockUpdater.start();
    }
    
    /**
       Used by main's thread to finish initializing clock display.
       Loops into itself for inefficient updates.
       @@@ Will call update() to begin more efficient loop@@@
    */
    
    public void run()
    {
        //fetch time data
        date = String.format("%tr", new Date());
	
        //break up time data into binary strings, then feed to blocks
        hour = Integer.toBinaryString(Integer.parseInt(date.substring(0, 2)));
	    updateBlocks(hour, panel.getHour());
	
        minute10s = Integer.toBinaryString(Integer.parseInt(date.substring(3, 4)));
	    updateBlocks(minute10s, panel.getMinute10s());
        minute1s = Integer.toBinaryString(Integer.parseInt(date.substring(4, 5)));
	    updateBlocks(minute1s, panel.getMinute1s());
	
        second10s = Integer.toBinaryString(Integer.parseInt(date.substring(6, 7)));
            updateBlocks(second10s, panel.getSecond10s());
        second1s = Integer.toBinaryString(Integer.parseInt(date.substring(7, 8)));
            updateBlocks(second1s, panel.getSecond1s());

        //translate Am/Pm data and feed to blocks
        if(date.charAt(9)=='A')
	    AM_PM = "1";
        else AM_PM = "0";
            updateAmPmBlocks(AM_PM, panel.getAmPm());

        //set text on the time panel for debugging purposes. Temporary.
        //time.setText(date);

        frame.setVisible(true);

	//start thread timer
	startTime = System.currentTimeMillis();
	slast = System.currentTimeMillis(); // temporary
	mlast = System.currentTimeMillis(); // temporary
	hlast = System.currentTimeMillis(); // temporary
	ampmlast = System.currentTimeMillis(); // temporary
        //tell the thread to sleep for a twentieth of a second before reiterating
        try
        {
            Thread.sleep(50);
        }

	catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        update();
    }

    /**
        Used by main's thread to update the clock display every 50ms.
        Currently an unused placeholder, will be a more efficient loop than run().
    */
    
    protected void update()
    {
	secTimer = System.currentTimeMillis() - slast;
	minTimer = System.currentTimeMillis() - mlast;
	hrTimer = System.currentTimeMillis() - hlast;
	ampmTimer = System.currentTimeMillis() - ampmlast;
        date = String.format("%tr", new Date());

	// This loop is more efficient because it only updates the blocks that need to be updated
	// For example, hour won't get updated every single second
	
	// update seconds after every 1000 ms
	if(secTimer > 900){
	    second10s = Integer.toBinaryString(Integer.parseInt(date.substring(6, 7)));
            updateBlocks(second10s, panel.getSecond10s());

	    second1s = Integer.toBinaryString(Integer.parseInt(date.substring(7, 8)));
	    updateBlocks(second1s, panel.getSecond1s());
	    slast = System.currentTimeMillis();
	}
	
	
	// update minute after every 60,000 ms
	if((minTimer > 1000 * 60 - 100) || (refresh == true)){
	    minute10s = Integer.toBinaryString(Integer.parseInt(date.substring(3, 4)));
	    updateBlocks(minute10s, panel.getMinute10s());
	    minute1s = Integer.toBinaryString(Integer.parseInt(date.substring(4, 5)));
	    updateBlocks(minute1s, panel.getMinute1s());
	    mlast = System.currentTimeMillis();
	}
	
	// update hour after 3,600,000 ms
	if((hrTimer > 1000 * 60 * 60 - 100) || (refresh == true)){
	    hour = Integer.toBinaryString(Integer.parseInt(date.substring(0, 2)));
	    updateBlocks(hour, panel.getHour()); 
	    hlast = System.currentTimeMillis();
	    }
        
	// update am/pm after 12 hours (3,600,000 * 12)
	if((ampmTimer > 1000 * 60 * 60 * 12 - 100) || (refresh == true)){
	    if(date.charAt(9)=='A')
		AM_PM = "1";
	    else AM_PM = "0";
	    updateAmPmBlocks(AM_PM, panel.getAmPm());
	    refresh = false;
	}
	
        //tell the thread to sleep before reiterating
        try
	    {
		Thread.sleep(500);
	    }

	catch(InterruptedException ex)
	    {
		ex.printStackTrace();
	    }
        update();
    }

    /**
        Inputs the string into the array of blocks
        @param s Binary String to be input
        @param blocks Array of Blocks to be updated
    */
    protected void updateBlocks(String s, Block[] blocks)
    {
        for(int i =  Array.getLength(blocks) - 1; i >= 0; i--)
        {
            //associates appropriate blocks to their bits
            if(i<s.length())
                blocks[i].input(s.charAt(s.length()-1-i));
            else blocks[i].input('0');

        }
    }

    /**
        Inputs the string into the AmPm array of blocks
        @param s Binary String to be input
        @param blocks Array of Blocks to be updated
    */
    protected void updateAmPmBlocks(String s, Block[] blocks)
    {
        if(s.charAt(0) == '1') //if it's am
        {
            blocks[0].input('1'); //am is on
            blocks[1].input('0'); //pm is off
        } else
        {
            blocks[0].input('0');
            blocks[1].input('1');
        }
    }

    public static void helpBox(String helpMessage, String titleBar)
    {
	final JOptionPane pane = new JOptionPane(helpMessage);
	final JDialog d = pane.createDialog((JFrame)null, titleBar);
	d.setLocation(250,80);
	d.setVisible(true);
    }

}
