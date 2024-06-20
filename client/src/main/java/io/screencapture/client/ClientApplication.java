package io.screencapture.client;


import io.screencapture.client.config.ClientConfig;
import io.screencapture.client.core.SocketClient;
import io.screencapture.client.util.FileUtil;
import io.screencapture.client.util.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Gentjan Kolicaj
 */
public class ClientApplication extends Application {

  static SocketClient socketClient = new SocketClient(ClientConfig.HOSTNAME, ClientConfig.PORT);
  static StackPane stackPane = new StackPane();
  static ImageView imageView = new ImageView();
  static Image image;

  public static void main(String[] args) throws IOException {
    socketClient.start(args);
    launch(args);
  }


  @Override
  public void start(Stage stage) throws Exception {
    //Get socket image as a byte array initially
    byte[] bytes = socketClient.getByteArray(1);
    ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
    image = new Image(byteInputStream);

    //Set image at image view
    imageView.setImage(image);
    imageView.setFitHeight(ClientConfig.IMAGE_VIEW_HEIGHT);
    imageView.setPreserveRatio(true);

    //Add image view
    stackPane.getChildren().add(imageView);

    stage.setTitle(ClientConfig.GUI_TITLE);
    stage.setScene(new Scene(stackPane, ClientConfig.SCENE_WIDTH, ClientConfig.SCENE_HEIGHT));
    stage.show();

    //Set on close request
    stage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    });

    //Create thread for updating images and start it.
    Thread imageThread = new Thread(new ImageUpdater());
    imageThread.setName("imageThread");
    imageThread.start();

  }


  static class ImageUpdater implements Runnable {

    @Override
    public void run() {
      boolean ioExceptionThrown = false;
      while (socketClient.isConnected() && !ioExceptionThrown) {
        try {
          byte[] imageArray = socketClient.getByteArray(1);
          image = new Image(new ByteArrayInputStream(imageArray));

          //reset view
          imageView.setImage(image);

          Thread.sleep(ClientConfig.IMAGE_VIEW_UPDATE_DELAY);
        } catch (InterruptedException ie) {
          ie.printStackTrace();
        } catch (IOException io) {
          io.printStackTrace();
          ioExceptionThrown = true;
        }
      }

      if (ioExceptionThrown) {
        try {
          String filePath = FileUtil.getResourcePath("500.jpg");
          image = new Image(new FileInputStream(filePath));
          imageView.setImage(image);
          LogUtil.info("Socket-client is not connected and about to close in "
              + ClientConfig.OPS_IMAGE_VIEW_DELAY + " seconds.");
          Thread.sleep(ClientConfig.OPS_IMAGE_VIEW_DELAY);

        } catch (InterruptedException | FileNotFoundException ie) {
          ie.printStackTrace();
        }
        //Platform exit & shutdown jvm
        Platform.exit();
        System.exit(0);
      }
    }
  }


}
