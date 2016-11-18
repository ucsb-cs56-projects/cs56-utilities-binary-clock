package edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock.GeneralPathWrapper;
import edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock.ShapeTransforms;

/**
 * Used by TimePanel to build BinaryClock's graphical representation.
 * @@@ To Do:
 * @@@ - Fix draw bug. Currently, only background color will render.
 * @@@ - Making these blocks into Shapes, and drawing them directly on
 *          the TimePanel may help
 * @@@ - May want to lose the Canvas parent. Swing and awt sometimes
 *          have issues with each other.
 * @author Peter Bennion
 * @author Yantsey Tsai
 * @version cs56 legacy code project, W14
*/
public class Block extends Canvas
{
    protected boolean on;
    protected Color colorOn;
    protected Color colorOff;
    protected Color brushColor;

    int x, y, w, h;

    /**
        Constructor
    */
    public Block()
    {
        super();

        x = getX();
        y = getY();
        w = getWidth();
        h = getHeight();

	// Default colors
        this.colorOn  = Color.red;
        this.colorOff = Color.orange;
        this.on = false;

        setBackground(colorOff);
        repaint();
    }

    /**
        Constructor
        @param on fill color when on
        @param off fill color when off
    */
    public Block(Color on, Color off)
    {
        super();

        x = getX();
        y = getY();
        w = getWidth();
        h = getHeight();

        this.colorOn = on;
        this.colorOff = off;
        this.on = false;

        setBackground(colorOff);
        repaint();
	
    }

    /**
        Toggles the block's 'on' state. For testing purposes.
    */
    public void toggle()
    {
        on = !on;

        repaint();
    }

    /**
        Takes a char '1' or '0' and tells the block which state it should be in.
        @param c A char representing the desired state, '1' for on,
                 anything else ('0') for off.
    */
    public void input(char c)
    {
	/*
	// Original version
        if(c=='1')
            on = true;
        else on = false;

        if(on){   
	    setBackground(colorOn);
        }
	else{
	    setBackground(colorOff);
        }
	repaint();
	*/
	
	// New verison
	if(c == '1' && on == false){
	    //off but should be on
	    on = true;
	    brushColor = Color.white;
	    setBackground(colorOn);
	    repaint();
	}
	else if(c == '0' && on == true){
	    // on but should be off
	    on = false;
	    setBackground(colorOff);
	    repaint();
	}

	
    }
    
    /**
        Used by the system to render the block. Currently bugged and will only display background color.
        @param g A variable representing graphics. Only available to the system.
    */
    /*   // for the flickering issue, you might want to change the parameters of the loops
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        if(on){
	    brushColor = colorOn;
	    for(int i=0; i<getHeight(); i++){
		g.setColor(brushColor);
		// get darker every 3 loops
		if(i % 3 == 0){
		    brushColor = getDarker();
		}
		g.fillRect(0,i,getWidth(),1);
	    }
        } 
	else{
	    brushColor = colorOff;
	    for(int i=0; i<getHeight(); i++){
		g.setColor(brushColor);
		// get darker every 3 loops
		if(i % 3 == 0){
		    brushColor = getDarker();
		}
		g.fillRect(0,i,getWidth(),1);
	    }
        }
	
    }
    
    private Color getDarker()
    {
	int r = brushColor.getRed();
        int g = brushColor.getGreen();
        int b = brushColor.getBlue();
	
        if(r> 0) r-=5;
        if(g> 0) g-=5;
        if(b> 0) b-=5;

        return new Color(r,g,b);
    }
    
    */

}
