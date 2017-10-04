package org.wsecu.rdelhommer.user;

import java.util.*;
import org.springframework.stereotype.*;
import org.wsecu.rdelhommer.exceptions.*;

@Repository
public class UserRepository {
  private Hashtable<String, User> users = new Hashtable<String, User>();

	public User save(User user) throws UserAlreadyExistsException {
    User existingUser = users.putIfAbsent(user.getUsername(), user);

    if (existingUser != null) {
      throw new UserAlreadyExistsException(user.getUsername());
    }

    return user;
  }

  public User findByUsername(String username) {
    return users.get(username);
  }

  public User update(User updatedUser) {
    return users.merge(updatedUser.getUsername(), updatedUser, (oldValue, newValue) -> {
      try {
        return User.CreateUser(oldValue.getUsername(), newValue.getName(), newValue.getEmail());
      }
      catch (InvalidEmailException ex) {
        return oldValue;
      }
    });
  }

  public User delete(String username) {
    return users.remove(username);
  }
}