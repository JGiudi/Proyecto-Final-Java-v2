package com.E_commerce.E_commerce.services;

import com.E_commerce.E_commerce.entities.Clients;
import com.E_commerce.E_commerce.entities.Products;
import com.E_commerce.E_commerce.repositories.ClientRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Clients create(Clients client) {
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new ServiceException("Error creating client", e);
        }
    }
    public Clients SearchById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void updatePoints(Long idCliente, int puntos) {
        Clients cliente = clientRepository.findById(idCliente).orElse(null);
        if (cliente != null) {
            int nuevosPuntos = cliente.getPoints() + puntos;
            cliente.setPoints(nuevosPuntos);
            clientRepository.save(cliente);
        }
    }
    public Clients updateClient(Long id, Clients updatedClient) {
        try {
            Clients existingClient = clientRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Client not found with id: " + id));

            existingClient.setName(updatedClient.getName());
            existingClient.setEmail(updatedClient.getEmail());
            existingClient.setDni(updatedClient.getDni());
            existingClient.setStreet(updatedClient.getStreet());
            existingClient.setSurname(updatedClient.getSurname());
            existingClient.setPoints(updatedClient.getPoints());

            return clientRepository.save(existingClient);
        } catch (Exception e) {
            throw new ServiceException("Error updating client", e);
        }
    }
    public List<Clients> searchAll() {
        try {
            return clientRepository.findAll();
        }catch (Exception e){
            throw new ServiceException("Error retrieving clients", e);
        }
    }
    public void deleteClient(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting client", e);
        }
    }
}

