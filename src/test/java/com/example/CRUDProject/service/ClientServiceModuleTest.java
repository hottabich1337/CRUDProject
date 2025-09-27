package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.mapper.ClientMapper;
import com.example.CRUDProject.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClientServiceModuleTest {


    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    private ClientDTO clientDTO;
    private Client client;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO();
        clientDTO.setName("Иван");
        clientDTO.setSurname("Иванов");
        clientDTO.setEmail("ivan@example.com");
        clientDTO.setPhone("89201111000");

        client = new Client();
        client.setId(1);
        client.setName("Иван");
        client.setSurname("Иванов");
        client.setEmail("ivan@example.com");
        client.setPhone("89201111000");
    }

    @Test
    void addClient_ValidData_ShouldCallMapperAndRepository() {
        // Arrange
        when(clientMapper.clientDTOToClient(clientDTO)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);

        // Act
        clientService.addClient(clientDTO);

        // Assert
        verify(clientMapper).clientDTOToClient(clientDTO);
        verify(clientRepository).save(client);
    }



   @Test
    void updateClient_ExistingEmail_ShouldUpdateFields() {
        // Arrange
        when(clientRepository.findByEmail("ivan@example.com")).thenReturn(Optional.of(client));;
       //     when(clientRepository.save(client)).thenReturn(client);

        // Modify DTO
        ClientDTO updatedDTO = new ClientDTO();
        updatedDTO.setEmail("ivan@example.com");
        updatedDTO.setSurname("Петров");
        updatedDTO.setPhone("89201111001");

        // Act
        clientService.updateClient(updatedDTO);
       // Assert: используем ArgumentCaptor, чтобы проверить сохранённый объект
       ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
       verify(clientRepository).save(captor.capture());
        // Assert
        assertEquals("Петров", client.getSurname());
        assertEquals("89201111001", client.getPhone());
        verify(clientRepository).save(client);
    }


    @Test
    void addClient() {
    }

    @Test
    void updateClient() {
    }

    @Test
    void clientInfo() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void filterAndSortClients() {
    }
}