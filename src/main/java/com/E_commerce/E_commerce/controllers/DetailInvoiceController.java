package com.E_commerce.E_commerce.controllers;

import com.E_commerce.E_commerce.entities.DetailInvoice;
import com.E_commerce.E_commerce.services.DetailInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/detail-invoices")
public class DetailInvoiceController {

    @Autowired
    private DetailInvoiceService detailInvoiceService;

    @GetMapping
    public ResponseEntity<List<DetailInvoice>> getAllDetailInvoices() {
        try {
            List<DetailInvoice> detailInvoices = detailInvoiceService.searchAll();
            return new ResponseEntity<>(detailInvoices, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error to get all detail invoices", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailInvoice> getDetailInvoiceById(@PathVariable Long id) {
        try {
            DetailInvoice detailInvoice = detailInvoiceService.searchById(id);
            if (detailInvoice != null) {
                return new ResponseEntity<>(detailInvoice, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail invoice not found with that id" );
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with detail invoice by id", e);
        }
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<DetailInvoice>> getDetailInvoicesByInvoiceId(@PathVariable Long invoiceId) {
        try {
            List<DetailInvoice> detailInvoices = detailInvoiceService.searchByInvoiceId(invoiceId);
            return new ResponseEntity<>(detailInvoices, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving detail invoices by invoice id", e);
        }
    }

    @PostMapping
    public ResponseEntity<DetailInvoice> createDetailInvoice(@RequestBody DetailInvoice detailInvoice) {
        try {
            DetailInvoice createdDetailInvoice = detailInvoiceService.createInvoiceDetail(detailInvoice);
            return new ResponseEntity<>(createdDetailInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating detail invoice", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailInvoice> updateDetailInvoice(@PathVariable Long id, @RequestBody DetailInvoice updatedDetailInvoice) {
        try {
            DetailInvoice detailInvoice = detailInvoiceService.updateDetailInvoice(id, updatedDetailInvoice);
            return new ResponseEntity<>(detailInvoice, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            throw e; // Re-throw the specific exception
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating detail invoice", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailInvoice(@PathVariable Long id) {
        try {
            detailInvoiceService.deleteDetailInvoice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting detail invoice", e);
        }
    }
}
