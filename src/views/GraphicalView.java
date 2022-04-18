package views;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ISnapshot;
import model.PhotoAlbumModel;

public class GraphicalView extends JFrame implements IView {
  protected JFrame frame;
  protected JPanel buttonPanel = new JPanel();
  protected DrawPanel picturePanel;
  protected List<ISnapshot> snaps;
  protected ArrayList<String> snapIDs = new ArrayList<>();
  protected String[] options;
  protected int xMax, yMax; // screen size
  protected int counter = 0; // counter for position in Snapshot list

  public GraphicalView(List<ISnapshot> snaps, int xMax, int yMax) {
    super();
    frame = this;
    this.snaps = snaps;
    this.xMax = xMax;
    this.yMax = yMax;
    this.picturePanel = new DrawPanel(this.snaps.get(counter)); // ONLY SINGLE SNAPSHOT PASSED IN

    // getting snapIDs
    for (ISnapshot snap : this.snaps) {
      snapIDs.add(snap.getID());
    }
    options = snapIDs.toArray(new String[snapIDs.size()]);


    // default settings for frame
    setSize(xMax, yMax);
    setTitle("Graphical View Photo Album");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // setting button styles
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    buttonPanel.setLayout(new GridLayout(1, 0));

    // creating buttons, adding functionality, and adding to frame
    JButton prevButton = new JButton("Previous");
    JButton selectButton = new JButton("Select");
    JButton nextButton = new JButton("Next");
    JButton closeButton = new JButton("Close");

    prevButton.addActionListener(new PreviousListener());
    selectButton.addActionListener(new SelectListener());
    nextButton.addActionListener(new NextListener());
    closeButton.addActionListener(new MyCloseListener());

    buttonPanel.add(prevButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(closeButton);

    add(buttonPanel, BorderLayout.SOUTH);

  }

  @Override
  public void displaySnapshots() {
    // takes in snap object (index determined by buttons above, start at 0)
    // pass info to DrawPanel
    // update this.picturePanel

    picturePanel = new DrawPanel(this.snaps.get(counter));
    add(picturePanel);
    this.setVisible(true);
  }

  private class SelectListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String s = (String) JOptionPane.showInputDialog(frame,
          "Please select from the following Snapshots:",
          "Snapshot Selection", JOptionPane.INFORMATION_MESSAGE,
          null, options, snapIDs.get(0));

      for (int i = 0; i < snaps.size(); i++) {
        if (Objects.equals(s, snapIDs.get(i))) {
          counter = i;
          displaySnapshots();
        }
      }
    }
  }

  private class NextListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      counter++;
      if (counter == snaps.size()) {
        JOptionPane.showMessageDialog(frame,
            "You have reached the end of the photo album.",
            "End of Album",
            JOptionPane.WARNING_MESSAGE);
         counter = snaps.size() - 1;
         displaySnapshots();
      }
      displaySnapshots();
    }
  }

  private class PreviousListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      counter--;
      if (counter < 0) {
        JOptionPane.showMessageDialog(frame,
            "You have reached the beginning of the photo album.",
            "Beginning of Album",
            JOptionPane.WARNING_MESSAGE);
        counter = 0;
        displaySnapshots();
      }
      displaySnapshots();
    }
  }


  /**
   * SMOKE TEST DELETE THIS
   * @param args N/A
   */
  public static void main(String[] args) {
    PhotoAlbumModel album = new PhotoAlbumModel();
    album.addShape("Oval_1", "oval", 100, 200,
        850, 90, "100.0,30.0,50.0");
    album.addShape("Rectangle_1", "Rectangle", 250, 600,
        100, 500, "0.0,200.0,1.0");

    album.takeSnapshot("Test Snapshot 1");

    album.removeShape("Oval_1");
    album.removeShape("Rectangle_1");

    album.addShape("Oval_2", "oval", 500, 500,
        200, 200, "1.0,255,300");
    album.addShape("Rectangle_2", "Rectangle", 50, 50,
        50, 50, "50,200,150");

    album.takeSnapshot("Test Snapshot 2");

    album.removeShape("Oval_2");
    album.removeShape("Rectangle_2");

    album.addShape("Oval_3", "oval", 500, 700,
        55, 60, "100.0,30.0,50.0");
    album.addShape("Rectangle_3", "Rectangle", 800, 600,
        300, 300, "0.0,200.0,1.0");

    album.takeSnapshot("Test Snapshot 3");

    album.removeShape("Oval_3");
    album.removeShape("Rectangle_3");

    album.addShape("Oval_4", "oval", 200, 200,
        900, 900, "1.0,255,300");
    album.addShape("Rectangle_4", "Rectangle", 50, 800,
        50, 50, "50,200,150");

    album.takeSnapshot("Test Snapshot 4");


    GraphicalView view = new GraphicalView(album.getSnapshots(), 1000, 1000);
    view.displaySnapshots();
  }
}
