package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import model.ISnapshot;
import model.PhotoAlbumModel;

public class GraphicalView extends JFrame implements IView {
  public static final int WIDTH = 500;
  public static final int HEIGHT = 500;
  JPanel buttonPanel = new JPanel();
  JPanel picturePanel;
  List<ISnapshot> snaps;

  public GraphicalView(List<ISnapshot> snaps) {
    super();
    this.snaps = snaps;
    this.picturePanel = new DrawPanel(this.snaps.get(0)); // ONLY SINGLE SNAPSHOT PASSED IN

    // default settings for frame
    setSize(WIDTH, HEIGHT);
    setTitle("Graphical View Photo Album");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // setting button styles
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    buttonPanel.setLayout(new GridLayout(1, 0));

    // creating buttons, adding functionality, and adding to frame
    JButton prevButton = new JButton("Previous");
    // display index?
    JPopupMenu selectButton = new JPopupMenu("Select");
    for (ISnapshot snap : this.snaps) {
      selectButton.add(snap.getID());
    }
    JButton nextButton = new JButton("Next");
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(new MyCloseListener());

    buttonPanel.add(prevButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(closeButton);

    add(buttonPanel, BorderLayout.SOUTH);

  }

  private JList makeDropdown() {
    return null;
  }


  @Override
  public void displaySnapshots() {
    // takes in snap object (index determined by buttons above, start at 0)
    // pass info to DrawPanel
    // update this.picturePanel
    add(picturePanel);
    this.setVisible(true);
  }

  /**
   * SMOKE TEST DELETE THIS
   * @param args N/A
   */
  public static void main(String[] args) {
    PhotoAlbumModel album = new PhotoAlbumModel();
    album.addShape("Oval_1", "oval", 100, 300,
        850, 90, "100.0,30.0,50.0");
    album.addShape("Rectangle_1", "Rectangle", 250, 600,
        100, 500, "0.0,200.0,1.0");

    album.takeSnapshot("Snapshot 1");

    album.addShape("Oval_2", "oval", 500, 500,
        200, 200, "1.0,255,300");
    album.addShape("Rectangle_2", "Rectangle", 50, 50,
        50, 50, "50,200,150");

    album.takeSnapshot("Snapshot 2");

    GraphicalView view = new GraphicalView(album.getSnapshots());
    view.displaySnapshots();
  }
}
