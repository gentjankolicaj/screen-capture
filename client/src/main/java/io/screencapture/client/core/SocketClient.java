package io.screencapture.client.core;

import io.screencapture.client.util.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient extends Client {

  private final String hostname;
  private final int port;
  private Socket socket;
  private DataInputStream dataInputStream;
  private DataOutputStream dataOutputStream;

  public SocketClient(String hostname, int port) {
    this.hostname = hostname;
    this.port = port;
  }

  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }

  public DataInputStream getDataInputStream() {
    return dataInputStream;
  }

  public DataOutputStream getDataOutputStream() {
    return dataOutputStream;
  }

  public void start(String[] args) throws IOException {
    LogUtil.info("Initialising socket-client on : ", hostname, port);
    this.socket = new Socket(hostname, port);
    InputStream socketInputStream = this.socket.getInputStream();
    OutputStream socketOutputStream = this.socket.getOutputStream();

    this.dataInputStream = new DataInputStream(socketInputStream);
    this.dataOutputStream = new DataOutputStream(socketOutputStream);
    LogUtil.info("Connected socket-client on : ", hostname, port);
  }

  @Override
  public void shutdown(String[] args) throws IOException {
    if (!this.socket.isClosed()) {
      this.socket.close();
    }
  }

  public byte[] getByteArray(int opCode) throws IOException {
    dataOutputStream.writeInt(opCode);

    int bytesToRead = dataInputStream.readInt();
    byte[] buffer = new byte[1024];
    int bytesRead;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    while (bytesToRead > 0) {
      bytesRead = dataInputStream.read(buffer);
      byteArrayOutputStream.write(buffer);
      bytesToRead -= bytesRead;
    }

    return byteArrayOutputStream.toByteArray();
  }

  public boolean isConnected() {
    return socket.isConnected();
  }

}
