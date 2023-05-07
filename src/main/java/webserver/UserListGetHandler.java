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

public class UserListGetHandler extends FrontHandler {

  private final BufferedReader request;
  private final OutputStream response;

  public UserListGetHandler(BufferedReader request, OutputStream response) {
    this.request = request;
    this.response = response;
  }

  @Override
  public void execute(String unused) throws IOException {
    String cookies = "";
    while (true) {
      String line = request.readLine();
      String[] split = line.split(": ");

      if ("Cookie".equals(split[0])) {
        cookies = split[1];
        break;
      }
    }

    Map<String, String> parseCookies = HttpRequestUtils.parseCookies(cookies);
    boolean isLogined = Boolean.parseBoolean(parseCookies.get("isLogined"));

    String redirectPath = getRedirectPathByLogin(isLogined);

    DataOutputStream dos = new DataOutputStream(response);
    response302Header(dos, redirectPath, redirectPath);
  }

  private static String getRedirectPathByLogin(boolean isLogined) {
    return isLogined ? "/index.html" : "/user/login.html";
  }
}
