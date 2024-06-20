package io.screencapture.server.util;

public class LogUtil {

  public static void info(String message, Object... vars) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(message);
    if (vars != null && vars.length != 0) {
      stringBuilder.append("%s ".repeat(vars.length));
    }
    String formatted = String.format(stringBuilder.toString(), vars);
    System.out.println(formatted);
  }
}
