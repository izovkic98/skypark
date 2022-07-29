package com.tvz.skypark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvz.skypark.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
