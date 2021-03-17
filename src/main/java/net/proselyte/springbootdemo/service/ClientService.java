package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.dto.ClientDto;
import net.proselyte.springbootdemo.model.Client;
import net.proselyte.springbootdemo.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client findById(Long id){
    return clientRepository.findById(id).get();
  }

  public List<Client> findAll(){ return clientRepository.findAll();
  }

  public Client saveClient(Client client){
    return clientRepository.save(client);
  }

  public Client updateClient (Long id, ClientDto updateDto) {
    Optional<Client> dbVersion = clientRepository.findById(id);
    if (dbVersion.isPresent()) {
      Client clientFromDb = dbVersion.get();
      clientFromDb.setAddress(updateDto.getAddress());
      clientFromDb.setInn(updateDto.getInn());
      clientFromDb.setName(updateDto.getName());
      clientFromDb.setPhoneNumber(updateDto.getPhoneNumber());
      clientFromDb.setAddress(updateDto.getAddress());
      return clientRepository.save(clientFromDb);
    }
    return null;
  }

  public void deleteById(Long id){
    clientRepository.deleteById(id);
  }
}
