package com.kerem.salepackage.repository;

import com.kerem.salepackage.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Integer> {
}
