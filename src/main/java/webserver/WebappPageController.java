package webserver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebappPageController extends FrontHandler {

  private final OutputStream response;

  public WebappPageController(OutputStream response) {
    this.response = response;
  }

  @Override
  public void execute(String path) throws IOException {
    byte[] body = Files.readAllBytes(getWebappFileApth(path));

    DataOutputStream dataOutputStream = new DataOutputStream(response);
    response200Header(dataOutputStream, body.length);
    responseBody(dataOutputStream, body);
  }

  private Path getWebappFileApth(String path) {
    if ("/".equals(path)) {
      return new File("./webapp/index.html").toPath();
    }

    return new File("./webapp" + path).toPath();
  }
}
