package net.springbootdemo.service;

import net.springbootdemo.dto.OrderDto;
import net.springbootdemo.model.Client;
import net.springbootdemo.model.Order;
import net.springbootdemo.repository.ClientRepository;
import net.springbootdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static net.springbootdemo.exception.ObjectNotFoundException.objectNotFoundExSupplier;

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
    if(Objects.nonNull(updateDto.getDescription())) {
      dbVersion.setDescription(updateDto.getDescription());
    }
    if (Objects.nonNull(updateDto.getNumber())) {
      dbVersion.setNumber(updateDto.getNumber());
    }
    if (Objects.nonNull(updateDto.getSum())) {
      dbVersion.setSum(updateDto.getSum());
    }
      return orderRepository.save(dbVersion);
  }

  public void deleteById(Long id) {
    orderRepository.findById(id).orElseThrow(objectNotFoundExSupplier(Order.class, id));
    orderRepository.deleteById(id);
  }
}
