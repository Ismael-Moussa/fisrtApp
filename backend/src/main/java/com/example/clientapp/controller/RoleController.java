package com.example.clientapp.controller;

import com.example.clientapp.model.Role;
import com.example.clientapp.service.RoleService;
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
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {
  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public List<Role> getRoles() {
    return roleService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Role> getRole(@PathVariable Long id) {
    return roleService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Role> createRole(@RequestBody Role role) {
    Role created = roleService.create(role);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
    return roleService.update(id, role)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
    if (roleService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
