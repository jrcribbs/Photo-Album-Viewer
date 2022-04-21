import static org.junit.Assert.assertEquals;

import model.PhotoAlbumModel;
import org.junit.Before;
import org.junit.Test;
import views.WebView;

/**
 * Tests variables used in WebView.
 */
public class IViewTest {
  WebView webView;
  PhotoAlbumModel model;

  /**
   * Sets up variables to be tested.
   */
  @Before
  public void setUp() {
    model = new PhotoAlbumModel();
    model.addShape("Oval_1", "oval", 100, 300,
        850, 90, "100,30,50");
    model.addShape("Rectangle_1", "Rectangle", 250, 600,
        100, 500, "0,200,1");

    model.takeSnapshot("Snapshot 1");

    model.addShape("Oval_2", "oval", 500, 500,
        200, 200, "1,255,300");
    model.addShape("Rectangle_2", "Rectangle", 50, 50,
        50, 50, "50,200,150");

    model.takeSnapshot("Snapshot 2");

    this.webView = new WebView(model.getSnapshots(), "testOutput.html", 1000, 1000);
  }

  /**
   * Test web view.
   */
  @Test
  public void testWebView() {
    this.webView.displaySnapshots();
    assertEquals("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Your Photo Album</h1>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #1</h2>\n" +
            "<div>\n" +
            "    <h2>Description: Snapshot 1</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <ellipse id=\"Oval_1\" cx=\"100.0\" cy=\"300.0\" rx=\"850.0\" ry=\"90.0\" fill=\"rgb(100,30,50)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #2</h2>\n" +
            "<div>\n" +
            "    <h2>Description: Snapshot 2</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <ellipse id=\"Oval_1\" cx=\"100.0\" cy=\"300.0\" rx=\"850.0\" ry=\"90.0\" fill=\"rgb(100,30,50)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "        <ellipse id=\"Oval_2\" cx=\"500.0\" cy=\"500.0\" rx=\"200.0\" ry=\"200.0\" fill=\"rgb(1,255,300)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_2\" x=\"50.0\" y=\"50.0\" width=\"50.0\" height=\"50.0\" fill=\"rgb(50,200,150)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>", this.webView.getSnapStrings().toString());
  }

  /**
   * Testing if Webview updates properly after model change (removed shape and took screenshot).
   */
  @Test
  public void testRemoveShapeWebview() {
    // removing shape and taking snapshot
    model.removeShape("Oval_1");
    model.takeSnapshot("Snapshot 3");

    // updating webview
    this.webView = new WebView(model.getSnapshots(), "testOutput2.html", 1000, 1000);
    this.webView.displaySnapshots();

    assertEquals("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Your Photo Album</h1>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #1</h2>\n" +
            "<div>\n" +
            "    <h2>Description: Snapshot 1</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <ellipse id=\"Oval_1\" cx=\"100.0\" cy=\"300.0\" rx=\"850.0\" ry=\"90.0\" fill=\"rgb(100,30,50)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #2</h2>\n" +
            "<div>\n" +
            "    <h2>Description: Snapshot 2</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <ellipse id=\"Oval_1\" cx=\"100.0\" cy=\"300.0\" rx=\"850.0\" ry=\"90.0\" fill=\"rgb(100,30,50)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "        <ellipse id=\"Oval_2\" cx=\"500.0\" cy=\"500.0\" rx=\"200.0\" ry=\"200.0\" fill=\"rgb(1,255,300)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_2\" x=\"50.0\" y=\"50.0\" width=\"50.0\" height=\"50.0\" fill=\"rgb(50,200,150)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #3</h2>\n" + // should be added and have no Oval_1
            "<div>\n" +
            "    <h2>Description: Snapshot 3</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "        <ellipse id=\"Oval_2\" cx=\"500.0\" cy=\"500.0\" rx=\"200.0\" ry=\"200.0\" fill=\"rgb(1,255,300)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_2\" x=\"50.0\" y=\"50.0\" width=\"50.0\" height=\"50.0\" fill=\"rgb(50,200,150)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>", this.webView.getSnapStrings().toString());
  }

  /**
   * Testing Webview if empty model (no shapes/snapshot created) is passed in.
   */
  @Test
  public void testNoShapesWebView() {
    // making new model
    PhotoAlbumModel model = new PhotoAlbumModel();

    // new webView and displaying snapshots that don't exist
    this.webView = new WebView(model.getSnapshots(), "testOutput3.html", 1000, 1000);
    this.webView.displaySnapshots();

    // should just contain title of page & header/footer
    assertEquals("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Your Photo Album</h1>\n" +
            "</body>\n" +
            "</html>", this.webView.getSnapStrings().toString());
  }

  /**
   * Testing Webview if an extra snapshot with no extra shapes/changes from the last is passed in.
   */
  @Test
  public void testBlankSnapshot() {
    this.model.takeSnapshot("Blank");

    // new webView and displaying snapshots that don't exist
    this.webView = new WebView(model.getSnapshots(), "testOutput4.html", 1000, 1000);
    this.webView.displaySnapshots();

    assertEquals("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Your Photo Album</h1>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #1</h2>\n" +
            "<div>\n" +
            "    <h2>Description: Snapshot 1</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <ellipse id=\"Oval_1\" cx=\"100.0\" cy=\"300.0\" rx=\"850.0\" ry=\"90.0\" fill=\"rgb(100,30,50)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #2</h2>\n" +
            "<div>\n" +
            "    <h2>Description: Snapshot 2</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <ellipse id=\"Oval_1\" cx=\"100.0\" cy=\"300.0\" rx=\"850.0\" ry=\"90.0\" fill=\"rgb(100,30,50)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "        <ellipse id=\"Oval_2\" cx=\"500.0\" cy=\"500.0\" rx=\"200.0\" ry=\"200.0\" fill=\"rgb(1,255,300)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_2\" x=\"50.0\" y=\"50.0\" width=\"50.0\" height=\"50.0\" fill=\"rgb(50,200,150)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "<div>\n" +
            "    <h2>Snapshot ID #3</h2>\n" + // snapshot ID 3 should match Snap ID 2
            "<div>\n" +
            "    <h2>Description: Blank</h2>\n" +
            "    <svg width=\"1000\" height=\"1000\">\n" +
            "        <ellipse id=\"Oval_1\" cx=\"100.0\" cy=\"300.0\" rx=\"850.0\" ry=\"90.0\" fill=\"rgb(100,30,50)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_1\" x=\"250.0\" y=\"600.0\" width=\"100.0\" height=\"500.0\" fill=\"rgb(0,200,1)\">\n" +
            "        </rect>\n" +
            "        <ellipse id=\"Oval_2\" cx=\"500.0\" cy=\"500.0\" rx=\"200.0\" ry=\"200.0\" fill=\"rgb(1,255,300)\">\n" +
            "        </ellipse>\n" +
            "        <rect id=\"Rectangle_2\" x=\"50.0\" y=\"50.0\" width=\"50.0\" height=\"50.0\" fill=\"rgb(50,200,150)\">\n" +
            "        </rect>\n" +
            "    </svg>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>", this.webView.getSnapStrings().toString());
  }
}
