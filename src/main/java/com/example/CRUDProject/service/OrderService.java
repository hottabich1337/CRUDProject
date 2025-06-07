package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.OrderDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Order;
import com.example.CRUDProject.enums.OrderStatus;
import com.example.CRUDProject.mapper.OrderMapper;
import com.example.CRUDProject.repository.ClientRepository;
import com.example.CRUDProject.repository.OrderRepository;
import com.example.CRUDProject.specification.ClientSpecification;
import com.example.CRUDProject.specification.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

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

    // Метод для фильтрации, сортировки и пагинации
    public Page<OrderDTO> filterAndSortOrders(
            LocalDateTime orderCreationDate, String orderStatus ,
            String sortField, String sortDirection,
            Pageable pageable
    ) {
        // 1. Создание спецификации
        Specification<Order> specification = OrderSpecification.filterBy(orderCreationDate, orderStatus);

        // 2. Настройка сортировки
        Sort.Direction direction = Optional.ofNullable(sortDirection)
                .map(String::toUpperCase)
                .filter(dir -> dir.equals("ASC") || dir.equals("DESC"))
                .map(Sort.Direction::fromString)
                .orElse(Sort.Direction.ASC);

        Sort sort = Sort.by(direction, sortField);
        Pageable finalPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // 3. Выполнение запроса
        Page<Order> orderPage = orderRepository.findAll(specification, finalPageable); // ✅ Через внедрённый репозиторий

        // 4. Преобразование в DTO
        return orderPage.map(orderMapper::OrderToOrderDTO);
    }

}
