package edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock.GeneralPathWrapper;
import edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock.ShapeTransforms;


/**
 * The main display for the Binary Clock, uses Block.
 * @@@ To Do:
 * @@@ - Additional render layouts, currently only tutorial
 * @@@ - Support for additional block styles, currently only basic
 * @@@ - Better looking text labels for tutorial mode
 * @@@ - Windowless fullscreen support?
 * @author Peter Bennion
 * @author Yantsey Tsai
 * @version legacy code project cs56, W14
 */
public class TimePanel extends JPanel 
{
    protected GroupLayout layout;

    protected Block s1, s2, s4, s8, s10, s20, s40;
    protected Block m1, m2, m4, m8, m10, m20, m40;
    protected Block h1, h2, h4, h8;
    protected Block PM, AM;

   
    /**
        Constructor
        @param type String representing the layout type, "Tutorial", "Grid", or "Fill"
                    Defaults to "Tutorial" if invalid param
                    currently, only "Tutorial" is supported
    */
    public TimePanel(String type, Color timePanelBackgroundColor_, Color onBlockColor_, Color offBlockColor_)
    {
        layout = new GroupLayout(this);
	// Set input colors
	setLayout(layout);
	setBackground(timePanelBackgroundColor_);
	initBlocks(onBlockColor_, offBlockColor_);
	initTutorial();
    }

    /**
        Initializes the blocks with a basic style.
    */
    protected void initBlocks(Color onBlockColor_, Color offBlockColor_)
    {
        s1 = new Block(onBlockColor_, offBlockColor_);
        s2 = new Block(onBlockColor_, offBlockColor_);
        s4 = new Block(onBlockColor_, offBlockColor_);
        s8 = new Block(onBlockColor_, offBlockColor_);
        s10= new Block(onBlockColor_, offBlockColor_);
        s20= new Block(onBlockColor_, offBlockColor_);
        s40= new Block(onBlockColor_, offBlockColor_);

        m1 = new Block(onBlockColor_, offBlockColor_);
        m2 = new Block(onBlockColor_, offBlockColor_);
        m4 = new Block(onBlockColor_, offBlockColor_);
        m8 = new Block(onBlockColor_, offBlockColor_);
        m10= new Block(onBlockColor_, offBlockColor_);
        m20= new Block(onBlockColor_, offBlockColor_);
        m40= new Block(onBlockColor_, offBlockColor_);

        h1 = new Block(onBlockColor_, offBlockColor_);
        h2 = new Block(onBlockColor_, offBlockColor_);
        h4 = new Block(onBlockColor_, offBlockColor_);
        h8 = new Block(onBlockColor_, offBlockColor_);

        AM = new Block(onBlockColor_, offBlockColor_);
        PM = new Block(onBlockColor_, offBlockColor_);

    }


