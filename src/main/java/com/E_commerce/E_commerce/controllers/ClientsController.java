package com.E_commerce.E_commerce.controllers;


import com.E_commerce.E_commerce.entities.Clients;
import com.E_commerce.E_commerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientsController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Clients>> getAllClients(){
        List<Clients> clients = clientService.searchAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable Long id) {
        Clients client = clientService.SearchById(id);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Clients> createClient(@RequestBody Clients client) {
        Clients createdClient = clientService.create(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateClient(@PathVariable Long id, @RequestBody Clients updatedClient) {
        try {
            Clients client = clientService.updateClient(id, updatedClient);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
