package net.springbootdemo.mapper;

import net.springbootdemo.dto.OrderDto;
import net.springbootdemo.model.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {ClientMapper.class}
)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "client", ignore = true)
    Order fromDTO(OrderDto orderDto);

    OrderDto toDTO(Order source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    void applyUpdate(@MappingTarget Order target, OrderDto update);

}
