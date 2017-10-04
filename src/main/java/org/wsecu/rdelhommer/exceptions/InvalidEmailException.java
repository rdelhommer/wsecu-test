package org.wsecu.rdelhommer.exceptions;

public class InvalidEmailException extends Exception {
  public InvalidEmailException(String invalidEmail) {
    super(invalidEmail + " is not a valid email address. Please ensure that your email is correct.");
  }
}