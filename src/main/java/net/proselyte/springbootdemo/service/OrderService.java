package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.dto.OrderDto;
import net.proselyte.springbootdemo.model.Client;
import net.proselyte.springbootdemo.model.Order;
import net.proselyte.springbootdemo.repository.ClientRepository;
import net.proselyte.springbootdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static net.proselyte.springbootdemo.exception.ObjectNotFoundException.objectNotFoundExSupplier;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final ClientRepository clientRepository;

  public OrderService(OrderRepository orderRepository, ClientRepository clientRepository) {
    this.orderRepository = orderRepository;
    this.clientRepository = clientRepository;
  }

  public Order findById(Long id) {
    return orderRepository.findById(id).orElseThrow(objectNotFoundExSupplier(Order.class, id));
  }

  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  public List<Order> findAllByClients(Long clientId) {
    Client client = clientRepository.findById(clientId).orElseThrow(objectNotFoundExSupplier(Client.class, clientId));

    return orderRepository.findAllByClient(client);
  }

  public Order saveOrder(Order order, Long ownerId) {
    Client owner = clientRepository.findById(ownerId).orElseThrow(objectNotFoundExSupplier(Client.class, ownerId));
    order.setClient(owner);
    return orderRepository.save(order);
  }


  public Order updateOrder(Long id, OrderDto updateDto) {
    Order dbVersion = orderRepository.findById(id).orElseThrow(objectNotFoundExSupplier(Order.class, id));

      return orderRepository.save(dbVersion);
  }

  public void deleteById(Long id) {
    orderRepository.deleteById(id);
  }
}
