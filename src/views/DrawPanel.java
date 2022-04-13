package views;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class DrawPanel extends JPanel {

  @Override
  public void paintComponent(Graphics g ) {
    super.paintComponent(g);
    setBackground(Color.ORANGE);
    g.drawOval(GraphicalView.WIDTH/4, GraphicalView.HEIGHT/4,
        GraphicalView.WIDTH/2, GraphicalView.HEIGHT/6);
    g.setColor(Color.blue);
    g.fillOval(GraphicalView.WIDTH/4, GraphicalView.HEIGHT/4,
        GraphicalView.WIDTH/2, GraphicalView.HEIGHT/6);
  }
}