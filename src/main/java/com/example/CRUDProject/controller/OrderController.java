package com.example.CRUDProject.controller;

import com.example.CRUDProject.dto.OrderDTO;
import com.example.CRUDProject.mapper.OrderMapper;
import com.example.CRUDProject.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
