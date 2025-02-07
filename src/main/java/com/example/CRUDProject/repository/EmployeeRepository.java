package com.example.CRUDProject.repository;

import com.example.CRUDProject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> getEmployeeByEmail(String email);

    void removeEmployeeByEmail(String email);


    boolean existsByEmail(String email);
}
