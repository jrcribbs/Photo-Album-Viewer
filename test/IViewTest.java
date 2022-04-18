import static org.junit.Assert.assertEquals;

import model.PhotoAlbumModel;
import org.junit.Before;
import org.junit.Test;
import views.WebView;

public class IViewTest {
  WebView webView;

  @Before
  public void setUp() {
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

    // this.webView = new WebView(album.getSnapshots(), "testOutput.html", 1000, 1000);

  }

  @Test
  public void testWebView() {

  }



}
