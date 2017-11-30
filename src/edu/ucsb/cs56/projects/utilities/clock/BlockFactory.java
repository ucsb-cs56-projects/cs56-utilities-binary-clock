package edu.ucsb.cs56.projects.utilities.clock;
import java.awt.Color;
import java.awt.Canvas;

public class BlockFactory implements ShapeFactory{
    public Shape build(Color on, Color off, Color BGcolor_){
	return new Block(on, off, BGcolor_);
    }
    
}
