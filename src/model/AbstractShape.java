package model;

import java.util.Objects;

/**
 * The type Abstract shape.
 */
public abstract class AbstractShape implements IShape {
  private String name;
  private String type;
  private double xCoordinate;
  private double yCoordinate;
  private double width;
  private double height;
  private String color;
  private int red, green, blue;


  /**
   * Constructor for shape objects.
   *
   * @param name        name of shape
   * @param type        type of shape
   * @param xCoordinate x coordinate of shape
   * @param yCoordinate y coordinate of shape
   * @param width       width/equivalent of shape
   * @param height      height/equivalent
   * @param color       color of shape
   * @throws IllegalArgumentException if name or color is null
   */
  public AbstractShape(String name, String type, double xCoordinate, double yCoordinate,
      double width, double height, String color) throws IllegalArgumentException {
    if (name == null || name.equals("") || name.equals(" ")
        || color == null || color.equals("") || color.equals(" ")) {
      throw new IllegalArgumentException("Values cannot be blank.");
    } else {
    this.name = name;
    this.type = type;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.width = width;
    this.height = height;
    this.color = color;
    String[] rgb = this.color.split(",");
    this.red = Integer.parseInt(rgb[0]);
    this.green = Integer.parseInt(rgb[1]);
    this.blue = Integer.parseInt(rgb[2]);
    }
  }

  @Override
  public void setCoordinates(double newX, double newY) {
    // check if valid move?
    this.xCoordinate = newX;
    this.yCoordinate = newY;
  }

  @Override
  public void setColor(String color) {
    // check if valid color?
    this.color = color;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public double getxCoordinate() {
    return xCoordinate;
  }

  @Override
  public double getyCoordinate() {
    return yCoordinate;
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public String getColor() {
    return color;
  }


  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }

  /**
   * Equals method for shapes. Shapes are considered equal if they have the same name,
   * meaning all shapes are required to have different names.
   * @param o other shape
   * @return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractShape that = (AbstractShape) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }
}
