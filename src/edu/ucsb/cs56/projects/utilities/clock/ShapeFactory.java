package edu.ucsb.cs56.projects.utilities.clock;
import java.awt.Color;
import java.awt.Canvas;

public interface ShapeFactory {
    public Shape build(Color on, Color off, Color BGcolor);
}
