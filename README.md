cs56-utilities-binary-clock
===========================

Description:

A binary clock consists of several blocks that resemble the hour, minute, and second of the current time in binary. To read this binary clock, add the values of the "on" (red) blocks in each column. These values represent the hour, minute, and second of the current time in decimal, or base 10. It's pretty self explanatory when you see the actual Binary Clock GUI.

Documentation
=============
* The "driver class" that runs everything is BinaryClock.java. Basically, this class parses the current time into binary strings, then calls a method to update Block objects themselves using these binary strings. There is a recursive method, called update(), that keeps the time updated. :)

* The TimePanel.java class is the GUI that outputs the Binary Clock on the screen. The blue blocks are "off", and the red blocks are "on". You calculate the time by adding the "on" blocks in each column.

* The Block.java class has code that does the actual drawing. It extends Canvas (java.awt package), and has method paint() that paints each Block object based on its "on" or "off" status. This class only draws, it doesn't do any calculations.

How to Run
==========

cd into the top level (build.xml, src, README.md, etc...)
use "ant" to compile
use "ant run" to run the Binary Clock GUI