package com.example.CRUDProject.controller;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.mapper.ClientMapper;
import com.example.CRUDProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("addClient")
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO clientDTO) {
        clientService.addClient(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }


    @PostMapping("/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
        clientService.updateClient(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/deleteClient")
    public void deleteClient(@RequestParam Integer id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/clientInfo")
    public ResponseEntity<ClientDTO> getClientInfo(@RequestParam Integer id) {
        ClientDTO clientDTO = clientService.clientInfo(id);
        return ResponseEntity.ok(clientDTO);
    }


    @GetMapping("/filter")
    public Page<ClientDTO> filterAndSortClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false, defaultValue = "name") String sortField,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @PageableDefault(page = 0, size = 30) Pageable pageable
    ) {
        return clientService.filterAndSortClients(name,surName,email, phone, sortField, sortDirection, pageable);
    }
}
