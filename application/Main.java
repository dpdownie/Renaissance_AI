/**
 * Main:
 * Establishes the GUI of the program using JavaFX 11.
 * 
 * @author Drew Downie (2020)
 *
 */

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
  private static final String APP_TITLE = "Renaissance AI";
  private static final int WINDOW_WIDTH = 600;
  private static final int WINDOW_HEIGHT = 600;

  @Override
  public void start(Stage mainStage) throws Exception {
    BorderPane root = new BorderPane();

    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    mainScene.getStylesheets().add("application/win_style.css");

    root.setStyle("-fx-background-color: #E1D2C0");

    // https://cdn.britannica.com/37/75437-004-EFD403D1/detail-William-Shakespeare-portrait-oil-painting-John-1610.jpg
    mainStage.getIcons().add(new Image("images/shakespeare.jpg"));

    /* Set the title and scene of the main stage */
    mainStage.setTitle(APP_TITLE);
    mainStage.setScene(mainScene);
    mainStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}


