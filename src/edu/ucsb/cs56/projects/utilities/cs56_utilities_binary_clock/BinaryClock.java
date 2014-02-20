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
 * @author Peter Bennion
 * @version for UCSB CS56, W12, choice points 2
 */
public class BinaryClock implements Runnable
{
    protected JFrame frame;
    protected int frameheight;
    protected int framewidth;

    protected JLabel time;

    protected TimePanel panel;

    protected String hour, minute10s, minute1s, second10s, second1s, AM_PM;
    protected String date;

    /**
        Constructor
     */
    public BinaryClock()
    {
        frameheight = 960;
        framewidth = 720;

        //Make frame and all objects
        frame = new JFrame();
            frame.getContentPane().setBackground(Color.BLACK);
            frame.setSize(frameheight, framewidth);
            frame.setTitle("Binary Clock");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        time = new JLabel();
            time.setForeground(Color.WHITE);
        JLabel tut = new JLabel("Each column represents a digit in time of the form hours:minutes:seconds. Add the values of each column to get the digit's total value.");
            tut.setForeground(Color.WHITE);
        panel = new TimePanel("Tutorial"); //No real modes are supported at the moment

        //add objects to the frame
        frame. getContentPane().add(BorderLayout.CENTER, panel);
        frame. getContentPane().add(BorderLayout.SOUTH, time);
        frame. getContentPane().add(BorderLayout.NORTH, tut);
    }

    /**
        Main Function
        @param args not used
    */
    public static void main(String[] args)
    {
        BinaryClock bc = new BinaryClock();

        //Create update thread and start it
        Thread clockUpdater = new Thread(bc);
            clockUpdater.start();
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
        time.setText(date);

        frame.setVisible(true);

        //tell the thread to sleep for a twentieth of a second before reiterating
        try
        {
            Thread.sleep(50);
        } catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
        run();
    }

    /**
        Used by main's thread to update the clock display every 50ms.
        Currently an unused placeholder, will be a more efficient loop than run().
    */
    protected void update()
    {

    }

    /**
        Inputs the string into the array of blocks
        @param s Binary String to be input
        @param blocks Array of Blocks to be updated
    */
    protected void updateBlocks(String s, Block[] blocks)
    {
        for(int i = 0; i < Array.getLength(blocks); i++)
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
}
