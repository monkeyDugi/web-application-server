package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;

public abstract class FrontHandler {

  private static final Logger log = LoggerFactory.getLogger(FrontHandler.class);

  public static void call(BufferedReader request, OutputStream response) throws IOException {
    String reuqestUrl = request.readLine();
    String path = HttpRequestUtils.parsePath(reuqestUrl);

    if ("POST".equals(HttpRequestUtils.parseMethod(reuqestUrl)) && "/user/create".equals(path)) {
      new UserCreatedPostHandler(request, response).execute("");
    }

    new WebappPageController(response).execute(path);
  }

  protected abstract void execute(String path) throws IOException;

  protected void response200Header(DataOutputStream response, int lengthOfBodyContent) {
    try {
      response.writeBytes("HTTP/1.1 200 OK \r\n");
      response.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
      response.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
      response.writeBytes("\r\n");
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  protected void responseBody(DataOutputStream response, byte[] body) {
    try {
      response.write(body, 0, body.length);
      response.flush();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
