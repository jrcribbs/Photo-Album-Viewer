package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import model.IAlbumModel;
import model.PhotoAlbumModel;
import views.IView;
import views.WebView;

public class WebController implements IAlbumController {
  File commandFile;
  String outputFile;
  IAlbumModel model;
  IView webView;

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
          System.out.println("Adding shape" + name + type + xCoordinate + yCoordinate + width + height + color);
          break;
        case "MOVE":
          name = input.next();
          xCoordinate = input.nextInt();
          yCoordinate = input.nextInt();
          this.model.moveShape(name, xCoordinate, yCoordinate);
          break;
        case "SNAPSHOT":
          if (Objects.equals(input.next(), "\n")) {
            this.model.takeSnapshot("");
          } else {
            // building description to end of line
            StringBuilder description = new StringBuilder();
            while (!Objects.equals(input.next(), "\n")) {
              description.append(input.next()).append(" "); // including spaces between words
            }
            // taking snapshot with full description
            this.model.takeSnapshot(description.toString());
          }
        case "RESIZE":
          name = input.next();
          width = input.nextInt();
          height = input.nextInt();
          this.model.resizeShape(name, width, height);
          break;
        case "REMOVE":
          name = input.next();
          this.model.removeShape(name);
          break;
        default:
          command = input.next();
      }
    }
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
