cs56-utilities-binary-clock
===========================

Description:

This program displays a GUI of a binary clock. A binary clock consists of several blocks that resemble the hour, minute, and second of the current time in binary, or base 2. To read this binary clock, add the values of the "on" (red) blocks in each column. These values represent the hour, minute, and second of the current time in decimal, or base 10. It's pretty self explanatory when you see the actual binary clock GUI.

project history
===============
```
W15 | mbahia | glee | Graphical Binary Clock
W14 | bkiefer13 5pm | yantsey | Graphical Binary Clock
```



Documentation
=============
* The "driver class" that runs everything is `BinaryClock.java`. Basically, this class parses the current time into binary strings, then calls a method to update Block objects themselves using these binary strings. There is a recursive method, called update(), that keeps the time updated. :)

* The `TimePanel.java` class is the GUI that outputs the binary clock on the screen. The blue blocks are "off", and the red blocks are "on". You calculate the time by adding the "on" blocks in each column.

* The `Block.java` class has code that does the actual drawing. It extends Canvas (java.awt package), and has method paint() that paints each Block object based on its "on" or "off" status. This class only draws, it doesn't do any calculations.

How to Run
==========

* First, cd into the top level directory (where build.xml, src, README.md, etc... are)
* Use `ant` to compile
* Use `ant run` to run the binary clock GUI

Other Notes
=

* Currently, the gradient is done by using a for loop to pain several rectangles over each other, with each successive rectangle decreasing 1 pixel in height, and getting darker by 1 RGB value.
* There may be a way to use GradientPaint, but that involves refactoring the entire project and changing the Block class to implement Shape rather than extend Canvas. You would have to change code in every single class.
