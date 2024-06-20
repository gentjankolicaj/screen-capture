package io.screencapture.server.config;

import io.screencapture.server.util.HostUtil;

public class ServerConfig {

  public static final String GUI_TITLE = "SC-com.screen_capture.server";

  public static final String FILE_DIR_NAME = "sc-com.screen_capture.server";
  public static final String FILE_PATH =
      HostUtil.getUserDir() + HostUtil.getPathSeparator() + FILE_DIR_NAME;


  public static final String HOSTNAME = HostUtil.getHostname();
  public static final String IMAGE_FORMAT_NAME = "JPG";
  public static final long GUI_UPDATE_INTERVAL = 5000; //3 seconds in millis
  public static int PORT = 8000;
  public static int SCENE_WIDTH = 600;
  public static int SCENE_HEIGHT = 150;

}
