package net.proselyte.springbootdemo.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  String codeRegion;
  String district;
  String city;
  String street;
  Integer houseNumber;
  Integer corpsNumber;
  Integer officeNumber;

}
