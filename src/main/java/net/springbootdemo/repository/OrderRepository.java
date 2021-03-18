package net.springbootdemo.repository;

import net.springbootdemo.model.Client;
import net.springbootdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {

  List<Order> findAllByClient(Client client);

  void deleteAllByClient(Client client);
}
