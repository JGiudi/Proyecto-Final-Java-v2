package com.E_commerce.E_commerce.repositories;

import com.E_commerce.E_commerce.entities.DetailInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailInvoiceRepository extends JpaRepository<DetailInvoice, Long> {
    List<DetailInvoice> findAllByInvoiceId(Long invoiceId);
}
