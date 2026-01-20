package com.example.clientapp.controller;

import com.example.clientapp.model.User;
import com.example.clientapp.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getUsers() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    return userService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return userService.create(user)
      .map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
      .orElse(ResponseEntity.badRequest().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    return userService.update(id, user)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.badRequest().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (userService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
