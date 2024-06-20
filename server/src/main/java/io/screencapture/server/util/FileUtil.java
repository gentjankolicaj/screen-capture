package io.screencapture.server.util;

public class FileUtil {

  public static String getResourcePath(String resourceName) {
    String dirPath = HostUtil.getUserDir();
    return dirPath + HostUtil.getPathSeparator() + "src" + HostUtil.getPathSeparator() + "main"
        + HostUtil.getPathSeparator() + "resources" + HostUtil.getPathSeparator() + resourceName;
  }

}
