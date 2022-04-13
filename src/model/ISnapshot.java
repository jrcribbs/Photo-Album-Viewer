package model;

import java.util.Date;
import java.util.List;

/**
 * Snapshot interface
 */
public interface ISnapshot {

  /**
   * Gets a string of just shape objects in current snapshot.
   *
   * @return the string of shapes
   */
  public String shapesString();

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getID();

  /**
   * Gets timestamp.
   *
   * @return the timestamp
   */
  public Date getTimestamp();

  /**
   * Gets list of shape objects in Snapshot
   * @return list of shapes
   */
  public List<IShape> getShapes();

  /**
   * Gets description of Snapshot
   * @return description
   */
  public String getDescription();
}
