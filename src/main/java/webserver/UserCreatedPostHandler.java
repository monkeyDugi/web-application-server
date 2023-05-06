/*
 * Created by Be lee on 2023/05/06
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by backend Team <bd_lee@bigin.io>, 2023/05/06
 */
package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import model.User;
import util.HttpRequestUtils;
import util.IOUtils;

/**
 * create on 2023/05/06. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Bd Lee
 * @version 1.0
 * @since 1.0
 */
public class UserCreatedPostHandler extends FrontHandler {

  private final BufferedReader request;
  private final OutputStream response;

  public UserCreatedPostHandler(BufferedReader request, OutputStream response) {
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

    new User(bodyValues);

    DataOutputStream dos = new DataOutputStream(response);
    response200Header(dos, 0);
  }
}
