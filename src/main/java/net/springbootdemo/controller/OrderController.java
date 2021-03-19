package net.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import net.springbootdemo.dto.OrderDto;
import net.springbootdemo.mapper.OrderMapper;
import net.springbootdemo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll() {
        List<OrderDto> orders = orderService.findAll()
                .stream().map(OrderMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto, @RequestParam Long orderOwner) {
        return new ResponseEntity<>(OrderMapper.INSTANCE.toDTO(
                orderService.saveOrder(
                        OrderMapper.INSTANCE.fromDTO(orderDto), orderOwner)),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(OrderMapper.INSTANCE.toDTO(
                orderService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/byclient")
    public ResponseEntity<List<OrderDto>> getOrderByClientId(@RequestParam Long clientId) {
        List<OrderDto> orders = orderService.findAllByClients(clientId)
                .stream().map(OrderMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(OrderMapper.INSTANCE.toDTO(
                orderService.updateOrder(id, orderDto)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
