package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, Long> {
}
