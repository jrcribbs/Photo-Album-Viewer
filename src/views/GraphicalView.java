package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GraphicalView extends JFrame implements IView {
  public static final int WIDTH = 500;
  public static final int HEIGHT = 500;
  JPanel buttonPanel = new JPanel();
  JPanel picturePanel = new DrawPanel();
  // snapshot list?

  public GraphicalView() {
    // default settings for frame
    super();
    setSize(WIDTH, HEIGHT);
    setTitle("Graphical View Photo Album");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // setting button styles
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    buttonPanel.setLayout(new GridLayout(1, 0));

    // creating buttons, adding functionality, and adding to frame
    JButton prevButton = new JButton("Previous");
    // display index?
    JButton selectButton = new JButton("Select");
    JButton nextButton = new JButton("Next");
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(new MyCloseListener());

    buttonPanel.add(prevButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(closeButton);

    add(buttonPanel, BorderLayout.SOUTH);
    add(picturePanel);
  }

  public void drawPicture() {
    // takes in snap object (index determined by buttons above, start at 0)
    // pass info to DrawPanel
    // update this.picturePanel
  }
}
