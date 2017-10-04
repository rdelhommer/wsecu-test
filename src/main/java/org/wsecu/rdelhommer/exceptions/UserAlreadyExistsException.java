package org.wsecu.rdelhommer.exceptions;

public class UserAlreadyExistsException extends Exception {
  public UserAlreadyExistsException(String invalidUsername) {
    super("The username " + invalidUsername + " has already been registered. Please try again with a different username");
  }
}