package edu.ucsb.cs56.projects.utilities.clock;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import edu.ucsb.cs56.projects.utilities.clock.GeneralPathWrapper;
import edu.ucsb.cs56.projects.utilities.clock.ShapeTransforms;

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
public class Circle extends Shape
{
    

    int x, y, r;

    /**
        Constructor
    */
    /*
    public Circle()
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
    */
    /**
        Constructor
        @param on fill color when on
        @param off fill color when off
    */
    public Circle(Color on, Color off, Color bg)
    {
        super(on, off, bg);

        //x = getX();
        //y = getY();
        //r = getWidth()/2;
        //h = getHeight();

        //setBackground(colorOff);
        //repaint();
	
    }

    public void paint(Graphics g){
	if(on){
	    g.setColor(colorOn);
	}
	else{
	    g.setColor(colorOff);
	}
	g.fillOval(0,0,getWidth(),getHeight());
	
    }
    /**
        Toggles the block's 'on' state. For testing purposes.
    */
    public void toggle()
    {
        on = !on;

        repaint();
    }


}
