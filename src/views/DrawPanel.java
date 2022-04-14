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

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.WHITE);

    g.drawOval(GraphicalView.WIDTH/4, GraphicalView.HEIGHT/4,
        GraphicalView.WIDTH/2, GraphicalView.HEIGHT/6);
    g.setColor(Color.blue);
    g.fillOval(GraphicalView.WIDTH/4, GraphicalView.HEIGHT/4,
        GraphicalView.WIDTH/2, GraphicalView.HEIGHT/6);
  }
}