package com.example.CRUDProject.repository;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {

    Optional<Client> findByEmail(String email);
    Page<Client> findAll(
            Pageable pageable
    );
}
