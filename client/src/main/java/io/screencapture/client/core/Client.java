package io.screencapture.client.core;

import java.io.IOException;

public abstract class Client {

  public abstract void start(String[] args) throws IOException;

  public abstract void shutdown(String[] args) throws IOException;
}
