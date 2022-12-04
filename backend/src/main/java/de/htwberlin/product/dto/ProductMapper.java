package de.htwberlin.product.dto;

import de.htwberlin.product.model.ProductEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

  ProductDto toDto(ProductEntity entity);

  List<ProductDto> toDtos(List<ProductEntity> entities);

  ProductEntity toEntity(ProductDto dto);

  List<ProductEntity> toEntities(List<ProductDto> dtos);
}
