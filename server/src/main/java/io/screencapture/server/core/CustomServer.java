package io.screencapture.server.core;

import java.io.IOException;

public abstract class CustomServer {

  public abstract void start(String[] args) throws IOException;

  public abstract void shutdown(String[] args) throws IOException;

}
