package io.screencapture.server.core;

import io.screencapture.server.config.ServerConfig;
import io.screencapture.server.host.HostScreen;
import io.screencapture.server.util.LogUtil;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CustomServerSocket extends CustomServer {

  protected final HostScreen hostScreen;

  protected ServerSocket serverSocket;
  protected DataInputStream dataInputStream;
  protected DataOutputStream dataOutputStream;
  protected List<Socket> socketClients;

  public CustomServerSocket(HostScreen hostScreen) {
    super();
    this.hostScreen = hostScreen;
    this.socketClients = new ArrayList<>();
  }


  public void start(String[] args) throws IOException {
    int argSize = args.length;
    int portNumber = ServerConfig.PORT;
    if (argSize == 0) {
      System.out.println("No port provided.Default port will used " + portNumber);
    } else {
      portNumber = Integer.parseInt(args[0]);
    }

    LogUtil.info("Starting com.screen_capture.server... ");

    serverSocket = new ServerSocket(portNumber);
    LogUtil.info("Server listening on port ", portNumber);

    Socket clientSocket = serverSocket.accept();
    socketClients.add(clientSocket);
    LogUtil.info("Client connected.");

    OutputStream outputStream = clientSocket.getOutputStream();
    InputStream inputStream = clientSocket.getInputStream();

    dataOutputStream = new DataOutputStream(outputStream);
    dataInputStream = new DataInputStream(inputStream);

  }

  @Override
  public void shutdown(String[] args) throws IOException {
    if (!serverSocket.isClosed()) {
      serverSocket.close();
    }
    LogUtil.info("Closed com.screen_capture.server");
  }


  public byte[] getScreenCapture() {
    return hostScreen.getScreenCapture();
  }


  public List<Socket> getSocketClients() {
    return socketClients;
  }

}
