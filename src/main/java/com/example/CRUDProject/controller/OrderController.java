package com.example.CRUDProject.controller;

import com.example.CRUDProject.dto.OrderDTO;
import com.example.CRUDProject.mapper.OrderMapper;
import com.example.CRUDProject.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.addOrder(orderDTO);
        return ResponseEntity.ok().body(orderDTO);
    }

    @PostMapping("/updateOrder")
    public void updateOrder(@RequestParam Integer id,@RequestParam String orderStatus) {
        orderService.updateOrder(id, orderStatus);
    }

    @DeleteMapping("/deleteOrder")
    public void deleteOrder(@RequestParam Integer id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/orderInfo")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam Integer id) {
        return ResponseEntity.ok(orderService.getOrderInfo(id));
    }


    @GetMapping("/filter")
    public Page<OrderDTO> filterAndSortOrders(
            @RequestParam(required = false) LocalDateTime orderCreationDate,
            @RequestParam(required = false) String orderStatus,
            @RequestParam(required = false, defaultValue = "orderCreationDate") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @PageableDefault(page = 0, size = 30) Pageable pageable
    ) {
        return orderService.filterAndSortOrders(orderCreationDate,orderStatus,sortField,sortDirection,pageable);
    }
}
