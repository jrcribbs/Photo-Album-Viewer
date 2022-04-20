package model;

import java.awt.Color;

/**
 * Interface for Shape objects.
 */
public interface IShape {

  /**
   * Move shape to designated coordinates.
   *
   * @param newX the new x position
   * @param newY the new y position
   */
  void setCoordinates(double newX, double newY);

  /**
   * Change color of shape.
   *
   * @param color the new color
   */
  void setColor(String color);

  /**
   * Gets name.
   *
   * @return name of shape
   */
  String getName();

  /**
   * Gets type.
   *
   * @return type of shape
   */
  String getType();

  /**
   * Gets x coordinate.
   *
   * @return the x coordinate
   */
  double getxCoordinate();

  /**
   * Gets y coordinate.
   *
   * @return the y coordinate
   */
  double getyCoordinate();

  /**
   * Gets width or equivalent value for ovaloid shapes.
   *
   * @return the width/equivalent
   */
  double getWidth();

  /**
   * Gets height or equivalent for ovaloid shapes.
   *
   * @return the height/equivalent
   */
  double getHeight();

  /**
   * Gets color.
   *
   * @return the color
   */
  String getColor();

  /**
   * Gets red value for shape.
   *
   * @return the red value
   */
  int getRed();

  /**
   * Gets green value for shape.
   *
   * @return the green value
   */
  int getGreen();

  /**
   * Gets blue value for shape.
   *
   * @return the blye value
   */
  int getBlue();

  /**
   * To String for shapes. Shape objects are required to individually override toString()
   * @return shape string
   */
  String toString();
}


