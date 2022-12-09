package de.htwberlin.core.appservice.dto;

import de.htwberlin.core.domain.model.Product;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IProductMapper {

  ProductDto toDto(Product entity);

  List<ProductDto> toDtos(List<Product> entities);

  Product toEntity(ProductDto dto);

  List<Product> toEntities(List<ProductDto> dtos);
}
