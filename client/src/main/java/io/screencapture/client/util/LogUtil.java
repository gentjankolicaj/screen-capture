package io.screencapture.client.util;

public class LogUtil {

  public static void info(String message, Object... vars) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(message);
    if (vars != null && vars.length != 0) {
      stringBuilder.append("%s ".repeat(vars.length));
    }
    String formated = String.format(stringBuilder.toString(), vars);
    System.out.println(formated);
  }
}
