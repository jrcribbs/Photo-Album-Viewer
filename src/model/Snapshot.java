package model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The type Snapshot.
 */
public class Snapshot implements ISnapshot {

  private String id;
  private Date timestamp = new Date();
  private List<IShape> shapes;
  private String description;


  /**
   * Instantiates a new Snapshot.
   *
   * @param id_number   the id number
   * @param shapes      the shapes
   * @param description the description
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Snapshot(int id_number, List<IShape> shapes, String description) {
      this.id = "Snapshot ID #" + id_number;
      this.shapes = shapes;
      this.description = description;
  }

  @Override
  public String shapesString() {
    StringBuilder shapeString = new StringBuilder();
    for (IShape shape : this.shapes) {
      shapeString.append(shape.toString()).append("\n\n");
    }
    return shapeString.toString();
  }

  @Override
  public String getID() {
    return this.id;
  }

  @Override
  public Date getTimestamp() {
    return timestamp;
  }

  @Override
  public List<IShape> getShapes() {
    return Collections.unmodifiableList(this.shapes);
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String toString() {
    return "Snapshot ID: " + this.id + "\n"
        + "Timestamp: " + this.timestamp + "\n"
        + "Description: " + this.description + "\n"
        + "Shape information:" + "\n"
        + shapesString();
  }

}
