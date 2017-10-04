package org.wsecu.rdelhommer.user;

public class UserDto {
  public User user;
  public String message;

  public UserDto(User user) {
    this(user, null);
  }

  public UserDto(Exception exception) {
    this(null, exception.getMessage());
  }

  public UserDto(User user, String message) {
    this.user = user;
    this.message = message;
  }
}