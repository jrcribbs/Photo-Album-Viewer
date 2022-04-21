package views;

import java.awt.*;
import java.util.List;
import javax.swing.*;

import model.IShape;
import model.ISnapshot;

/**
 * The type Draw panel.
 */
class DrawPanel extends JPanel {
  ISnapshot snapshot;
  List<IShape> shapes;

  /**
   * Instantiates a new Draw panel.
   *
   * @param snapshot the snapshot
   */
  public DrawPanel(ISnapshot snapshot) {
    this.snapshot = snapshot;
    this.shapes = this.snapshot.getShapes();
  }

  /**
   * Sets current snapshot to be drawn.
   *
   * @param snapshot the snapshot
   */
  public void setSnapshot(ISnapshot snapshot) {
    this.snapshot = snapshot;
    this.shapes = this.snapshot.getShapes();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    setBackground(Color.white);

    for (IShape shape : shapes) {
      switch (shape.getType()) {
        case "oval":
          // grabbing oval data and casting parameters from doubles to ints
          g.drawOval((int)shape.getxCoordinate(), (int)shape.getyCoordinate(),
              (int)shape.getWidth(), (int)shape.getHeight());
          g.setColor(new Color(shape.getRed(), shape.getGreen(), shape.getBlue()));
          g.fillOval((int)shape.getxCoordinate(), (int)shape.getyCoordinate(),
              (int)shape.getWidth(), (int)shape.getHeight());
          break;
        case "rectangle":
          // grabbing oval data and casting parameters from doubles to ints
          g.drawRect((int)shape.getxCoordinate(), (int)shape.getyCoordinate(),
              (int)shape.getWidth(), (int)shape.getHeight());
          g.setColor(new Color(shape.getRed(), shape.getGreen(), shape.getBlue()));
          g.fillRect((int)shape.getxCoordinate(), (int)shape.getyCoordinate(),
              (int)shape.getWidth(), (int)shape.getHeight());
          break;
      }
    }
  }
}