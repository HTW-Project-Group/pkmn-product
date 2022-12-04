package de.htwberlin.product.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", nullable = false, updatable = false)
  UUID id;

  @Column(name = "name", unique = true, nullable = false, updatable = false)
  String name;

  @Column(name = "price")
  double price;

  @Column(name = "description")
  String description;

  @Column(name = "condition")
  int condition;

  @Column(name = "pokemon_id", unique = true, nullable = false, updatable = false)
  String pokemonId;
}
