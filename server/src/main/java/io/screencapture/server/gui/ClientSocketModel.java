package io.screencapture.server.gui;

import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ClientSocketModel {

  private final Socket socket;
  private final String time;

  public ClientSocketModel(Socket socket) {
    this.socket = socket;
    this.time = LocalTime.now().format(DateTimeFormatter.ISO_TIME);
  }

  @Override
  public String toString() {
    return time + "-" + socket;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof Socket) {
      Socket that = (Socket) o;
      return socket == that;
    }
    if (!(o instanceof ClientSocketModel)) {
      return false;
    }
    ClientSocketModel that = (ClientSocketModel) o;
    return socket.equals(that.socket);
  }

  @Override
  public int hashCode() {
    return Objects.hash(socket.getPort());
  }

}
