package com.example.CRUDProject.repository;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByEmail(String email);
}
