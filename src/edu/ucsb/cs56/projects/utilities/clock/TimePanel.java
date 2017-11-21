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
    

    // protected Block s1, s2, s4, s8, s10, s20, s40;
    // protected ArrayList<Block> sec1 = new ArrayList<Block>();
    // protected ArrayList<Block> sec10 = new ArrayList<Block>();
    // protected Block m1, m2, m4, m8, m10, m20, m40;
    // protected ArrayList<Block> minute1 = new ArrayList<Block>();
    // protected ArrayList<Block> minute10 = new ArrayList<Block>();
    // protected Block h1, h2, h4, h8;
    // protected ArrayList<Block> hour = new ArrayList<Block>();

    protected ArrayList<ArrayList<Block>> timeBlocks = new ArrayList<ArrayList<Block>>();
						
    protected Block pm, am;

   
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
	String [] seconds1 = {"s1","s2","s4","s8"};
    ArrayList<Block> sec1 = new ArrayList<Block>();
	for (String s: seconds1){
	    sec1.add(new Block(onBlockColor_,  offBlockColor_));
        }

    ArrayList<Block> sec10 = new ArrayList<Block>();
    String [] seconds10 = {"s10","s20","s40"};
    for (String s: seconds10){
        sec10.add(new Block(onBlockColor_,  offBlockColor_));
        }

    ArrayList<Block> minute1 = new ArrayList<Block>();
	String [] minutes1 = {"m1", "m2", "m4", "m8"};
	for (String m: minutes1){
            minute1.add(new Block(onBlockColor_,  offBlockColor_));

        }

    ArrayList<Block> minute10 = new ArrayList<Block>();
    String [] minutes10 = {"m10", "m20", "m40"};
    for (String m: minutes10){
            minute10.add(new Block(onBlockColor_,  offBlockColor_));

        }

    ArrayList<Block> hour = new ArrayList<Block>();
	String [] hr = {"h1", "h2", "h4", "h8"};
	for (String h: hr){
            hour.add(new Block(onBlockColor_,  offBlockColor_));

        }

    am = new Block(onBlockColor_,  offBlockColor_);
	pm = new Block(onBlockColor_,  offBlockColor_);

    timeBlocks.add(sec1);
    timeBlocks.add(sec10);
    timeBlocks.add(minute1);
    timeBlocks.add(minute10);
    timeBlocks.add(hour);

    }
    /**
        Initializes a beginner-friendly format with guide labels
    */
    public ArrayList<Block> createTempArrayList(int column_num){
	ArrayList<Block> temporary = new ArrayList<Block>();
	for(int i=timeBlocks.size()-1; i>=0; i--){
	    if(timeBlocks.get(column_num)!= NULL)
		temporary.add(timeBlocks.get(i).get(column_num));
	}
	return temporary;
    }

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

       GroupLayout.ParallelGroup ampmGroup = layout.createParallelGroup();
       hGroup.addGroup(ampmGroup);
       ampmGroup.addComponent(extraLabel);
       ampmGroup.addComponent(am);
       ampmGroup.addComponent(pm);

       
       GroupLayout.ParallelGroup hourGroup = layout.createParallelGroup();
       hGroup.addGroup(hourGroup);
       hourGroup.addComponent(HLabel);
       createParallelG(timeBlocks.get(4), hourGroup);

       //hGroup.addGroup(layout.createParallelGroup().addComponent(HLabel));

        GroupLayout.ParallelGroup minute10Group = layout.createParallelGroup();
        hGroup.addGroup(minute10Group);
	    minute10Group.addComponent(M10Label);
        createParallelG(timeBlocks.get(3), minute10Group);

        GroupLayout.ParallelGroup minute1Group = layout.createParallelGroup();
        hGroup.addGroup(minute1Group);
	    minute1Group.addComponent(M1Label);
        createParallelG(timeBlocks.get(2), minute1Group);

        GroupLayout.ParallelGroup second10Group = layout.createParallelGroup();
        hGroup.addGroup(second10Group);
	    second10Group.addComponent(S10Label);
        createParallelG(timeBlocks.get(1), second10Group);

        GroupLayout.ParallelGroup second1Group = layout.createParallelGroup();
        hGroup.addGroup(second1Group);
	second1Group.addComponent(S1Label);
        createParallelG(timeBlocks.get(0), second1Group);

        
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
        GroupLayout.ParallelGroup eightGroup = layout.createParallelGroup(Alignment.BASELINE);
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).
	    addComponent(N8Label).
            addComponent(h8 ).
            addComponent(m8 ).
            addComponent(s8 ));
	
	GroupLayout.ParallelGroup fourGroup = layout.createParallelGroup(Alignment.BASELINE);
        vGroup.addGroup(fourGroup);
	fourGroup.addComponent(N4Label);
        createParallelG(createTempArrayList(2), fourGroup);
	   
        
	GroupLayout.ParallelGroup twoGroup = layout.createParallelGroup(Alignment.BASELINE);
	vGroup.addGroup(twoGroup);
	twoGroup.addComponent(am);
	twoGroup.addComponent(AMLabel);
	twoGroup.addComponent(N2Label);
        createParallelG(createTempArrayList(1), twoGroup);
  
	GroupLayout.ParallelGroup oneGroup = layout.createParallelGroup(Alignment.BASELINE);
        vGroup.addGroup(oneGroup);
	oneGroup.addComponent(pm );
	oneGroup.addComponent(PMLabel);
        oneGroup.addComponent(N1Label);
        createParallelG(createTempArrayList(0), oneGroup);
        layout.setVerticalGroup(vGroup);
    }



    public void createParallelG(ArrayList<Block> blocks, GroupLayout.ParallelGroup pGroup){
        //pGroup.addComponent(label);
        for(Block b : blocks){
            pGroup.addComponent(b);
        }
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
