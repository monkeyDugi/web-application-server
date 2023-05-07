package webserver;

import db.DataBase;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import model.User;
import util.HttpRequestUtils;
import util.IOUtils;

public class UserLoginPostHandler extends FrontHandler {

  private final BufferedReader request;
  private final OutputStream response;

  public UserLoginPostHandler(BufferedReader request, OutputStream response) {
    this.request = request;
    this.response = response;
  }

  @Override
  public void execute(String unused) throws IOException {
    int contentLength = 0;
    while (true) {
      String line = request.readLine();
      String[] split = line.split(": ");

      if ("Content-Length".equals(split[0])) {
        contentLength = Integer.parseInt(split[1]);
      }
      if ("".equals(line)) {
        break;
      }
    }

    String body = IOUtils.readData(request, contentLength);
    Map<String, String> bodyValues = HttpRequestUtils.parseQueryString(body);

    String userId = bodyValues.get("userId");
    String password = bodyValues.get("password");

    User findUser = DataBase.findUserById(userId);
    findUser.validatePassword(password);

    DataOutputStream dos = new DataOutputStream(response);
    response302Header(dos);
  }
}
