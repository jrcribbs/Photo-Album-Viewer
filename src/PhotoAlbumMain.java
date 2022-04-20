import controllers.GraphicalController;
import controllers.WebController;
import java.io.File;
import java.io.IOException;
import model.PhotoAlbumModel;

/**
 * Main for Photo Album Program.
 */
public class PhotoAlbumMain {

  /**
   * Main for Photo Album Program.
   */
  public static void main(String[] args) throws IOException {
    String controllerType = null;
    File commandFile = null;
    String outputFile = null;
    int xMax= 1000; // DEFAULT VALUE
    int yMax = 1000;

    // looping through args and capturing input
    for (int i = 0; i < args.length; i++) {
      // checking view type
      if (args[i].equalsIgnoreCase("-view")
          || args[i].equalsIgnoreCase("-v")) {
        controllerType = args[i + 1];
        // finding file containing commands
      } else if (args[i].equalsIgnoreCase("-in")) {
        commandFile = new File(args [i + 1]);
      }
    }
    // checking which view was selected and gathering view-specific input
    if (controllerType.equalsIgnoreCase("web")) { // web view
      for (int i = 0; i < args.length; i++) {
        if (args[i].equalsIgnoreCase("-out")) {
          outputFile = args[i + 1];
          // grabbing screen size if x & y max specified by user
          // min amount of commands for Web is 6
        } else if ((args[i].equalsIgnoreCase("-view")
            || args[i].equalsIgnoreCase("-v")) && args.length > 6) {
            xMax = Integer.parseInt(args[i + 2]);
            yMax = Integer.parseInt(args[i + 3]);
          }
        }
        // testing captures
        System.out.println(controllerType);
        System.out.println(commandFile);
        System.out.println(outputFile);
        System.out.println(args.length);

        // passing args to WebController
        try {
          new WebController(commandFile, outputFile, xMax, yMax).go(new PhotoAlbumModel());
        } catch (IOException e) {
          e.printStackTrace();
        }
    } else if (controllerType.equalsIgnoreCase("graphical")) { // graphical view
      for (int i = 0; i < args.length; i++) {
        // grabbing screen size if x & y max specified by user
        // min amount of commands for Graphical is 4
        if ((args[i].equalsIgnoreCase("-view")
            || args[i].equalsIgnoreCase("-v")) && args.length > 4) {
          xMax = Integer.parseInt(args[i + 2]);
          yMax = Integer.parseInt(args[i + 3]);
        }
      }
      // testing captures
      System.out.println(controllerType);
      System.out.println(commandFile);
      System.out.println(args.length);

      // passing args to Graphical Controller
      try {
        new GraphicalController(commandFile, xMax, yMax).go(new PhotoAlbumModel());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

