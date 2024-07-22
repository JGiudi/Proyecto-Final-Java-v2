package com.E_commerce.E_commerce.services;

import com.E_commerce.E_commerce.entities.Invoice;
import com.E_commerce.E_commerce.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice create(Invoice invoice) {
        try {
            return invoiceRepository.save(invoice);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating invoice", e);
        }
    }

    public Invoice searchById(Long id) {
        try {
            return invoiceRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found with id"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error searching by id", e);
        }
    }

    public List<Invoice> searchAll() {
        try {
            return invoiceRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error searching all invoices", e);
        }
    }

    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        try {
            Invoice existingInvoice = invoiceRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found with id"));

            existingInvoice.setClients(updatedInvoice.getClients());
            existingInvoice.setTotal(updatedInvoice.getTotal());
            existingInvoice.setState(updatedInvoice.getState());
            existingInvoice.setDateEmission(updatedInvoice.getDateEmission());
            existingInvoice.setPayMethod(updatedInvoice.getPayMethod());
            existingInvoice.setDetailInvoices(updatedInvoice.getDetailInvoices());

            return invoiceRepository.save(existingInvoice);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating invoice", e);
        }
    }

    public void deleteInvoice(Long id) {
        try {
            if (!invoiceRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found with that id");
            }
            invoiceRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting invoice", e);
        }
    }
}
