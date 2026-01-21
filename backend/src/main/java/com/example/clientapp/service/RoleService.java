package com.example.clientapp.service;

import com.example.clientapp.model.Role;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  private final List<Role> roles = new ArrayList<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  public RoleService() {
    create(new Role(null, "Admin", "Accès complet à la plateforme"));
    create(new Role(null, "Manager", "Gestion d'équipe et suivi des clients"));
    create(new Role(null, "Agent", "Accès aux clients assignés"));
  }

  public List<Role> findAll() {
    return roles.stream()
      .sorted(Comparator.comparing(Role::getId))
      .toList();
  }

  public Optional<Role> findById(Long id) {
    return roles.stream().filter(role -> role.getId().equals(id)).findFirst();
  }

  public Role create(Role role) {
    Role newRole = new Role(
      idGenerator.getAndIncrement(),
      role.getName(),
      role.getDescription()
    );
    roles.add(newRole);
    return newRole;
  }

  public Optional<Role> update(Long id, Role updated) {
    return findById(id).map(existing -> {
      existing.setName(updated.getName());
      existing.setDescription(updated.getDescription());
      return existing;
    });
  }

  public boolean delete(Long id) {
    return roles.removeIf(role -> role.getId().equals(id));
  }
}
