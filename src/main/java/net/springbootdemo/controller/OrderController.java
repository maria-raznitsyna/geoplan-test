package net.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import net.springbootdemo.dto.OrderDto;
import net.springbootdemo.model.Order;
import net.springbootdemo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    List<Order> orders = orderService.findAll();
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> createOrder(@RequestBody Order order, @RequestParam Long orderOwner) {
    Order createdOrder = orderService.saveOrder(order, orderOwner);
    return new ResponseEntity<>(createdOrder, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getOrderById(@PathVariable Long id) {
    Order order = orderService.findById(id);
    if (order == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(order, HttpStatus.OK);
  }

  @GetMapping("/byclient")
  public ResponseEntity<?> getOrderByClientId(@RequestParam Long clientId) {
    List<Order> orders = orderService.findAllByClients(clientId);
    if (orders == null) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderDto orderDto) {
    Order order = orderService.findById(id);
    if (order == null) {
      return ResponseEntity.notFound().build();
    }
    Order updatedOrder = orderService.updateOrder(id, orderDto);
    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Order order = orderService.findById(id);
    orderService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
