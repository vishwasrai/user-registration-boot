package com.fse.user.service;

import com.fse.user.entity.Users;
import com.fse.user.model.Login;
import com.fse.user.model.User;

public interface UserService {

  void register(User user);

  User validateUser(String username);

  Users accountUpdate(User user);
}
