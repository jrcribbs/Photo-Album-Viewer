package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Model for Photo Album.
 */
public class PhotoAlbumModel implements IAlbumModel{

  private List<IShape> shapes = new ArrayList<>();
  private List<ISnapshot> snapshots = new ArrayList<>();
  private StringBuilder log = new StringBuilder();


  /**
   * Instantiates a new Photo album, no parameters needed.
   */
  public PhotoAlbumModel() {
  }

  @Override
  public void addShape(String name, String type, double xCoordinate, double yCoordinate,
      double width, double height, String color) throws IllegalArgumentException {
    // checking if new shape is unique
    for (IShape shape : this.shapes) {
      if (name.equalsIgnoreCase(shape.getName())) {
        throw new IllegalArgumentException("New shapes must have a unique name.");
      }
      // else adding to canvas
    } if (type.equalsIgnoreCase("oval")) {
      this.shapes.add(new Oval(name, xCoordinate, yCoordinate, width, height, color));
      this.log.append("Added Oval with following details: Name: ")
          .append(name + " ")
          .append("Coordinates: ")
          .append(xCoordinate + " ")
          .append(yCoordinate + " ")
          .append("Dimensions: ")
          .append(width + " ")
          .append(height + " ")
          .append("Color: ")
          .append(color + "\n");
    } else if (type.equalsIgnoreCase("rectangle")) {
      this.shapes.add(new Rectangle(name, xCoordinate, yCoordinate, width, height, color));
      this.log.append("Added Rectangle with following details: Name: ")
          .append(name + " ")
          .append("Coordinates: ")
          .append(xCoordinate + " ")
          .append(yCoordinate + " ")
          .append("Dimensions: ")
          .append(width + " ")
          .append(height + " ")
          .append("Color: ")
          .append(color + "\n");
    } else {
      throw new IllegalArgumentException("Shape type not found.");
    }
  }

  @Override
  public void takeSnapshot(String description) {
    // making shape list copy
    List<IShape> shapeCopy = new ArrayList<>(this.shapes);
    // adding to snapshots using copied list
    this.snapshots.add(new Snapshot((this.snapshots.size() + 1), shapeCopy, description));
    this.log.append("Took a Snapshot\n");
  }

  @Override
  public void moveShape(String name, double newX, double newY)
      throws IllegalArgumentException {
    // check if shape on canvas
    for (IShape shape : this.shapes) {
      if (name.equalsIgnoreCase(shape.getName())) {
        shape.setCoordinates(newX, newY);
        this.log.append("Moved Shape ")
            .append(name)
            .append(" to ")
            .append(newX + " ")
            .append(newY +"\n");
        return;
      }
    }
    throw new IllegalArgumentException("Shape not found on canvas.");
  }

  @Override
  public void resizeShape(String name, double newWidth, double newHeight)
      throws IllegalArgumentException {
    // check if shape on canvas
    for (IShape shape : this.shapes) {
      if (name.equalsIgnoreCase(shape.getName())) {
        shape.setCoordinates(newWidth, newHeight);
        this.log.append("Resized Shape ")
            .append(name)
            .append(" to ")
            .append(newWidth + " ")
            .append(newHeight +"\n");
        return;
      }
    }
    throw new IllegalArgumentException("Shape not found on canvas.");
  }

  @Override
  public void changeColor(String name, String color) throws IllegalArgumentException {
    // check if shape on canvas
    for (IShape shape : this.shapes) {
      if (name.equalsIgnoreCase(shape.getName())) {
        shape.setColor(color);
        this.log.append("Changed color of shape ")
            .append(name)
            .append(" to ")
            .append(color +"\n");
        return;
      }
    }
    throw new IllegalArgumentException("Shape not found on canvas.");
  }

  @Override
  public void removeShape(String name) throws IllegalArgumentException{
    for (IShape shape : this.shapes) {
      if (name.equalsIgnoreCase(shape.getName())) {
        this.shapes.remove(shape);
        this.log.append("Removed shape ")
            .append(name + "\n");
        return;
      }
    }
    throw new IllegalArgumentException("Shape not found on canvas.");
  }

  @Override
  public List<IShape> getShapes() {
    return this.shapes;
  }

  @Override
  public List<ISnapshot> getSnapshots() {
    return this.snapshots;
  }

  @Override
  public String getSnapshotIDList() {
    List<String> snapIDs = new ArrayList<>();
    for (ISnapshot snap : this.snapshots) {
      snapIDs.add("" + snap.getID());
    }
    return "List of snapshots before reset: " + snapIDs;
  }

  @Override
  public StringBuilder getSnapshotStrings() {
    StringBuilder snapIDs = new StringBuilder();
    for (ISnapshot snap : this.snapshots) {
      snapIDs.append(snap.toString());
    }
    return snapIDs;
  }

  @Override
  public StringBuilder getLog() {
    return this.log;
  }

  @Override
  public String printSnapshots() {
    return "Printing Snapshots\n"
        + getSnapshotStrings();
  }

  @Override
  public void reset() {
    this.shapes = new ArrayList<>();
    this.snapshots = new ArrayList<>();
    this.log.append("Reset Photo Album\n");
  }

}
