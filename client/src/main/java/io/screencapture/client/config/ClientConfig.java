package io.screencapture.client.config;

import io.screencapture.client.util.HostUtil;

public class ClientConfig {

  public static final String GUI_TITLE = "SC-client";

  //File config
  public static final String FILE_DIR_NAME = "sc-client";
  public static final String FILE_PATH =
      HostUtil.getUserDir() + HostUtil.getPathSeparator() + FILE_DIR_NAME;


  //Host config
  public static final String HOSTNAME = HostUtil.getHostname();
  public static int PORT = 8000;


  //Gui config
  public static int SCENE_WIDTH = 800;
  public static int SCENE_HEIGHT = 600;
  public static int IMAGE_VIEW_HEIGHT = SCENE_HEIGHT - 50;


  //Update thread config
  public static int IMAGE_VIEW_UPDATE_DELAY = 50;
  public static int OPS_IMAGE_VIEW_DELAY = 7000;

}
