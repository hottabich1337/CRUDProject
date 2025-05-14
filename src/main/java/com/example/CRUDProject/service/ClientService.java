package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.mapper.ClientMapper;
import com.example.CRUDProject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;


    public void addClient(ClientDTO clientDTO) {
        if (clientDTO == null || clientDTO.getName() == null || clientDTO.getSurname() == null ||
                clientDTO.getEmail() == null || clientDTO.getPhone() == null) {
            System.out.println("Invalid data");
        }
        Client client = clientMapper.clientDTOToClient(clientDTO); // Преобразуем DTO в Entity

        clientRepository.save(client);
    }
}
