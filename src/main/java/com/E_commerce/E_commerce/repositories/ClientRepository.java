package com.E_commerce.E_commerce.repositories;

import com.E_commerce.E_commerce.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Clients, Long> {
}
