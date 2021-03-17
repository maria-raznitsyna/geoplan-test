package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.dto.ClientDto;
import net.proselyte.springbootdemo.dto.OrderDto;
import net.proselyte.springbootdemo.model.Client;
import net.proselyte.springbootdemo.model.Order;
import net.proselyte.springbootdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  private final OrderRepository orderRepository;

  public OrderService(OrderRepository clientRepository) {
    this.orderRepository = clientRepository;
  }

  public Order findById(Long id){
    return orderRepository.getOne(id);
  }

  public List<Order> findAll(){
    return orderRepository.findAll();
  }

  public Order saveOrder(Order order){
    return orderRepository.save(order);
  }

  public void deleteById(Long id){
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
