package de.htwberlin.entity;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
  @Id UUID id;

  String name;

  double price;
}
