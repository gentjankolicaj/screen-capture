package io.screencapture.server.core;

import io.screencapture.server.config.ServerConfig;
import io.screencapture.server.gui.ServerGui;
import java.io.IOException;


public class ServerSocketThread extends Thread {

  private final CustomServerSocket customServerSocket;
  private final String[] args;
  private final ServerGui serverGui;

  public ServerSocketThread(String[] args, CustomServerSocket customServerSocket,
      ServerGui serverGui) {
    super();
    this.args = args;
    this.customServerSocket = customServerSocket;
    this.serverGui = serverGui;
    this.setName("ServerSocket-Thread");
  }

  @Override
  public void run() {
    try {
      //Starts com.screen_capture.server socket
      customServerSocket.start(args);

      //Performs custom processing
      process();

      //Closes com.screen_capture.server socket
      customServerSocket.shutdown(args);

      //Shutdown jvm
      System.exit(0);

    } catch (IOException io) {
      io.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  private void process() throws IOException {
    int opCode;
    long startTime = System.currentTimeMillis();
    loop:
    while ((opCode = customServerSocket.dataInputStream.readInt()) != 0) {
      switch (opCode) {
        case 1:
          byte[] bytes = customServerSocket.getScreenCapture();

          //Writes bytes to com.screen_capture.server socket output stream
          customServerSocket.dataOutputStream.writeInt(bytes.length);
          customServerSocket.dataOutputStream.write(bytes);
          break;
        default:
          break loop;
      }

      if (canUpdate(startTime)) {
        serverGui.updateSocketClientGui(customServerSocket.getSocketClients());

        //Update startTime
        startTime = System.currentTimeMillis();
      }
    }
  }

  private boolean canUpdate(long startTime) {
    long currentTime = System.currentTimeMillis();
    long updateTime = (currentTime - startTime);
    return updateTime >= ServerConfig.GUI_UPDATE_INTERVAL;
  }


}
