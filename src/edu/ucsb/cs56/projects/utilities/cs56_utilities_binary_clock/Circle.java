package edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock;

public class Circle
        extends java.awt.geom.Ellipse2D.Double
		    implements java.awt.Shape
{
    /**
     * Constructor for objects of class Circle
     * @param x    x coordinate of center of circle
     * @param y    y coordinate of center of circle
     * @param r    radius of circle
     */
    public Circle(double x, double y, double r)
    {
	// invoke the super class constructor,
	// i.e. the one for Ellipse2D.Double, which takes
	// upper-left-x, upper-left-y (of the bounding box)
	// width, and height

	super( x - r, y - r,   /* upper left corner of bounding box */
	       r * 2,  r * 2); /* width and height are double the radius */
    }
}
