package edu.ucsb.cs56.projects.utilities.clock;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import edu.ucsb.cs56.projects.utilities.clock.GeneralPathWrapper;
import edu.ucsb.cs56.projects.utilities.clock.ShapeTransforms;


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
    ArrayList<Block> sec = new ArrayList<Block>();
    protected Block m1, m2, m4, m8, m10, m20, m40;
    ArrayList<Block> minute = new ArrayList<Block>();
    protected Block h1, h2, h4, h8;
    ArrayList<Block> hour = new ArrayList<Block>();
						
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
	String [] seconds = {"s1","s2","s4","s8","s10","s20","s40"};
	for (String s: seconds){
	    sec.add(new Block(onBlockColor_,  offBlockColor_));
        }

	String [] minutes = {"m1", "m2", "m4", "m8", "m10", "m20", "m40"};
	for (String m: minutes){
            minute.add(new Block(onBlockColor_,  offBlockColor_));

        }

	String [] hr = {"h1", "h2", "h4", "h8"};
	for (String h: hr){
            hour.add(new Block(onBlockColor_,  offBlockColor_));

        }
   
	AM = new Block(onBlockColor_, offBlockColor_);
        PM = new Block(onBlockColor_, offBlockColor_);

    }
    /**
        Initializes a beginner-friendly format with guide labels
    */


    protected void createLabel(JLabel aLabel){
	//layout.setAutoCreateGaps(true);
        //layout.setAutoCreateContainerGaps(true);
        aLabel.setFont(new Font("URW Gothic L", Font.BOLD,12));
        aLabel.setForeground(Color.WHITE);
        aLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aLabel.setVerticalAlignment(SwingConstants.CENTER);
        aLabel.setMinimumSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
        aLabel.setPreferredSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
        aLabel.setMaximumSize(new Dimension(BinaryClock.getFrameWidth()/25, BinaryClock.getFrameHeight()*3/20));
    }
    protected void initTutorial()
    {
        //add gaps between components and edges
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

	      
        //create the guide labels
        JLabel AMLabel = new JLabel("AM");
	createLabel(AMLabel);
                    
	    
	JLabel PMLabel = new JLabel("PM");
	createLabel(PMLabel);
	        
        
	JLabel extraLabel = new JLabel(" ");  //placeholder whitespace for leftmost column
        createLabel(extraLabel);	
	
        JLabel HLabel = new JLabel("Hours");
	createLabel(HLabel);
           	    
	    
        JLabel M10Label = new JLabel("Minutes 10's");
	createLabel(M10Label);
    	    
            	    
        JLabel M1Label = new JLabel("Minutes 1's");
	createLabel(M1Label);
            
                        	    
        JLabel S10Label = new JLabel("Seconds 10's");
	createLabel(S10Label);
                        
            	    
	JLabel S1Label = new JLabel("Seconds 1's");
	createLabel(S1Label);
                        	    
	JLabel N8Label = new JLabel("8"); //8
	createLabel(N8Label);
            
        JLabel N4Label = new JLabel("4"); //4
	createLabel(N4Label);
            
        JLabel N2Label = new JLabel("2"); //2
	createLabel(N2Label);
            
        JLabel N1Label = new JLabel("1");
	createLabel(N1Label);
	    
        //tell the layout how to set up columns
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup().
             addComponent(AMLabel).
             addComponent(PMLabel));
        hGroup.addGroup(layout.createParallelGroup().
	     addComponent(extraLabel). //extra
	     addComponent(AM ).
             addComponent(PM ));
	HLabel.addComponent(hour.get(3) );
	HLabel.addComponent(hour.get(2));
	HLabel.addComponent(hour.get(1));
	HLabel.addComponent(hour.get(0));
	hGroup.addGroup(layout.createParallelGroup().addComponent(HLabel));
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
