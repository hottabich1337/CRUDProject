package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Employee;
import com.example.CRUDProject.mapper.ClientMapper;
import com.example.CRUDProject.repository.ClientRepository;
import com.example.CRUDProject.repository.EmployeeRepository;
import com.example.CRUDProject.specification.ClientSpecification;
import com.example.CRUDProject.specification.EmployeeSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        ClientDTO clientDTO = clientMapper.clientToClientDTO(existingClient);
        clientDTO.setOrders(existingClient.getOrders());
        return clientDTO;
    }

    public void deleteClient(Integer id) {
        Client existingClient = clientRepository.findById(id).get();
        clientRepository.delete(existingClient);
    }

    public Page<ClientDTO> filterAndSortClients( String name, String surName ,String email ,String phone,
        String sortField, String sortDirection,
        Pageable pageable){

        // Создание спецификации для фильтрации по имени
        Specification<Client> specification = ClientSpecification.nameContains(sortField);

        // Настройка сортировки
        Sort.Direction direction = Optional.ofNullable(sortDirection)
                .map(dir -> dir.toUpperCase())
                .filter(dir -> dir.equals("ASC") || dir.equals("DESC"))
                .map(Sort.Direction::fromString)
                .orElse(Sort.Direction.ASC); // Значение по умолчанию

        Sort sort = Sort.by(direction, "name");

        // Создание Pageable с учётом сортировки
        Pageable finalPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );

        // Выполнение запроса
        Page<Client> clientPage = clientRepository.findAll(specification, finalPageable);

        // Преобразование в DTO
        return clientPage.map(clientMapper::clientToClientDTO);

    }

}