    /**
        Initializes a beginner-friendly format with guide labels
    */
    protected void initTutorial()
    {
        //add gaps between components and edges
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
	
      
        //create the guide labels
        JLabel AMLabel = new JLabel("AM");
	AMLabel.setFont(new Font("URW Gothic L", Font.BOLD,12));
            AMLabel.setForeground(Color.WHITE);
            AMLabel.setHorizontalAlignment(SwingConstants.CENTER);
            AMLabel.setVerticalAlignment(SwingConstants.CENTER);
	    AMLabel.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
	    AMLabel.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
	    AMLabel.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
	JLabel PMLabel = new JLabel("PM");
	PMLabel.setFont(new Font("URW Gothic L", Font.BOLD,12));
            PMLabel.setForeground(Color.WHITE);
            PMLabel.setHorizontalAlignment(SwingConstants.CENTER);
            PMLabel.setVerticalAlignment(SwingConstants.CENTER);
	    PMLabel.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
	    PMLabel.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
	    PMLabel.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
	JLabel extraLabel = new JLabel(" ");  //placeholder whitespace for leftmost column
            extraLabel.setForeground(Color.WHITE);
            extraLabel.setHorizontalAlignment(SwingConstants.CENTER);
            extraLabel.setVerticalAlignment(SwingConstants.CENTER);
	    extraLabel.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    extraLabel.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    extraLabel.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
        JLabel HLabel = new JLabel("Hours");
	HLabel.setFont(new Font("URW Gothic L", Font.BOLD,12));
            HLabel.setForeground(Color.WHITE);
            HLabel.setHorizontalAlignment(SwingConstants.CENTER);
            HLabel.setVerticalAlignment(SwingConstants.CENTER);
	    HLabel.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    HLabel.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    HLabel.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
        JLabel M10Label = new JLabel("Minutes 10's");
	M10Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
    	    M10Label.setForeground(Color.WHITE);
            M10Label.setHorizontalAlignment(SwingConstants.CENTER);
            M10Label.setVerticalAlignment(SwingConstants.CENTER);
	    M10Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    M10Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    M10Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
        JLabel M1Label = new JLabel("Minutes 1's");
	M1Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
            M1Label.setForeground(Color.WHITE);
            M1Label.setHorizontalAlignment(SwingConstants.CENTER);
            M1Label.setVerticalAlignment(SwingConstants.CENTER);
	    M1Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    M1Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    M1Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
        JLabel S10Label = new JLabel("Seconds 10's");
	S10Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
            S10Label.setForeground(Color.WHITE);
            S10Label.setHorizontalAlignment(SwingConstants.CENTER);
            S10Label.setVerticalAlignment(SwingConstants.CENTER);
      	    S10Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    S10Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    S10Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	JLabel S1Label = new JLabel("Seconds 1's");
	S1Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
            S1Label.setForeground(Color.WHITE);
            S1Label.setHorizontalAlignment(SwingConstants.CENTER);
            S1Label.setVerticalAlignment(SwingConstants.CENTER);
	    S1Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    S1Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	    S1Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/10, BinaryClock.getFrameHeight()/20));
	JLabel N8Label = new JLabel("8"); //8
	N8Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
            N8Label.setForeground(Color.WHITE);
            N8Label.setHorizontalAlignment(SwingConstants.CENTER);
            N8Label.setVerticalAlignment(SwingConstants.CENTER);
	    N8Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N8Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N8Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
        JLabel N4Label = new JLabel("4"); //4
	N4Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
            N4Label.setForeground(Color.WHITE);
            N4Label.setHorizontalAlignment(SwingConstants.CENTER);
            N4Label.setVerticalAlignment(SwingConstants.CENTER);
	    N4Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N4Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N4Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
        JLabel N2Label = new JLabel("2"); //2
	N2Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
            N2Label.setForeground(Color.WHITE);
            N2Label.setHorizontalAlignment(SwingConstants.CENTER);
            N2Label.setVerticalAlignment(SwingConstants.CENTER);
	    N2Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N2Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N2Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
        JLabel N1Label = new JLabel("1");
	N1Label.setFont(new Font("URW Gothic L", Font.BOLD,12));
	    N1Label.setForeground(Color.WHITE); //1
            N1Label.setHorizontalAlignment(SwingConstants.CENTER);
            N1Label.setVerticalAlignment(SwingConstants.CENTER);
	    N1Label.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N1Label.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));
	    N1Label.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/50, BinaryClock.getFrameHeight()*3/20));

 	
        //tell the layout how to set up columns
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup().
             addComponent(AMLabel).
             addComponent(PMLabel));
        hGroup.addGroup(layout.createParallelGroup().
	     addComponent(extraLabel). //extra
	     addComponent(AM ).
             addComponent(PM ));
        hGroup.addGroup(layout.createParallelGroup().
             addComponent(HLabel).
             addComponent(h8 ).
             addComponent(h4 ).
             addComponent(h2 ).
             addComponent(h1 ));
        hGroup.addGroup(layout.createParallelGroup().
             addComponent(M10Label).
             addComponent(m40).
             addComponent(m20).
             addComponent(m10));
        hGroup.addGroup(layout.createParallelGroup().
             addComponent(M1Label).
             addComponent(m8 ).
             addComponent(m4 ).
             addComponent(m2 ).
             addComponent(m1 ));
        hGroup.addGroup(layout.createParallelGroup().
             addComponent(S10Label).
             addComponent(s40).
             addComponent(s20).
             addComponent(s10));
        hGroup.addGroup(layout.createParallelGroup().
             addComponent(S1Label).
             addComponent(s8 ).
             addComponent(s4 ).
             addComponent(s2 ).
             addComponent(s1 ));
        hGroup.addGroup(layout.createParallelGroup().
            addComponent(N8Label).
            addComponent(N4Label).
            addComponent(N2Label).
            addComponent(N1Label));
        layout.setHorizontalGroup(hGroup);

        //tell the layout how to set up rows
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	     addComponent(extraLabel). //extra
	     addComponent(HLabel).
	     addComponent(M10Label).
	     addComponent(M1Label).
	     addComponent(S10Label).
	     addComponent(S1Label));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	    addComponent(N8Label).
            addComponent(h8 ).
            addComponent(m8 ).
            addComponent(s8 ));
            vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	    addComponent(N4Label).
            addComponent(h4 ).
            addComponent(m40).
            addComponent(m4 ).
            addComponent(s40).
            addComponent(s4 ));
	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).	    
            addComponent(AM ).
	    addComponent(AMLabel).
	    addComponent(N2Label).
            addComponent(h2 ).
            addComponent(m20).
            addComponent(m2 ).
            addComponent(s20).
            addComponent(s2 ));
     	vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).		
            addComponent(PM ).
	    addComponent(PMLabel).
            addComponent(N1Label).
            addComponent(h1 ).
            addComponent(m10).
            addComponent(m1 ).
            addComponent(s10).
            addComponent(s1 ));
        layout.setVerticalGroup(vGroup);
    }

    /**
        Get the blocks representing hours
        @return an array of Blocks h8, h4, h2, h1
    */
    public Block[] getHour()
    {
        Block[] b = {h1, h2, h4, h8};
        return b;
    }

    /**
        Get the blocks representing the 10s digit of minutes
        @return an array of Blocks m40, m20, m10
    */
    public Block[] getMinute10s()
    {
        Block[] b = {m10, m20, m40};
        return b;
    }

    /**
        Get the blocks representing the 1s digit of minutes
        @return an array of Blocks m8, m4, m2, m1
    */
    public Block[] getMinute1s()
    {
        Block[] b = {m1, m2, m4, m8};
        return b;
    }

    /**
        Get the blocks representing the 10s digit of seconds
        @return an array of Blocks s40, s20, s10
    */
    public Block[] getSecond10s()
    {
        Block[] b = {s10, s20, s40};
        return b;
    }

    /**
        Get the blocks representing the 1s digit of seconds
        @return an array of Blocks s8, s4, s2, s1
    */
    public Block[] getSecond1s()
    {
        Block[] b = {s1, s2, s4, s8};
        return b;
    }

    /**
        Get the blocks representing Am and Pm
        @return an array of blocks AM, PM
    */
    public Block[] getAmPm()
    {
        Block[] b = {AM, PM};
        return b;
    }

}
