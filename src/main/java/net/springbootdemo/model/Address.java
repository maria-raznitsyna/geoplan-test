package net.springbootdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
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

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Client client;

}
