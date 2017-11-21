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
public class BinaryClock extends JPanel 
{
    protected final String[] TOP_HORIZ_LABELS = {" ", "Hours", "Minutes 10's", "Minutes 1's", "Seconds 10's", "Seconds 1's"}; 
    protected final String[] LEFT_VERT_LABELS = {" ", "AM", "PM"}; 
    protected final String[] RIGHT_VERT_LABELS = {"8", "4", "2", "1"}; 

    private final int HOUR_INDEX = 4;
    private final int MINUTE10_INDEX = 3;
    private final int MINUTE1_INDEX = 2;
    private final int SECOND10_INDEX = 1;
    private final int SECOND1_INDEX = 0;


    protected GroupLayout layout;

    protected ArrayList<ArrayList<Block>> timeBlocks = new ArrayList<ArrayList<Block>>();

    protected Block pm, am;



   
    /**
        Constructor
        @param type String representing the layout type, "Tutorial", "Grid", or "Fill"
                    Defaults to "Tutorial" if invalid param
                    currently, only "Tutorial" is supported
    */
    public BinaryClock(String type, Color timePanelBackgroundColor_, Color onBlockColor_, Color offBlockColor_)
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
    protected ArrayList<Block> createTempArrayList(int column_num){
        ArrayList<Block> temporary = new ArrayList<Block>();
        for(int i=timeBlocks.size()-1; i>=0; i--){

            if(timeBlocks.get(i).size() > column_num){
                temporary.add(timeBlocks.get(i).get(column_num));
            }
        }

	   return temporary;
    }



    protected void createLabel(JLabel aLabel){

        aLabel.setFont(new Font("URW Gothic L", Font.BOLD,12));
        aLabel.setForeground(Color.WHITE);
        aLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aLabel.setVerticalAlignment(SwingConstants.CENTER);
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

        GroupLayout.ParallelGroup minute10Group = layout.createParallelGroup();
        hGroup.addGroup(minute10Group);

        GroupLayout.ParallelGroup minute1Group = layout.createParallelGroup();
        hGroup.addGroup(minute1Group);

        GroupLayout.ParallelGroup second10Group = layout.createParallelGroup();
        hGroup.addGroup(second10Group);

        GroupLayout.ParallelGroup second1Group = layout.createParallelGroup();
        hGroup.addGroup(second1Group);


        hourGroup.addComponent(HLabel);
        minute10Group.addComponent(M10Label);
        minute1Group.addComponent(M1Label);
        second10Group.addComponent(S10Label);
        second1Group.addComponent(S1Label);


        createParallelG(timeBlocks.get(HOUR_INDEX), hourGroup);
        createParallelG(timeBlocks.get(MINUTE10_INDEX), minute10Group);
        createParallelG(timeBlocks.get(MINUTE1_INDEX), minute1Group);
        createParallelG(timeBlocks.get(SECOND10_INDEX), second10Group);
        createParallelG(timeBlocks.get(SECOND1_INDEX), second1Group);



        
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
        vGroup.addGroup(eightGroup);
        eightGroup.addComponent(N8Label);

        GroupLayout.ParallelGroup fourGroup = layout.createParallelGroup(Alignment.BASELINE);
        vGroup.addGroup(fourGroup);
        fourGroup.addComponent(N4Label);
	   
        
        GroupLayout.ParallelGroup twoGroup = layout.createParallelGroup(Alignment.BASELINE);
        vGroup.addGroup(twoGroup);
        twoGroup.addComponent(am);
        twoGroup.addComponent(AMLabel);
        twoGroup.addComponent(N2Label);
  
        GroupLayout.ParallelGroup oneGroup = layout.createParallelGroup(Alignment.BASELINE);
        vGroup.addGroup(oneGroup);
        oneGroup.addComponent(pm );
        oneGroup.addComponent(PMLabel);
        oneGroup.addComponent(N1Label);


        createParallelG(createTempArrayList(3), eightGroup);
        createParallelG(createTempArrayList(2), fourGroup);
        createParallelG(createTempArrayList(1), twoGroup);
        createParallelG(createTempArrayList(0), oneGroup);
        layout.setVerticalGroup(vGroup);
    }


    /**
        Adds each Block object in blocks as a component of pGroup
    */
    public void createParallelG(ArrayList<Block> blocks, GroupLayout.ParallelGroup pGroup){
   
        for(Block b : blocks){
            pGroup.addComponent(b);
        }
    }


