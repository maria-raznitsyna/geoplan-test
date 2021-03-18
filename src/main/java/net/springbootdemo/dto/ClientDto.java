package net.springbootdemo.dto;

import lombok.*;
import net.springbootdemo.model.Address;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class ClientDto {
  private Long id;
  private String name;
  private String inn;
  private String phoneNumber;
  private Address address;
}
