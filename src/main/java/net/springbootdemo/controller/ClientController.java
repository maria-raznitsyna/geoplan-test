package net.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import net.springbootdemo.dto.ClientDto;
import net.springbootdemo.mapper.ClientMapper;
import net.springbootdemo.model.Client;
import net.springbootdemo.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {
  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public ResponseEntity<List<ClientDto>> findAll() {
    List<ClientDto> clients = clientService.findAll()
            .stream().map(ClientMapper.INSTANCE::toDTO)
            .collect(Collectors.toList());
    return new ResponseEntity<>(clients, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {

    return new ResponseEntity<>(ClientMapper.INSTANCE.toDTO(
            clientService.saveClient(
                    ClientMapper.INSTANCE.fromDTO(clientDto))),
            HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<ClientDto> getUserById(@PathVariable Long id) {
    return new ResponseEntity<>(ClientMapper.INSTANCE.toDTO(
            clientService.findById(id)),
            HttpStatus.OK);
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto updateClient) {
    Client updatedClient = clientService.updateClient(id, updateClient);
    return new ResponseEntity<>(ClientMapper.INSTANCE.toDTO(updatedClient), HttpStatus.OK);
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
