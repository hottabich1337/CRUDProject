package com.example.CRUDProject.controller;

import com.example.CRUDProject.dto.ClientDTO;
import com.example.CRUDProject.dto.EmployeeDTO;
import com.example.CRUDProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("")
    public ResponseEntity<ClientDTO> addEmployee(@RequestBody ClientDTO clientDTO) {
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
}
