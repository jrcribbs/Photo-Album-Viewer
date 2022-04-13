package model;

public class Rectangle extends AbstractShape {

  public Rectangle(String name, double xCoordinate, double yCoordinate, double width,
      double height, String color) {
    super(name, "rectangle", xCoordinate, yCoordinate, width, height, color);
  }

  @Override
  public String toString() {
    return "Name: " + this.getName() + "\n"
        + "Type: " + this.getType() + "\n"
        + "Min corner: (" + this.getxCoordinate() + ", " + this.getyCoordinate() + "), "
        + "Width: " + this.getWidth() + ", " + "Height: " + this.getHeight() + ", "
        + "Color: " + "(" + this.getColor() + ")";
  }
}
