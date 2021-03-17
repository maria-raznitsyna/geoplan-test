package net.proselyte.springbootdemo.dto;

import lombok.*;
import net.proselyte.springbootdemo.model.Address;
import net.proselyte.springbootdemo.model.Order;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class ClientDto {
  private String name;
  private String inn;
  private String phoneNumber;
  private Address address;
  private Set<Order> orders = new HashSet<>();
}
