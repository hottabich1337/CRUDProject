package com.example.CRUDProject.mapper;


import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Order;
import com.example.CRUDProject.dto.OrderDTO;
import com.example.CRUDProject.enums.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order OrderDTOToOrder(OrderDTO orderDTO);
    OrderDTO OrderToOrderDTO(Order order);

}
