package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.model.Client;
import net.proselyte.springbootdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {

  List<Order> findAllByClient(Client client);
}
