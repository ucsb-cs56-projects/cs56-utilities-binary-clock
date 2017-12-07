cs56-utilities-binary-clock
===========================

Description:

This program displays a GUI of a binary clock. A binary clock consists of several blocks that resemble the hour, minute, and second of the current time in binary, or base 2. To read this binary clock, add the values of the "on" (red) blocks in each column. These values represent the hour, minute, and second of the current time in decimal, or base 10. It's pretty self explanatory when you see the actual binary clock GUI.
#Pictures
Here is an example of a digital clock versus a binary clock. Both clocks read 12:15:45. 
![example binary clock 2] (https://cloud.githubusercontent.com/assets/22383747/20042784/cd345452-a434-11e6-9ddb-1632acc35106.jpg)

This is our default binary clock program. 
![github_image](https://cloud.githubusercontent.com/assets/22383747/20818587/88005a6c-b7e3-11e6-92ea-8dffea11142c.png)

#Project History
===============
```
F17 | shudaniel, sitaochen   | Graphical Binary Clock
F16 | KevinL123, mschanteltc | Graphical Binary Clock
W15 | JohnUCSB, mbahia (4pm) | Graphical Binary Clock
W14 | bkiefer13, yantsey (5pm) | Graphical Binary Clock
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

F17 Final Remarks
=================

* The BinaryClock.java contains the JPanel that contains the clock itself, which is the main display for the Binary Clock. The BinaryClockGUI.java contains the menubar options and the JFrame that holds the clock. BinaryClock implements Runnable and is independent of BinaryClockGUI. It can run by itself as long as a new thread is created and start() is called. 

* The shape of the blocks are created using the factory pattern. The ShapeFactory.java file handles the creation of different shapes of blocks independent of the BinaryClock object.


	Things to keep in mind:

* Currently, for BinaryClockGUI to change how the clock looks (such as the color), it stops the current BinaryClock thread and creates a new BinaryClock object with the new settings.

* The size of each block is not the same. They are not set by us. They layout manager automatically determined each size.

* We changed the names of the files. BinaryClock.java used to be called TimePanel.java, and BinaryClockGUI.java used to be called BinaryClock.java. Keep this in mind when reading old comments or issues.   

* In the BinaryClock.java run() function, there this line of code: System.out.println("Thread: " + this.hashCode());
Professor Conrad wants to leave this in for demo purposes in lecture

F16 Final Remarks
=================

* The project that we editted was a binary clock, representing a ticking clock that would sum up to its value for hours, minutes, and seconds. In the preset settings, the blue blocks are considered “off” while the red blocks are considered “on.” By adding the values of each red block in each column, you get the value of that particular section. If you turn on the volume of your computer, you are able to hear the ticking sound of the clock as well. 

	Things to keep in mind:

* When you expand the window size, the blocks will adjust accordingly. However when you try to shrink the window, the blocks will retain the largest size it had when expanding. Also, the ticking sound is slightly off.
* F16 (us) commented out the gradient in the Block.java file because we thought that it was not that pleasing to the eye.
* Look up GUIs and read up on border layout and flow layout to get a better understanding of how the blocks are positioned and created. 
* I would advise to focus on BinaryClock.java since thats where the bulk of the code for the application is. Timepanel.java is where you would want to go to be able to change the position of the componenets of the application.
* The size of the box vary with the size of the text around the boxes. It would be good to be able to seperate the borderlayouts between the text and the boxes themselves.

