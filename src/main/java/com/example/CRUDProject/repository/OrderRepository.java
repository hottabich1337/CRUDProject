package com.example.CRUDProject.repository;

import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<Order, Integer> , JpaSpecificationExecutor<Order> {

    Page<Order> findAll(
            Pageable pageable
    );
}
