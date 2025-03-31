package com.example.CRUDProject.repository;

import com.example.CRUDProject.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor {

    List<Employee> getEmployeeByEmail(String email);

    void removeEmployeeByEmail(String email);


    boolean existsByEmail(String email);

    Employee findByEmail(String email);


    // Метод для динамической фильтрации и сортировки
    @Query("SELECT e FROM Employee e " +
            "WHERE (:name IS NULL OR e.name LIKE %:name%) " +
            "AND (:surname IS NULL OR e.surname LIKE %:surname%) " +
            "AND (:email IS NULL OR e.email LIKE %:email%) " +
            "AND (:role IS NULL OR e.role LIKE %:role%) " +
            "ORDER BY CASE WHEN :sort = 'name' THEN e.name END ASC, " +
            "CASE WHEN :sort = 'role' THEN e.role END ASC")
    List<Employee> filterAndSortEmployees(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("email") String email,
            @Param("role") String role,
            @Param("filter") String filter, // Используется для фильтрации
            @Param("sort") String sort     // Используется для сортировки
    );


    // Метод для фильтрации
    Page<Employee> findAllByNameContainingAndSurnameContainingAndEmailContainingAndRoleEquals(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("email") String email,
            @Param("role") String role,
            Pageable pageable
    );
}
