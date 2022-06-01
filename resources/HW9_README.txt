The entry point for my program is, of course, PhotoAlbumMain. PhotoAlbumMain parses the
arguments from the command line and, based on which view the user asks for, passes them to WebViewController or 
GraphicalController. These two controllers then read instructions from the "command file", pass
those instructions to the model, then whatever snapshots the model returns at the end of the command file
is passed to the proper view. I split the controllers up because WebView and GraphicalView need slightly
different parameters (namely an output file in WebViews case) and I thought it would be easier to split their
controllers in this way. Throughout I tried to keep the MVC design principle at the forefront. 

While building this program I did make some changes to my model as the need arose. I added a log of actions
taken by the model for posterity (this may be needed in future versions of this program). I also I realized 
I didn't make an Interface for my model, so I added one. Originally, AbstractShape simply took in a String 
for it's color, such as "100, 50, 255". For the HTML output this was no issue, however I need to get the individual 
RGB values for things to translate properly to Graphical view.
 
Luckily, because of the format of the string I was able to split on the commas and grab each of these values
in the AbstractShape constructor. I made getters for each, and were able to call these when needed in 
GraphicalView. Finally, I also made some small changes to my Snapshot class. I added a getter for the description
(critical in both views) and also changed the format of the ID to a string (also necessary to properly output
this value in both views).
