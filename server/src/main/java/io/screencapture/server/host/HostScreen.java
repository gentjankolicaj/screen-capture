package io.screencapture.server.host;

import io.screencapture.server.config.ServerConfig;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HostScreen {

  private final Rectangle screenRectangle;
  private final Robot robot;

  public HostScreen() throws AWTException {
    super();
    this.screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    this.robot = new Robot();
  }


  public byte[] getScreenCapture() {
    BufferedImage capture = this.robot.createScreenCapture(screenRectangle);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] imageArray = new byte[0];
    try {
      ImageIO.write(capture, ServerConfig.IMAGE_FORMAT_NAME, baos);

      //flushes buffer & saves reference of byte array
      baos.flush();
      imageArray = baos.toByteArray();

      //closes output stream
      baos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return imageArray;
  }
}
