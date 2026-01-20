package com.example.clientapp.controller;

import com.example.clientapp.model.Client;
import com.example.clientapp.service.ClientService;
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
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public List<Client> getClients() {
    return clientService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Client> getClient(@PathVariable Long id) {
    return clientService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Client> createClient(@RequestBody Client client) {
    Client created = clientService.create(client);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
    return clientService.update(id, client)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
    if (clientService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
