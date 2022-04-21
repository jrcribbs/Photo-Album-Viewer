package views;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import model.IShape;
import model.ISnapshot;
import model.PhotoAlbumModel;

/**
 * WebView for Photo Album - generates HTML doc based on snapshot data passed in.
 */
public class WebView implements IView {

    List<ISnapshot> snaps;
    String outfile;
    int xMax, yMax;
    StringBuilder snapStrings;

    /**
     * Constructor for WebView.
     *
     * @param snaps   list of ISnapshots
     * @param outfile name of file to be output
     * @param xMax    max screen size x
     * @param yMax    max screen size y
     */
    public WebView(List<ISnapshot> snaps, String outfile, int xMax, int yMax) {
        this.snaps = snaps;
        this.outfile = outfile;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    /**
     * displaySnapshots for WebView takes in Snapshot data and outputs it to an HTML file.
     */
    @Override
    public void displaySnapshots() {
        // setting up string to append to
        StringBuilder snapStrings = new StringBuilder();
        snapStrings.append("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "<h1>Your Photo Album</h1>\n");

        for (ISnapshot snap : this.snaps) {
            // getting shapes out of snapshot
            List<IShape> shapes = snap.getShapes();

            // appending header from snapshot
            snapStrings.append("<div>\n    <h2>")
                    .append(snap.getID())
                    .append("</h2>\n")
                    .append("<div>\n    <h2>Description: ")
                    .append(snap.getDescription())
                    .append("</h2>\n")
                    .append("    <svg width=\"" + this.xMax + "\" height=\"" + this.yMax + "\">\n");

            for (IShape shape : shapes) {
                // appending shape information based on type
                if (Objects.equals(shape.getType(), "rectangle")) {
                    snapStrings.append("        <rect")
                            .append(" id=\"" + shape.getName() + "\"")
                            .append(" x=\"" + shape.getxCoordinate() + "\"")
                            .append(" y=\"" + shape.getyCoordinate() + "\"")
                            .append(" width=\"" + shape.getWidth() + "\"")
                            .append(" height=\"" + shape.getHeight() + "\"")
                            .append(" fill=\"rgb(" + shape.getColor() + ")\">\n")
                            .append("        </rect>\n");
                } else {
                    snapStrings.append("        <ellipse")
                            .append(" id=\"" + shape.getName() + "\"")
                            .append(" cx=\"" + shape.getxCoordinate() + "\"")
                            .append(" cy=\"" + shape.getyCoordinate() + "\"")
                            .append(" rx=\"" + shape.getWidth() + "\"")
                            .append(" ry=\"" + shape.getHeight() + "\"")
                            .append(" fill=\"rgb(" + shape.getColor() + ")\">\n")
                            .append("        </ellipse>\n");
                }
            }
            // ending snapshot section
            snapStrings.append("    </svg>\n</div>\n");
        }
        // ending html document
        snapStrings.append("</body>\n</html>");

        // writing to file
        writeFile(snapStrings.toString());

        // for testing/verification purposes
        this.snapStrings = snapStrings;
    }

    /**
     * Writes string to external html file.
     *
     * @param string String data to be put in file
     */
    private void writeFile(String string) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.outfile));
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets snapStrings.
     *
     * @return snapStrings
     */
    public StringBuilder getSnapStrings() {
        return this.snapStrings;
    }
}
