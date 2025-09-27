package com.example.CRUDProject.service;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.entity.Client;
import com.example.CRUDProject.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class ClientServiceIntegrationTest {


    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO();
        clientDTO.setName("Алексей");
        clientDTO.setSurname("Сидоров");
        clientDTO.setEmail("sidorov@example.com");
        clientDTO.setPhone("89201111002");
    }


    @Test
    void addClient_ShouldSaveToDatabase() {
        // Act
        clientService.addClient(clientDTO);

        // Assert
        Optional<Client> saved = clientRepository.findByEmail("sidorov@example.com");
        assertThat(saved).isPresent();
        assertThat(saved.get().getName()).isEqualTo("Алексей");
        assertThat(saved.get().getPhone()).isEqualTo("89201111002");
    }

    @Test
    void updateClient_ExistingEmail_ShouldUpdateData() {
        // Arrange: сначала добавляем клиента
        clientService.addClient(clientDTO);

        // Modify DTO
        clientDTO.setSurname("Новиков");
        clientDTO.setPhone("89201111003");

        // Act
        clientService.updateClient(clientDTO);

        // Assert
        Optional<Client> updated = clientRepository.findByEmail("sidorov@example.com");
        assertThat(updated).isPresent();
        assertThat(updated.get().getSurname()).isEqualTo("Новиков");
        assertThat(updated.get().getPhone()).isEqualTo("89201111003");
    }

    @Test
    void clientInfo_ExistingId_ShouldReturnCorrectData() {
        // Arrange
        clientService.addClient(clientDTO);

        Client savedClient = clientRepository.findByEmail("sidorov@example.com").orElse(null);
        Integer id = savedClient.getId();

        // Act
        ClientDTO result = clientService.clientInfo(id);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("sidorov@example.com");
        assertThat(result.getSurname()).isEqualTo("Сидоров");
    }


}