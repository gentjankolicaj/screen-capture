
import io.screencapture.client.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileUtilTest {

  @Test
  public void testGetResourcePath() {
    String path = FileUtil.getResourcePath("ops.gif");
    Assertions.assertNotEquals(path, null);

    String path2 = FileUtil.getResourcePath("500.png");
    Assertions.assertNotEquals(path2, null);
  }
}
