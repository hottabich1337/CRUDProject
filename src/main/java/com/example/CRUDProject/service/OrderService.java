package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.OrderDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Order;
import com.example.CRUDProject.enums.OrderStatus;
import com.example.CRUDProject.mapper.OrderMapper;
import com.example.CRUDProject.repository.ClientRepository;
import com.example.CRUDProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ClientRepository clientRepository;

    public void addOrder(OrderDTO orderDTO) {
        Client client = clientRepository.findById(orderDTO.getClientId()).get();
        Order order = orderMapper.OrderDTOToOrder(orderDTO);
        order.setClient(client);
        orderRepository.save(order);
    }

    public void updateOrder(Integer orderId,String orderStatus){
        Order order = orderRepository.findById(orderId).get();
        OrderStatus statusEnum = OrderStatus.fromString(orderStatus);
        order.setOrderStatus(statusEnum);
        orderRepository.save(order);
    }

    public OrderDTO getOrderInfo(Integer orderId){
        Order order = orderRepository.findById(orderId).get();
        return orderMapper.OrderToOrderDTO(order);
    }

    public void deleteOrder(Integer orderId){
        Order order = orderRepository.findById(orderId).get();
        orderRepository.delete(order);
    }
}
