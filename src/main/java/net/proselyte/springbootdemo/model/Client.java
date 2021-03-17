package net.proselyte.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "clients")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "inn")
  private String inn;

  @Column(name = "phone")
  private String phoneNumber;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_address", referencedColumnName = "ID")
  private Address address;

  @OneToMany(
    fetch = FetchType.EAGER,
    mappedBy = "client",
    orphanRemoval = true
  )
  private Set<Order> orders = new HashSet<>();
}

