package edu.ucsb.cs56.projects.utilities.clock;
import java.awt.Color;
import java.awt.Canvas;

public class CircleFactory implements ShapeFactory{
    public Shape build(Color on, Color off, Color bg){
	return new Circle(on, off, bg);
    }

}
