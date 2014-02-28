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

        this.colorOn  = Color.red;
        this.colorOff = Color.blue;
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
        if(c=='1')
            on = true;
        else on = false;

        if(on){   
	    repaint();
	    setBackground(colorOn);
        }
	else{
	    repaint();
	    setBackground(colorOff);
        }

	repaint();
    }

    /**
        Used by the system to render the block. Currently bugged and will only display background color.
        @param g A variable representing graphics. Only available to the system.
    */
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //GradientPaint gradient;
	System.out.println("paint comp was called");

        if(on)
        {
	    //setBackground(colorOn);
	    brushColor = colorOn;
	    for(int i=0; i<getHeight(); i++)
	    {
		g.setColor(brushColor);
		brushColor = getDarker();
		g.fillRect(0,i,getWidth(),1);
	    }
        } else
        {
	    //setBackground(colorOff);
	    brushColor = colorOff;
	    for(int i=0; i<getHeight(); i++)
	    {
		g.setColor(brushColor);
		brushColor = getDarker();
		g.fillRect(0,i,getWidth(),1);
	    }
        }

	
        /*gradient = new GradientPaint(x + (w/2), y, brushColor, x + (w/2), y + h, Color.BLACK);

        g2d.setPaint(gradient);
        g2d.fillRect(x+5, y+5, w-10, h-10);*/
    }

    private Color getDarker()
    {
	int r = brushColor.getRed();
        int g = brushColor.getGreen();
        int b = brushColor.getBlue();
	
        if(r> 0) r--;
        if(g> 0) g--;
        if(b> 0) b--;

        return new Color(r,g,b);
    }
    


}
