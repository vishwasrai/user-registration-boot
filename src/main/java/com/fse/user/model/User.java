package com.fse.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

  private Long id;
  @Size(min = 1, message = "Name is required")
  private String name;
  @Size(min = 1, message = "Username is required")
  private String username;
  @Size(min = 1, message = "Password is required")
  private String password;
  @Email
  @Size(min = 1, message = "Email is required")
  private String email;

}
