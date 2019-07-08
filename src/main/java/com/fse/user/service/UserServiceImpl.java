package com.fse.user.service;

import com.fse.user.dao.UserRepository;
import com.fse.user.entity.Users;
import com.fse.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  public UserRepository userRepository;

  @Override
  public void register(User user) {

    userRepository.save(toUserDetailsMapper(user));
  }

  @Override
  public Users accountUpdate(User user) {

    return userRepository.save(toUserDetailsMapper(user));
  }

  @Override
  public User validateUser(String username) {

    Users users = userRepository.findByUsername(username).orElse(null);
    if(users !=null){
      return User.builder().name(users.getName())
              .username(users.getUsername())
              .password(users.getPassword())
              .email(users.getEmail())
              .build();
    }
    return null;
  }

  private Users toUserDetailsMapper(User user) {

    Users users = new Users();
    users.setName(user.getName());
    users.setUsername(user.getUsername());
    users.setEmail(user.getEmail());
    users.setPassword(user.getPassword());
    return users;
  }
}