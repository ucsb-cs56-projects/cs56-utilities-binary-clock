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
public class BinaryClock extends JPanel implements Runnable
{

    //Index of outer array in timeBlocks
    private final int HOUR_INDEX = 5;
    private final int MINUTE10_INDEX = 4;
    private final int MINUTE1_INDEX = 3;
    private final int SECOND10_INDEX = 2;
    private final int SECOND1_INDEX = 1;
    private final int AMPM_INDEX = 0;

    //Index of inner array in timeBlocks
    private final int ONES_INDEX = 0;
    private final int TWOS_INDEX = 1;
    private final int FOURS_INDEX = 2;
    private final int EIGHTS_INDEX = 3;


    protected GroupLayout layout;

    protected ArrayList<ArrayList<Shape>> timeBlocks = new ArrayList<ArrayList<Shape>>();



   
    /**
        Constructor
        @param type String representing the layout type, "Tutorial", "Grid", or "Fill"
                    Defaults to "Tutorial" if invalid param
                    currently, only "Tutorial" is supported
    */
    public BinaryClock(String type, Color timePanelBackgroundColor_, Color onBlockColor_, Color offBlockColor_, ShapeFactory ini_factory)
    {
        layout = new GroupLayout(this);
	   // Set input colors
	   setLayout(layout);
	   setBackground(timePanelBackgroundColor_);
	   initBlocks(onBlockColor_, offBlockColor_, timePanelBackgroundColor_, ini_factory);
	   initTutorial();
    }

    /**
        Initializes the blocks with a basic style.
    */
    protected void initBlocks(Color onBlockColor_, Color offBlockColor_, Color BGcolor, ShapeFactory factory)
    {
        String [] seconds1 = {"s1","s2","s4","s8"};
        ArrayList<Shape> sec1 = new ArrayList<Shape>();
        for (String s: seconds1){
            sec1.add(factory.build(onBlockColor_, offBlockColor_, BGcolor));
        }

        ArrayList<Shape> sec10 = new ArrayList<Shape>();
        String [] seconds10 = {"s10","s20","s40"};
        for (String s: seconds10){
	       sec10.add(factory.build(onBlockColor_, offBlockColor_, BGcolor));
        }

        ArrayList<Shape> minute1 = new ArrayList<Shape>();
        String [] minutes1 = {"m1", "m2", "m4", "m8"};
        for (String m: minutes1){
            minute1.add(factory.build(onBlockColor_, offBlockColor_, BGcolor));

        }

        ArrayList<Shape> minute10 = new ArrayList<Shape>();
        String [] minutes10 = {"m10", "m20", "m40"};
        for (String m: minutes10){
            minute10.add(factory.build(onBlockColor_, offBlockColor_, BGcolor));

        }

        ArrayList<Shape> hour = new ArrayList<Shape>();
        String [] hr = {"h1", "h2", "h4", "h8"};
        for (String h: hr){
            hour.add(factory.build(onBlockColor_, offBlockColor_, BGcolor));
        }

        ArrayList<Shape> ampm = new ArrayList<Shape>();
        String[] ap = {"PM", "AM"};
        for(String h: ap){
            ampm.add(factory.build(onBlockColor_, offBlockColor_, BGcolor));
        }
	
        timeBlocks.add(AMPM_INDEX, ampm);
        timeBlocks.add(SECOND1_INDEX, sec1);
        timeBlocks.add(SECOND10_INDEX, sec10);
        timeBlocks.add(MINUTE1_INDEX, minute1);
        timeBlocks.add(MINUTE10_INDEX, minute10);
        timeBlocks.add(HOUR_INDEX, hour);

    }

    /**
        Initializes a beginner-friendly format with guide labels
    */
    protected ArrayList<Shape> createColumnArrayList(int column_num){
        ArrayList<Shape> temporary = new ArrayList<Shape>();
        for(int i=timeBlocks.size()-1; i>=0; i--){

            if(timeBlocks.get(i).size() > column_num){
                temporary.add(timeBlocks.get(i).get(column_num));
            }
        }

	   return temporary;
    }


