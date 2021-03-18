package net.springbootdemo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class OrderDto {
  private Long id;
  private Long number;
  private String description;
  private Float sum;
}
