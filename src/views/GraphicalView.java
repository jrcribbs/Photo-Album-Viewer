package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;

import model.ISnapshot;

/**
 * The type Graphical view.
 */
public class GraphicalView extends JFrame implements IView {
    private final JFrame frame;
    private final JPanel buttonPanel = new JPanel();
    private final JPanel infoPanel = new JPanel();
    private final JLabel jlabel = new JLabel();
    private DrawPanel picturePanel;
    private final List<ISnapshot> snaps;
    private final ArrayList<String> snapIDs = new ArrayList<>();
    private final String[] options;
    private int counter = 0; // counter for index position in Snapshot list

    /**
     * Instantiates a new Graphical view.
     *
     * @param snaps the list of snapshots
     * @param xMax  the maximum frame size on x-axis
     * @param yMax  the maximum frame size on y-axis
     */
    public GraphicalView(List<ISnapshot> snaps, int xMax, int yMax) {
        super();
        frame = this;
        this.snaps = snaps;
        // screen size
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

        // making buttons
        makeButtons();

        // making info panel
        makeInfoPanel();
    }

    /**
     * Helper class to make/place buttons.
     */
    private void makeButtons() {
        // setting button styles
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttonPanel.setLayout(new GridLayout(1, 0));

        // creating buttons
        JButton prevButton = new JButton("Previous");
        JButton selectButton = new JButton("Select");
        JButton nextButton = new JButton("Next");
        JButton closeButton = new JButton("Close");

        // adding action listeners
        prevButton.addActionListener(new PreviousListener());
        selectButton.addActionListener(new SelectListener());
        nextButton.addActionListener(new NextListener());
        closeButton.addActionListener(new CloseListener());

        // adding buttons and button panel to frame
        buttonPanel.add(prevButton);
        buttonPanel.add(selectButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Initial setup for info panel. Grabs metadata from first snapshot and displays at top of frame.
     */
    private void makeInfoPanel() {
        // setting button styles
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        infoPanel.setLayout(new GridLayout(1, 0));
        infoPanel.add(jlabel);
        jlabel.setText(this.snaps.get(counter).getID() + "\n" + this.snaps.get(counter).getDescription());
        jlabel.setFont(new Font("Verdana", 1, 12));

        add(infoPanel, BorderLayout.NORTH);
    }

    /**
     * Updates jLabel for graphical view as user moves through snapshots.
     */
    private void updateLabel() {
        jlabel.setText("");
        jlabel.setText(this.snaps.get(counter).getID() + "\n" + this.snaps.get(counter).getDescription());
        jlabel.setFont(new Font("Verdana", 1, 12));
    }

    @Override
    public void displaySnapshots() {
        picturePanel = new DrawPanel(this.snaps.get(counter)); // resetting panel
        add(picturePanel); // adding panel to frame
        this.setVisible(true);
    }

    /**
     * Subclass action listener for close button.
     */
    private class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Subclass action listener for select button.
     */
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
                    updateLabel();
                }
            }
        }
    }

    /**
     * Subclass action listener for next button.
     */
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
                updateLabel();
            }
            displaySnapshots();
            updateLabel();
        }
    }

    /**
     * Subclass action listener for previous button.
     */
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
                updateLabel();
            }
            displaySnapshots();
            updateLabel();
        }
    }
}
