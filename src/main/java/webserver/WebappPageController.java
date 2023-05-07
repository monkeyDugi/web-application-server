package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebappPageController extends FrontHandler {

  private final BufferedReader request;
  private final OutputStream response;

  public WebappPageController(BufferedReader request, OutputStream response) {
    this.request = request;
    this.response = response;
  }

  @Override
  public void execute(String path) throws IOException {
    byte[] body = Files.readAllBytes(getWebappFileApth(path));

    DataOutputStream dataOutputStream = new DataOutputStream(response);
    String contentType = "text/html";

    while (true) {
      String line = request.readLine();
      String[] split = line.split(": ");

      if ("Accept".equals(split[0])) {
        String[] acceptValues = split[1].split(",");
        for (String acceptValue : acceptValues) {
          if ("text/css".equals(acceptValue)) {
            contentType = "text/css";
            break;
          }
        }
      }

      if ("".equals(line)) {
        break;
      }
    }

    response200Header(dataOutputStream, contentType, body.length);
    responseBody(dataOutputStream, body);
  }

  private Path getWebappFileApth(String path) {
    if ("/".equals(path)) {
      return new File("./webapp/index.html").toPath();
    }

    return new File("./webapp" + path).toPath();
  }
}
