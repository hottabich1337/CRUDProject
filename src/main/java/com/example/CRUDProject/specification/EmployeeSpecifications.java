package com.example.CRUDProject.specification;
import com.example.CRUDProject.entity.Employee;
import org.springframework.data.jpa.domain.Specification;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;

public class EmployeeSpecifications {

    // Фильтрация по имени
    public static Specification<Employee> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(name)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }
    // Фильтрация по фамилии (новый метод)
    public static Specification<Employee> surnameContains(String surname) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(surname)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")), "%" + surname.toLowerCase() + "%");
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }

    // Фильтрация по email (новый метод)
    public static Specification<Employee> emailContains(String email) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(email)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }

    // Фильтрация по роли (исправленный метод)
    public static Specification<Employee> roleEquals(String role) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(role)) {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get("role")), role.toLowerCase());
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }

    // Комбинирование всех фильтров (обновлённый метод)
    public static Specification<Employee> filterBy(
            String name, String surname, String email, String role
    ) {
        return Specification
                .where(nameContains(name)) // Фильтр по имени
                .and(surnameContains(surname)) // Фильтр по фамилии
                .and(emailContains(email)) // Фильтр по email
                .and(roleEquals(role)); // Фильтр по роли
    }
}