package net.proselyte.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "number")
  private String number;
  @Column(name = "creation_dt")
  private Date creationDate;
  @Column(name = "description")
  private String description;
  @Column(name = "sum")
  private Float sum;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_owner", nullable = false)
  private Client client;
}
