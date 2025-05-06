package com.example.CRUDProject.mapper;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.entity.Employee;
import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO clientToClientDTO(Client client);
    Employee clientDTOToClient(ClientDTO clientDTO);
}
