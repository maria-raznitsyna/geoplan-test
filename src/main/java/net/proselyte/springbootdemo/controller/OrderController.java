package net.proselyte.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import net.proselyte.springbootdemo.dto.OrderDto;
import net.proselyte.springbootdemo.model.Order;
import net.proselyte.springbootdemo.service.OrderService;
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
  public ResponseEntity<?> findAll(){
    List<Order> orders = orderService.findAll();
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> createOrder(Order order){
    Order createdOrder = orderService.saveOrder(order);
    return new ResponseEntity<>(createdOrder, HttpStatus.OK);
  }
  @GetMapping("/get/{id}")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {
    Order order = orderService.findById(id);
    return new ResponseEntity<>(order, HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderDto orderDto) {
    Order updatedOrder = orderService.updateOrder(id, orderDto);
    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    orderService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
