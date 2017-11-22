package edu.ucsb.cs56.projects.utilities.clock;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import edu.ucsb.cs56.projects.utilities.clock.GeneralPathWrapper;
import edu.ucsb.cs56.projects.utilities.clock.ShapeTransforms;
import java.awt.Canvas;

public class Shape extends Canvas{
    protected boolean on;
    protected Color colorOn;
    protected Color colorOff;
    protected Color brushColor;
    protected Color BGcolor;

    public Shape(Color on, Color off, Color BG){
	this.colorOn = on;
	this.colorOff = off;
	this.on = false;
	setBackground(BG);
    }

    public void input(char c){
	if(c == '1' && on == false){
	    //off but should be on
	    on = true;
	    brushColor = Color.white;
	    //setBackground(colorOn);
	    repaint();
	}
	else if(c == '0' && on == true){
	    // on but should be off
	    on = false;
	    //setBackground(colorOff);
	    repaint();
	}
    }
}
