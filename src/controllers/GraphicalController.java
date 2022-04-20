package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import model.IAlbumModel;
import model.PhotoAlbumModel;
import views.GraphicalView;
import views.IView;

public class GraphicalController implements IAlbumController {
  File commandFile;
  int xMax, yMax;
  IAlbumModel model;
  IView graphicalView;

  public GraphicalController(File commandFile, int xMax, int yMax) {
    this.commandFile = commandFile;
    this.xMax = xMax;
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
          System.out.println("Moving shape " + name + xCoordinate + yCoordinate);
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
          System.out.println("Resize shape " + name + width + height);
          // checking if end of file, moving scanner pointer
          if (input.hasNext()) {
            command = input.next();
          }
          break;
        case "REMOVE":
          name = input.next();
          this.model.removeShape(name);
          System.out.println("Remove shape " + name);
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
    this.graphicalView = new GraphicalView(this.model.getSnapshots(), this.xMax, this.yMax);
    this.graphicalView.displaySnapshots();
  }
}

