package model;

import java.util.ArrayList;
import java.util.List;

public interface IAlbumModel {

  /**
   * Add shape to canvas. New shapes must be unique -> two shapes cannot have the same name.
   *
   * @param name        the name of shape
   * @param type        the type of shape
   * @param xCoordinate the x coordinate
   * @param yCoordinate the y coordinate
   * @param width       the width
   * @param height      the height
   * @param color       the color
   * @throws IllegalArgumentException illegal argument exception if new shape is not unique, or
   *                                  if shape type is not found
   */
  void addShape(String name, String type, double xCoordinate, double yCoordinate,
      double width, double height, String color) throws IllegalArgumentException;

  /**
   * Take snapshot of current state of canvas.
   *
   * @param description the description for the snapshot
   */
  void takeSnapshot(String description);

  /**
   * Moves shape on canvas. Checks to see if input shape is currently on canvas, and updates
   * coordinates if it is.
   *
   * @param name shape to be changed
   * @param newX new X coordinate
   * @param newY new Y coordinate
   * @throws IllegalArgumentException if shape not found on canvas
   */
  void moveShape(String name, double newX, double newY) throws IllegalArgumentException;

  /**
   * Changes color of designated shape. Checks if input shape is currently on canvas, and updates
   * its color if found.
   *
   * @param name  shape to be changed
   * @param color new color
   * @throws IllegalArgumentException if shape not found on canvas
   */
  void changeColor(String name, String color) throws IllegalArgumentException;

  /**
   * Remove shape from canvas.
   *
   * @param name name of shape to be removed
   * @throws IllegalArgumentException if shape not found
   */
  public void removeShape(String name) throws IllegalArgumentException;

  /**
   * Gets list of shapes on canvas
   * @return shapes on current canvas
   */
  List<IShape> getShapes();

  /**
   * Gets list of snapshots
   * @return shapes on current canvas
   */
  List<ISnapshot> getSnapshots();

  /**
   * Gets list of snapshot IDs.
   *
   * @return snapshot ID list
   */
  String getSnapshotIDList();

  /**
   * Gets string version of all snapshots taken so far.
   *
   * @return the snapshot strings
   */
  StringBuilder getSnapshotStrings();

  /**
   * Print string version of all snapshots.
   *
   * @return the string
   */
  String printSnapshots();

  /**
   * Resets canvas to initial state.
   */
  public void reset();

}
