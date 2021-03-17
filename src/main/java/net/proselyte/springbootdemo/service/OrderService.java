package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.dto.OrderDto;
import net.proselyte.springbootdemo.model.Client;
import net.proselyte.springbootdemo.model.Order;
import net.proselyte.springbootdemo.repository.ClientRepository;
import net.proselyte.springbootdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final ClientRepository clientRepository;

  public OrderService(OrderRepository clientRepository, ClientRepository clientRepository1) {
    this.orderRepository = clientRepository;
    this.clientRepository = clientRepository1;
  }

  public Order findById(Long id) {
    return orderRepository.getOne(id);
  }

  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  public Order saveOrder(Order order, Long ownerId) {
    Client owner = clientRepository.findById(ownerId).get();
    order.setClient(owner);
    return orderRepository.save(order);
  }

  public void deleteById(Long id) {
    orderRepository.deleteById(id);
  }

  public Order updateOrder(Long id, OrderDto updateDto) {
    Optional<Order> dbVersion = orderRepository.findById(id);
    if (dbVersion.isPresent()) {
      Order orderFromDb = dbVersion.get();

      return orderRepository.save(orderFromDb);
    }
    return null;
  }
}
