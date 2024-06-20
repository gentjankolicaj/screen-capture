package io.screencapture.server;

import io.screencapture.server.core.CustomServerSocket;
import io.screencapture.server.core.ServerSocketThread;
import io.screencapture.server.gui.ServerGui;
import io.screencapture.server.host.HostScreen;
import java.awt.AWTException;

public class ServerApplication {


  public static void main(String[] args) throws AWTException {
    ServerGui serverGui = new ServerGui();
    CustomServerSocket customServerSocket = new CustomServerSocket(new HostScreen());

    Thread serverSocketThread = new ServerSocketThread(args, customServerSocket, serverGui);
    serverSocketThread.start();

  }


}
