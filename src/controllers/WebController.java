package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import model.IAlbumModel;
import model.PhotoAlbumModel;
import views.IView;
import views.WebView;

/**
 * Controller for WebView.
 */
public class WebController implements IAlbumController {

  File commandFile;
  String outputFile;
  IAlbumModel model;
  IView webView;

  /**
   * Constructor for WebController. Takes in file containing commands and name of html file to be
   * output.
   * @param commandFile text file containting commands
   * @param outputFile string name of file to be output
   */
  public WebController(File commandFile, String outputFile) {
    this.commandFile = commandFile;
    this.outputFile = outputFile;
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
      System.out.println(command);
      // checking commands
      switch (command.toUpperCase()) {
        case "#":
        case "\n":
          command = input.next();
          System.out.println("Skipping line # or /n found");
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
          System.out.println(
              "Adding shape" + name + type + xCoordinate + yCoordinate + width + height + color);
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
            System.out.println("Snapshot with description " + description);
          } else {
            this.model.takeSnapshot(description);
            System.out.println("Snapshot with description " + description);
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
    this.webView = new WebView(this.model.getSnapshots(), this.outputFile);
    this.webView.displaySnapshots();
  }

  /**
   * SMOKE TEST DELETE THIS
   *
   * @param args N/A
   */
  public static void main(String[] args) {
    File commandFile = new File("buildings.txt");
    String outputFile = "testController.html";

    try {
      new WebController(commandFile, outputFile).go(new PhotoAlbumModel());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
