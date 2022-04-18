package views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import model.IShape;
import model.ISnapshot;

class DrawPanel extends JPanel {
  ISnapshot snapshot;
  List<IShape> shapes;

  public DrawPanel(ISnapshot snapshot) {
    this.snapshot = snapshot;
    this.shapes = this.snapshot.getShapes();
  }

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
          g.setColor(Color.blue);
          g.fillOval((int)shape.getxCoordinate(), (int)shape.getyCoordinate(),
              (int)shape.getWidth(), (int)shape.getHeight());
          break;
        case "rectangle":
          // grabbing oval data and casting parameters from doubles to ints
          g.drawRect((int)shape.getxCoordinate(), (int)shape.getyCoordinate(),
              (int)shape.getWidth(), (int)shape.getHeight());
          g.setColor(Color.red);
          g.fillRect((int)shape.getxCoordinate(), (int)shape.getyCoordinate(),
              (int)shape.getWidth(), (int)shape.getHeight());
          break;
      }
    }
  }
}