    /**
        Get the blocks corresponding to the string argument
        @return an array of Blocks from a certain column in timeBlocks
    */
    protected Block[] getTimeBlocks(String name){

        int blockIndex = 0;
        switch (name){
            case "hour":
                blockIndex = HOUR_INDEX;
                break;
            case "min10":
                blockIndex = MINUTE10_INDEX;
                break;
            case "min1":
                blockIndex = MINUTE1_INDEX;
                break;
            case "sec10":
                blockIndex = SECOND10_INDEX;
                break;
            case "sec1":
                blockIndex = SECOND1_INDEX;
                break;
            default:
                blockIndex = 0;
                break;
        }

        Block[] b = new Block[timeBlocks.get(blockIndex).size()];
        for(int i = 0; i < b.length; i++){
            b[i] = timeBlocks.get(blockIndex).get(i);
        }

        return b;
    }



    public void getInitialTime(){
        //fetch time data
        String date = String.format("%tr", new Date());
    
        updateHours(date);
        updateMinutes(date);
        updateSeconds(date);
        updateAMPM(date);
    }

    protected void updateSeconds(String date){
        String second10s = Integer.toBinaryString(Integer.parseInt(date.substring(6, 7)));
        updateBlocks(second10s, getTimeBlocks("sec10"));

        String second1s = Integer.toBinaryString(Integer.parseInt(date.substring(7, 8)));
        updateBlocks(second1s, getTimeBlocks("sec1"));
    }

    protected void updateMinutes(String date){
        String minute10s = Integer.toBinaryString(Integer.parseInt(date.substring(3, 4)));
        updateBlocks(minute10s, getTimeBlocks("min10"));
        String minute1s = Integer.toBinaryString(Integer.parseInt(date.substring(4, 5)));
        updateBlocks(minute1s, getTimeBlocks("min1"));
    }

    protected void updateHours(String date){
        String hour = Integer.toBinaryString(Integer.parseInt(date.substring(0, 2)));
        updateBlocks(hour, getTimeBlocks("hour")); 
    }

    protected void updateAMPM(String date){
        if(date.charAt(9)=='A')
            updateAmPmBlocks("1");
        else 
            updateAmPmBlocks("0");
    }

    public void updateTime(long[] prevTimes, boolean refresh){
         //fetch time data
        String date = String.format("%tr", new Date());
        long secTimer = System.currentTimeMillis() - prevTimes[0];
        long minTimer = System.currentTimeMillis() - prevTimes[1];
        long hrTimer = System.currentTimeMillis() - prevTimes[2];
        long ampmTimer = System.currentTimeMillis() - prevTimes[3];

        // update seconds after every 1000 ms
        if(secTimer > 900){
            updateSeconds(date);
            prevTimes[0] = System.currentTimeMillis();
        }
        // update minute after every 60,000 ms
        if((minTimer > 1000 * 60 - 100) || (refresh)){
            updateMinutes(date);
            prevTimes[1] = System.currentTimeMillis();
        }
        
        // update hour after 3,600,000 ms
        if((hrTimer > 1000 * 60 * 60 - 100) || (refresh)){
            updateHours(date);
            prevTimes[2] = System.currentTimeMillis();
        }
               
        // update am/pm after 12 hours (3,600,000 * 12)
        if((ampmTimer > 1000 * 60 * 60 * 12 - 100) || (refresh)){
            updateAMPM(date);
            prevTimes[3] = System.currentTimeMillis();
        }
    }


     /*
        Inputs the string into the AmPm array of blocks
        @param s Binary String to be input
        @param blocks Array of Blocks to be updated
    */
    protected void updateAmPmBlocks(String s)
    {
        if(s.charAt(0) == '1') //if it's am
        {
            am.input('1'); //am is on
            pm.input('0'); //pm is off
        } 
        else
        {
            am.input('0');
            pm.input('1');
        }
    }

    /*
        Inputs the string into the array of blocks
        @param s Binary String to be input
        @param blocks Array of Blocks to be updated
    */
    private void updateBlocks(String s, Block[] blocks)
    {
        for(int i =  blocks.length - 1; i >= 0; i--)
        {
            //associates appropriate blocks to their bits
            if(i<s.length())
                blocks[i].input(s.charAt(s.length()-1-i));
            else 
                blocks[i].input('0');

        }
    }
}




