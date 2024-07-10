package com.E_commerce.E_commerce.services;

import com.E_commerce.E_commerce.entities.DetailInvoice;
import com.E_commerce.E_commerce.repositories.DetailInvoiceRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailInvoiceService {
    @Autowired
    private DetailInvoiceRepository detailInvoiceRepository;

    public DetailInvoice createInvoiceDetail(DetailInvoice detailInvoice) {
        try {
            return detailInvoiceRepository.save(detailInvoice);
        } catch (Exception e) {
            throw new ServiceException("Error creating detail invoice", e);
        }
    }

    public DetailInvoice searchById(Long id) {
        try {
            Optional<DetailInvoice> detailInvoiceOptional = detailInvoiceRepository.findById(id);
            if (!detailInvoiceOptional.isPresent()) {
                throw new ServiceException("Detail invoice not found with id: " + id);
            }
            return detailInvoiceOptional.get();
        } catch (Exception e) {
            throw new ServiceException("Error searching by id", e);
        }
    }

    public List<DetailInvoice> searchByInvoiceId(Long invoiceId) {
        try {
            return detailInvoiceRepository.findAllByInvoiceId(invoiceId);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving detail invoices by invoice id", e);
        }
    }

    public double subTotal(DetailInvoice detailInvoice) {
        return detailInvoice.getQuantity() * detailInvoice.getUnitPrice();
    }

    public DetailInvoice updateDetailInvoice(Long id, DetailInvoice updatedDetailInvoice) {
        try {
            DetailInvoice existingDetailInvoice = detailInvoiceRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Detail invoice not found with id: " + id));

            existingDetailInvoice.setQuantity(updatedDetailInvoice.getQuantity());
            existingDetailInvoice.setUnitPrice(updatedDetailInvoice.getUnitPrice());
            existingDetailInvoice.setProduct(updatedDetailInvoice.getProduct());

            return detailInvoiceRepository.save(existingDetailInvoice);
        } catch (Exception e) {
            throw new ServiceException("Error updating detail invoice", e);
        }
    }

    public void deleteDetailInvoice(Long id) {
        try {
            if (!detailInvoiceRepository.existsById(id)) {
                throw new ServiceException("Detail invoice not found with id: " + id);
            }
            detailInvoiceRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting detail invoice", e);
        }
    }
}
