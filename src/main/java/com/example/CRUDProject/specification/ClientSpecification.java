package com.example.CRUDProject.specification;

import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ClientSpecification {
    // Фильтрация по имени
    public static Specification<Client> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(name)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }

    // Фильтрация по фамилии
    public static Specification<Client> surnameContains(String surname) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(surname)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("surname")), "%" + surname.toLowerCase() + "%");
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }


    // Фильтрация по email
    public static Specification<Client> emailContains(String email) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(email)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%");
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }

    // Фильтрация по телефону
    public static Specification<Client> phoneContains(String phone) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(phone)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + phone.toLowerCase() + "%");
            }
            return null; // Если параметр пустой, возвращаем null
        };
    }


    // Комбинирование всех фильтров (обновлённый метод)
    public static Specification<Client> filterBy(
            String name, String surname, String email, String phone
    ) {
        return Specification
                .where(nameContains(name)) // Фильтр по имени
                .and(surnameContains(surname)) // Фильтр по фамилии
                .and(emailContains(email)) // Фильтр по email
                .and(phoneContains(phone)); // Фильтр по телефону
    }
}
