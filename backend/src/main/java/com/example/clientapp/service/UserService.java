package com.example.clientapp.service;

import com.example.clientapp.model.Role;
import com.example.clientapp.model.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final List<User> users = new ArrayList<>();
  private final AtomicLong idGenerator = new AtomicLong(1);
  private final RoleService roleService;

  public UserService(RoleService roleService) {
    this.roleService = roleService;
    seedUsers();
  }

  private void seedUsers() {
    roleService.findAll().stream().findFirst().ifPresent(role ->
      create(new User(null, "Sarah Dupont", "sarah.dupont@example.com", role.getId(), role.getName()))
    );
    roleService.findAll().stream().skip(1).findFirst().ifPresent(role ->
      create(new User(null, "Omar Benali", "omar.benali@example.com", role.getId(), role.getName()))
    );
  }

  public List<User> findAll() {
    return users.stream()
      .sorted(Comparator.comparing(User::getId))
      .toList();
  }

  public Optional<User> findById(Long id) {
    return users.stream().filter(user -> user.getId().equals(id)).findFirst();
  }

  public Optional<User> create(User user) {
    Optional<Role> role = roleService.findById(user.getRoleId());
    if (role.isEmpty()) {
      return Optional.empty();
    }
    User newUser = new User(
      idGenerator.getAndIncrement(),
      user.getFullName(),
      user.getEmail(),
      role.get().getId(),
      role.get().getName()
    );
    users.add(newUser);
    return Optional.of(newUser);
  }

  public Optional<User> update(Long id, User updated) {
    Optional<Role> role = roleService.findById(updated.getRoleId());
    if (role.isEmpty()) {
      return Optional.empty();
    }
    return findById(id).map(existing -> {
      existing.setFullName(updated.getFullName());
      existing.setEmail(updated.getEmail());
      existing.setRoleId(role.get().getId());
      existing.setRoleName(role.get().getName());
      return existing;
    });
  }

  public boolean delete(Long id) {
    return users.removeIf(user -> user.getId().equals(id));
  }
}
