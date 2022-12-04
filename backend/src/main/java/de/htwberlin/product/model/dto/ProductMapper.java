package de.htwberlin.product.model.dto;

import de.htwberlin.product.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

  ProductDto toDto(ProductEntity entity);

  ProductEntity toEntity(ProductDto dto);
}
