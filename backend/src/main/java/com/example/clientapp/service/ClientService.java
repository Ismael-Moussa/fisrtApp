package com.example.clientapp.service;

import com.example.clientapp.model.Client;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  private final List<Client> clients = new ArrayList<>();
  private final AtomicLong idGenerator = new AtomicLong(1);

  public ClientService() {
    create(new Client(null, "Alice Martin", "alice@example.com", "+33 6 12 34 56 78", "Acme"));
    create(new Client(null, "Brahim Diallo", "brahim@example.com", "+33 6 98 76 54 32", "NovaTech"));
  }

  public List<Client> findAll() {
    return clients.stream()
      .sorted(Comparator.comparing(Client::getId))
      .toList();
  }

  public Optional<Client> findById(Long id) {
    return clients.stream().filter(client -> client.getId().equals(id)).findFirst();
  }

  public Client create(Client client) {
    Client newClient = new Client(
      idGenerator.getAndIncrement(),
      client.getName(),
      client.getEmail(),
      client.getPhone(),
      client.getCompany()
    );
    clients.add(newClient);
    return newClient;
  }

  public Optional<Client> update(Long id, Client updated) {
    return findById(id).map(existing -> {
      existing.setName(updated.getName());
      existing.setEmail(updated.getEmail());
      existing.setPhone(updated.getPhone());
      existing.setCompany(updated.getCompany());
      return existing;
    });
  }

  public boolean delete(Long id) {
    return clients.removeIf(client -> client.getId().equals(id));
  }
}
