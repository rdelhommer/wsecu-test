package org.wsecu.rdelhommer.user;

import org.apache.commons.validator.routines.EmailValidator;
import org.wsecu.rdelhommer.exceptions.InvalidEmailException;

public class User {
  private String username;
  private String name;
  private String email;

  private User(String username, String name, String email) {
    this.username = username;
    this.name = name;
    this.email = email;
  }

  public String getUsername() {
    return this.username;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public static User CreateUser(String username, String name, String email) throws InvalidEmailException {
    String trimmedEmail = email.trim();
    if (!EmailValidator.getInstance().isValid(trimmedEmail)) {
      throw new InvalidEmailException(trimmedEmail);
    }

    return new User(username.toLowerCase().trim(), name.trim(), trimmedEmail);
  }
}