package com.example.CRUDProject.specification;

import com.example.CRUDProject.entity.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderSpecification {

    public static Specification<Order> orderStatusContains(String orderStatus) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(orderStatus)) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("orderStatus")),
                        "%" + orderStatus.trim().toLowerCase() + "%"
                );
            }
            return null;
        };
    }

    public static Specification<Order> orderCreationDateEquals(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if (date != null) {
                return criteriaBuilder.equal(root.get("orderCreationDate"), date);
            }
            return null;
        };
    }

    // Комбинирование всех фильтров (обновлённый метод)
    public static Specification<Order> filterBy(
            LocalDateTime orderCreationDate,  String orderStatus
    ) {
        return Specification
                .where(orderStatusContains(orderStatus)) // Фильтр по orderStatus
                .and(orderCreationDateEquals(orderCreationDate)); // Фильтр по orderCreationDate
    }
}