    protected JLabel createLabel(String labelName){

        JLabel label = new JLabel(labelName);
        label.setFont(new Font("URW Gothic L", Font.BOLD,12));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        return label;
    }
    protected void initTutorial()
    {
        //add gaps between components and edges
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

	      
        //create the guide labels
        JLabel AMLabel = createLabel("AM");
        JLabel PMLabel = createLabel("PM");        
        JLabel extraLabel = createLabel(" ");	
        JLabel HLabel = createLabel("Hours");
        JLabel M10Label = createLabel("Minutes 10's");
        JLabel M1Label = createLabel("Minutes 1's");
        JLabel S10Label = createLabel("Seconds 10's");
        JLabel S1Label = createLabel("Seconds 1's");
        JLabel N8Label = createLabel("8"); 
        JLabel N4Label = createLabel("4"); 
        JLabel N2Label = createLabel("2"); 
        JLabel N1Label =  createLabel("1");
	       
        //tell the layout how to set up columns
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup().
            addComponent(AMLabel).
            addComponent(PMLabel));

        GroupLayout.ParallelGroup ampmGroup = layout.createParallelGroup();
        hGroup.addGroup(ampmGroup);
        ampmGroup.addComponent(extraLabel);

       
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

        createParallelG(timeBlocks.get(AMPM_INDEX), ampmGroup);
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

        twoGroup.addComponent(AMLabel);
        twoGroup.addComponent(N2Label);
  
        GroupLayout.ParallelGroup oneGroup = layout.createParallelGroup(Alignment.BASELINE);
        vGroup.addGroup(oneGroup);
        oneGroup.addComponent(PMLabel);
        oneGroup.addComponent(N1Label);


        createParallelG(createColumnArrayList(EIGHTS_INDEX), eightGroup);
        createParallelG(createColumnArrayList(FOURS_INDEX), fourGroup);
        createParallelG(createColumnArrayList(TWOS_INDEX), twoGroup);
        createParallelG(createColumnArrayList(ONES_INDEX), oneGroup);
        layout.setVerticalGroup(vGroup);
    }


    /**
        Adds each Block object in blocks as a component of pGroup
    */
    public void createParallelG(ArrayList<Shape> blocks, GroupLayout.ParallelGroup pGroup){
   
        for(Shape b : blocks){
            pGroup.addComponent(b);
        }
    }


    /**
        Get the blocks corresponding to the string argument
        @return an array of Blocks from a certain column in timeBlocks
    */
    protected Shape[] getTimeBlocks(String blockName){

        int blockIndex = 0;
        switch (blockName){
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
                return new Shape[0];  //Return empty array of shapes
        }

        Shape[] b = new Shape[timeBlocks.get(blockIndex).size()];
        for(int i = 0; i < b.length; i++){
            b[i] = timeBlocks.get(blockIndex).get(i);
        }

        return b;
    }



    public void getTime(){
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

     /*
        Inputs the string into the AmPm array of blocks
        @param s Binary String to be input
        @param blocks Array of Blocks to be updated
    */
    protected void updateAmPmBlocks(String s)
    {
        if(s.charAt(0) == '1') //if it's am
        {
            timeBlocks.get(AMPM_INDEX).get(1).input('1'); //am is on
            timeBlocks.get(AMPM_INDEX).get(0).input('0'); //pm is off
        } 
        else
        {
            timeBlocks.get(AMPM_INDEX).get(1).input('0');
            timeBlocks.get(AMPM_INDEX).get(0).input('1');
        }
    }

    /*
        Inputs the string into the array of blocks
        @param s Binary String to be input
        @param blocks Array of Blocks to be updated
    */
    private void updateBlocks(String s, Shape[] blocks)
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

    public void run(){

        //The clock will run forever until the application is closed
        while(true){

            this.getTime();

            //Update the clock every 500 milliseconds
            try
            {
                Thread.sleep(500);
            }

            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}


