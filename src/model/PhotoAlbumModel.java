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
    } else if (type.equalsIgnoreCase("rectangle")) {
      this.shapes.add(new Rectangle(name, xCoordinate, yCoordinate, width, height, color));
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
  }

  @Override
  public void moveShape(String name, double newX, double newY)
      throws IllegalArgumentException {
    // check if shape on canvas
    for (IShape shape : this.shapes) {
      if (name.equalsIgnoreCase(shape.getName())) {
        shape.setCoordinates(newX, newY);
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
      snapIDs.add("Snap #" + snap.getID());
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
  public String printSnapshots() {
    return "Printing Snapshots\n"
        + getSnapshotStrings();
  }

  @Override
  public void reset() {
    this.shapes = new ArrayList<>();
    this.snapshots = new ArrayList<>();
  }

}
