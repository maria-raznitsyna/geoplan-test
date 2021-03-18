package net.proselyte.springbootdemo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "number")
  private Long number;

  @CreationTimestamp
  @Column(name = "creation_dt",  updatable = false)
  private Date creationDate;

  @Column(name = "description")
  private String description;

  @Column(name = "sum")
  private Float sum;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_owner", nullable = false)
  private Client client;
}
