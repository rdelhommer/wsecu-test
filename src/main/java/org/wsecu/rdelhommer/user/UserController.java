package org.wsecu.rdelhommer.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wsecu.rdelhommer.exceptions.*;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/user")
  public ResponseEntity<UserDto> create(@RequestParam("username") String username,
                                        @RequestParam("name") String name,
                                        @RequestParam("email") String email) {
    // Validate and create the user
    User user = null;
    try {
      user = User.CreateUser(username, name, email);
    } catch (InvalidEmailException ex) {
      // Return 400 if email is invalid
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new UserDto(null, ex.getMessage()));
    }

    // Save the user to the repository
    User savedUser = null;
    try {
      savedUser = userRepository.save(user);
    }
    catch (UserAlreadyExistsException ex) {
      // Return 409 if username already exists
      return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new UserDto(null, ex.getMessage()));
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(savedUser));
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<UserDto> read(@PathVariable(value="username") String username) {
    User foundUser = userRepository.findByUsername(username);

    if (foundUser == null) {
      // Return 404 if not found
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new UserDto(null, "Could not find " + username));
    }

    return ResponseEntity.ok(new UserDto(foundUser));
  }

  @PutMapping("/user/{username}")
  public ResponseEntity<UserDto> update(@PathVariable(value="username") String username,
                                        String name,
                                        String email) {
    User preUpdateUser = null;
    try {
      preUpdateUser = User.CreateUser(username, name, email);
    } catch (InvalidEmailException ex) {
      // Return 400 if email is invalid
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new UserDto(null, ex.getMessage()));
    }

    User afterUpdateUser = userRepository.update(preUpdateUser);
    return ResponseEntity.ok(new UserDto(afterUpdateUser));
  }

  @DeleteMapping("/user/{username}")
  public ResponseEntity<UserDto> delete(@PathVariable(value="username") String username) {
    User deletedUser = userRepository.delete(username);
    if (deletedUser == null) {
      // Return 404 if not found
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new UserDto(null, "Could not find " + username));
    }

    return ResponseEntity.ok(new UserDto(deletedUser));
  }
}