package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import model.IAlbumModel;
import views.IView;
import views.WebView;

/**
 * Controller for WebView.
 */
public class WebController implements IAlbumController {

  File commandFile;
  String outputFile;
  int xMax, yMax;
  IAlbumModel model;
  IView webView;

  /**
   * Constructor for WebController. Takes in file containing commands and name of html file to be
   * output.
   * @param commandFile text file containing commands
   * @param outputFile string name of file to be output
   */
  public WebController(File commandFile, String outputFile, int xMax, int yMax) {
    this.commandFile = commandFile;
    this.outputFile = outputFile;
    this. xMax = xMax;
    this.yMax = yMax;
  }

  @Override
  public void go(IAlbumModel model) throws IOException {
    this.model = model;
    Scanner input = new Scanner(commandFile);
    String command = input.next();
    String name, type, color;
    int xCoordinate, yCoordinate, width, height;

    // scanning file
    while (input.hasNext()) {
      // checking commands
      switch (command.toUpperCase()) {
        case "#":
        case "\n":
          command = input.next();
          break;
        case "SHAPE":
          name = input.next();
          type = input.next();
          xCoordinate = input.nextInt();
          yCoordinate = input.nextInt();
          width = input.nextInt();
          height = input.nextInt();
          color = input.next() + "," + input.next() + "," + input.next();
          this.model.addShape(name, type, xCoordinate, yCoordinate, width, height, color);

          // checking if end of file, moving scanner pointer
          if (input.hasNext()) {
            command = input.next();
          }
          break;
        case "MOVE":
          name = input.next();
          xCoordinate = input.nextInt();
          yCoordinate = input.nextInt();
          this.model.moveShape(name, xCoordinate, yCoordinate);
          // checking if end of file, moving scanner pointer
          if (input.hasNext()) {
            command = input.next();
          }
          break;
        case "SNAPSHOT":
          String description = input.nextLine();
          if (description == null) {
            this.model.takeSnapshot(" ");
          } else {
            this.model.takeSnapshot(description);
          }
          // checking if end of file, moving scanner pointer
          if (input.hasNext()) {
            command = input.next();
          }
          break;
        case "RESIZE":
          name = input.next();
          width = input.nextInt();
          height = input.nextInt();
          this.model.resizeShape(name, width, height);

          // checking if end of file, moving scanner pointer
          if (input.hasNext()) {
            command = input.next();
          }
          break;
        case "REMOVE":
          name = input.next();
          this.model.removeShape(name);

          // checking if end of file, moving scanner pointer
          if (input.hasNext()) {
            command = input.next();
          }
          break;
        default:
          // checking if end of file, moving scanner pointer
          if (input.hasNext()) {
            command = input.next();
          }
      }
    }
    // outputting file
    this.webView = new WebView(this.model.getSnapshots(), this.outputFile, this.xMax, this.yMax);
    this.webView.displaySnapshots();
  }
}
