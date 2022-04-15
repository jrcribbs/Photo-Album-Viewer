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
  public static void main(String[] args) {
    String controllerType = null;
    File commandFile = null;
    String outputFile = null;
    int xMax;
    int yMax;

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
        }
      }
      // testing captures
      System.out.println(controllerType);
      System.out.println(commandFile);
      System.out.println(outputFile);

      // passing args to WebController
      try {
        new WebController(commandFile, outputFile).go(new PhotoAlbumModel());
      } catch (IOException e) {
        e.printStackTrace();
      }

    } else if (controllerType.equalsIgnoreCase("graphical")) { // graphical view

      for (int i = 0; i < args.length; i++) {
        if (args[i].equalsIgnoreCase("-view")
            || args[i].equalsIgnoreCase("-v")) {
          xMax = Integer.parseInt(args[i + 2]); // need default values and check not out of range
          yMax = Integer.parseInt(args[i + 3]);
        }
      }
    }
  }
}

