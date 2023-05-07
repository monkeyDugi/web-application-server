/*
 * Created by Be lee on 2023/05/07
 * As part of Bigin
 *
 * Copyright (C) Bigin (https://bigin.io/main) - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by backend Team <bd_lee@bigin.io>, 2023/05/07
 */
package webserver;

import java.util.HashMap;
import java.util.Map;
import model.User;

/**
 * create on 2023/05/07. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Bd Lee
 * @version 1.0
 * @since 1.0
 */
public class UserRepository {

  public static final Map<String, User> database = new HashMap<>();

  public void save(User user) {
    database.put(user.getUserId(), user);
  }
}
