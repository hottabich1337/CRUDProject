package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.mapper.ClientMapper;
import com.example.CRUDProject.repository.ClientRepository;
import com.example.CRUDProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientMapper clientMapper;
    @Autowired
    private EmployeeRepository employeeRepository;


    public void addClient(ClientDTO clientDTO) {
        if (clientDTO == null || clientDTO.getName() == null || clientDTO.getSurname() == null ||
                clientDTO.getEmail() == null || clientDTO.getPhone() == null) {
            System.out.println("Invalid data");
        }
        Client client = clientMapper.clientDTOToClient(clientDTO); // Преобразуем DTO в Entity

        clientRepository.save(client);
    }

    public void updateClient(ClientDTO clientDTO) {

        Client existingClient = clientRepository.findByEmail(clientDTO.getEmail());
        ClientDTO exsistingClientDTO = clientMapper.clientToClientDTO(existingClient);
        if (exsistingClientDTO != null) {
            existingClient.setName(clientDTO.getName()!=null ? clientDTO.getName():exsistingClientDTO.getName());
            existingClient.setSurname(clientDTO.getSurname()!=null ? clientDTO.getSurname():exsistingClientDTO.getSurname());
            existingClient.setPhone(clientDTO.getPhone()!=null ? clientDTO.getPhone():exsistingClientDTO.getPhone());
        }
        clientRepository.save(existingClient);
    }

    public ClientDTO clientInfo(Integer id) {
        Client existingClient = clientRepository.findById(id).get();
        return clientMapper.clientToClientDTO(existingClient);
    }

    public void deleteClient(Integer id) {
        Client existingClient = clientRepository.findById(id).get();
        clientRepository.delete(existingClient);
    }

}
