package controllers;

import java.io.IOException;
import model.IAlbumModel;

public interface IAlbumController {
  void go(IAlbumModel model) throws IOException;

}
