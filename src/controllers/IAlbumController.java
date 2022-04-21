package controllers;

import java.io.IOException;
import model.IAlbumModel;

/**
 * The interface Album controller.
 */
public interface IAlbumController {

  /**
   * Runs the program using inputted model.
   *
   * @param model the model
   * @throws IOException the io exception
   */
  void go(IAlbumModel model) throws IOException;

}
