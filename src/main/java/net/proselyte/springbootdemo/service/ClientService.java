package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.dto.ClientDto;
import net.proselyte.springbootdemo.model.Client;
import net.proselyte.springbootdemo.repository.ClientRepository;
import net.proselyte.springbootdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

  private final ClientRepository clientRepository;
  private final OrderRepository orderRepository;

  public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
    this.clientRepository = clientRepository;
    this.orderRepository = orderRepository;
  }

  public Client findById(Long id) {
    return clientRepository.findById(id).get();
  }

  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  public Client saveClient(Client client) {
    return clientRepository.save(client);
  }

  public Client updateClient(Long id, ClientDto updateDto) {
    Optional<Client> dbVersion = clientRepository.findById(id);
    if (dbVersion.isPresent()) {
      Client clientFromDb = dbVersion.get();
      if (Objects.nonNull(updateDto.getAddress())) {
        clientFromDb.setAddress(updateDto.getAddress());
      }
      if (Objects.nonNull(updateDto.getInn())) {
        clientFromDb.setInn(updateDto.getInn());
      }
      if (Objects.nonNull(updateDto.getName())) {
        clientFromDb.setName(updateDto.getName());
      }
      if (Objects.nonNull(updateDto.getPhoneNumber())) {
        clientFromDb.setPhoneNumber(updateDto.getPhoneNumber());
      }
      if (Objects.nonNull(updateDto.getAddress())) {
        clientFromDb.setAddress(updateDto.getAddress());
      }
      return clientRepository.save(clientFromDb);
    }
    return null;
  }

  public void deleteById(Long id) {
    clientRepository.deleteById(id);
  }
}
