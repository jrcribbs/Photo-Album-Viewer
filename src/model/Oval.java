package model;

public class Oval extends AbstractShape {

  public Oval(String name, double xCoordinate, double yCoordinate, double width,
      double height, String color) {
    super(name, "oval", xCoordinate, yCoordinate, width, height, color);
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + this.getType() + "\n"
        + "Center: (" + this.getxCoordinate() + ", " + this.getyCoordinate() + "), "
        + "X Radius: " + this.getWidth() + ", " + "Y Radius: " + this.getHeight() + ", "
        + "Color: " + "(" + this.getColor() + ")";
  }

}
