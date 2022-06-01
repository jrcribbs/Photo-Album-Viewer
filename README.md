# Photo-Album-Viewer

Reads text-file data from Terminal, automatically generates both HTML and graphical view with interactive UI for given input 
(see buildings.txt and demo_input.txt in src for example input)

The entry point for my program is PhotoAlbumMain. PhotoAlbumMain parses the
arguments from the command line and, based on which view the user asks for, passes them to WebViewController or 
GraphicalController. These two controllers then read instructions from the "command file", pass
those instructions to the model, then whatever snapshots the model returns at the end of the command file
is passed to the proper view. I split the controllers up because WebView and GraphicalView need slightly
different parameters (namely an output file in WebViews case) and I thought it would be easier to split their
controllers in this way. Throughout I tried to keep the MVC design principle at the forefront. 
