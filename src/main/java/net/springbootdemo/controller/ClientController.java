package net.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import net.springbootdemo.dto.ClientDto;
import net.springbootdemo.model.Client;
import net.springbootdemo.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {
  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    List<Client> clients = clientService.findAll();
    return new ResponseEntity<>(clients, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> createClient(@RequestBody Client client) {
    Client createdClient = clientService.saveClient(client);
    return new ResponseEntity<>(createdClient, HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {
    Client client = clientService.findById(id);
    if (client == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(client, HttpStatus.OK);
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientDto updateClient) {
    Client client = clientService.findById(id);
    if (client == null) {
      return ResponseEntity.notFound().build();
    }
    Client updatedClient = clientService.updateClient(id, updateClient);
    return new ResponseEntity<>(updatedClient, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Client client = clientService.findById(id);
    if (client == null) {
      return ResponseEntity.notFound().build();
    }
    clientService.deleteByIdWithAllOrders(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
