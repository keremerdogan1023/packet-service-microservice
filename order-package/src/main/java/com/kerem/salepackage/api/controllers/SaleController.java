package com.kerem.salepackage.api.controllers;

import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.salepackage.service.abstracts.SaleService;
import com.kerem.salepackage.service.dto.requests.CreateSaleRequest;
import com.kerem.salepackage.service.dto.requests.UpdateSaleRequest;
import com.kerem.salepackage.service.dto.responses.CreateSaleResponse;
import com.kerem.salepackage.service.dto.responses.GetAllSalesResponse;
import com.kerem.salepackage.service.dto.responses.GetSaleResponse;
import com.kerem.salepackage.service.dto.responses.UpdateSaleResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {
    private final SaleService service;
    @GetMapping
    public List<GetAllSalesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetSaleResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateSaleResponse update(@PathVariable int id, @Valid @RequestBody UpdateSaleRequest request) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSaleResponse add(@Valid @RequestBody CreateSaleRequest request) {
        return service.add(request);
    }
}
