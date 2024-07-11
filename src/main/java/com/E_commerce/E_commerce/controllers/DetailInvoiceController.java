package com.E_commerce.E_commerce.controllers;

import com.E_commerce.E_commerce.entities.DetailInvoice;
import com.E_commerce.E_commerce.services.DetailInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/detail-invoices")
public class DetailInvoiceController {

    @Autowired
    private DetailInvoiceService detailInvoiceService;

    @GetMapping
    public ResponseEntity<List<DetailInvoice>> getAllDetailInvoices() {
        List<DetailInvoice> detailInvoices = detailInvoiceService.searchAll();
        return new ResponseEntity<>(detailInvoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailInvoice> getDetailInvoiceById(@PathVariable Long id) {
        DetailInvoice detailInvoice = detailInvoiceService.searchById(id);
        if (detailInvoice != null) {
            return new ResponseEntity<>(detailInvoice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<DetailInvoice>> getDetailInvoicesByInvoiceId(@PathVariable Long invoiceId) {
        List<DetailInvoice> detailInvoices = detailInvoiceService.searchByInvoiceId(invoiceId);
        return new ResponseEntity<>(detailInvoices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailInvoice> createDetailInvoice(@RequestBody DetailInvoice detailInvoice) {
        DetailInvoice createdDetailInvoice = detailInvoiceService.createInvoiceDetail(detailInvoice);
        return new ResponseEntity<>(createdDetailInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailInvoice> updateDetailInvoice(@PathVariable Long id, @RequestBody DetailInvoice updatedDetailInvoice) {
        try {
            DetailInvoice detailInvoice = detailInvoiceService.updateDetailInvoice(id, updatedDetailInvoice);
            return new ResponseEntity<>(detailInvoice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailInvoice(@PathVariable Long id) {
        try {
            detailInvoiceService.deleteDetailInvoice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}