package webserver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class WebappPageController extends FrontHandler {

  private final OutputStream response;

  public WebappPageController(OutputStream response) {
    this.response = response;
  }

  @Override
  public void execute(String path) throws IOException {
    File file = new File("./webapp" + path);
    byte[] body = Files.readAllBytes(file.toPath());

    DataOutputStream dataOutputStream = new DataOutputStream(response);
    response200Header(dataOutputStream, body.length);
    responseBody(dataOutputStream, body);
  }
}
