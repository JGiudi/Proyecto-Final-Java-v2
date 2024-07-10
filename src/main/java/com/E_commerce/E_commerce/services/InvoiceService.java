package com.E_commerce.E_commerce.services;

import com.E_commerce.E_commerce.entities.Invoice;
import com.E_commerce.E_commerce.repositories.InvoiceRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice create(Invoice invoice) {
        try {
            return invoiceRepository.save(invoice);
        } catch (Exception e) {
            throw new ServiceException("Error creating invoice", e);
        }
    }

    public Invoice searchById(Long id) {
        try {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
            if (!invoiceOptional.isPresent()) {
                throw new ServiceException("Invoice not found with id: " + id);
            }
            return invoiceOptional.get();
        } catch (Exception e) {
            throw new ServiceException("Error searching by id", e);
        }
    }

    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        try {
            Invoice existingInvoice = invoiceRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Invoice not found with id: " + id));

            existingInvoice.setClients(updatedInvoice.getClients());
            existingInvoice.setTotal(updatedInvoice.getTotal());
            existingInvoice.setState(updatedInvoice.getState());
            existingInvoice.setDateEmission(updatedInvoice.getDateEmission());
            existingInvoice.setPayMethod(updatedInvoice.getPayMethod());
            existingInvoice.setDetailInvoices(updatedInvoice.getDetailInvoices());

            return invoiceRepository.save(existingInvoice);
        } catch (Exception e) {
            throw new ServiceException("Error updating invoice", e);
        }
    }

    public void deleteInvoice(Long id) {
        try {
            if (!invoiceRepository.existsById(id)) {
                throw new ServiceException("Invoice not found with id: " + id);
            }
            invoiceRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting invoice", e);
        }
    }
}
