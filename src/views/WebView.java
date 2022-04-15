package views;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import model.IShape;
import model.ISnapshot;
import model.PhotoAlbumModel;

public class WebView implements IView {

  List<ISnapshot> snaps;
  String outfile;

  /**
   * Constructor for WebView.
   *
   * @param snaps   list of ISnapshots
   * @param outfile name of file to be output
   */
  public WebView(List<ISnapshot> snaps, String outfile) {
    this.snaps = snaps;
    this.outfile = outfile;
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
          .append("    <svg width=\"1000\" height=\"1000\">\n");

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
   * SMOKE TEST DELETE THIS
   *
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

    WebView view = new WebView(album.getSnapshots(), "testOutput.html");
    view.displaySnapshots();
  }
}
