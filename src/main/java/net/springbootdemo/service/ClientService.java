package net.springbootdemo.service;

import net.springbootdemo.dto.ClientDto;
import net.springbootdemo.model.Client;
import net.springbootdemo.repository.ClientRepository;
import net.springbootdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static net.springbootdemo.exception.ObjectNotFoundException.objectNotFoundExSupplier;

@Service
public class ClientService {

  private final ClientRepository clientRepository;
  private final OrderRepository orderRepository;

  public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
    this.clientRepository = clientRepository;
    this.orderRepository = orderRepository;
  }

  public Client findById(Long id) {
    return clientRepository.findById(id).orElseThrow(objectNotFoundExSupplier(Client.class, id));
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

  @Transactional
  public void deleteByIdWithAllOrders(Long id) {
    Client client = clientRepository.findById(id).orElseThrow(objectNotFoundExSupplier(Client.class, id));
    orderRepository.deleteAllByClient(client);
    clientRepository.deleteById(id);
  }
}
