package com.kerem.salepackage.service.abstracts;

import com.kerem.salepackage.service.dto.requests.CreateSaleRequest;
import com.kerem.salepackage.service.dto.requests.UpdateSaleRequest;
import com.kerem.salepackage.service.dto.responses.CreateSaleResponse;
import com.kerem.salepackage.service.dto.responses.GetAllSalesResponse;
import com.kerem.salepackage.service.dto.responses.GetSaleResponse;
import com.kerem.salepackage.service.dto.responses.UpdateSaleResponse;

import java.util.List;

public interface SaleService {
    List<GetAllSalesResponse> getAll();
    GetSaleResponse getById(int id);
    CreateSaleResponse add(CreateSaleRequest request);
    UpdateSaleResponse update(UpdateSaleRequest request, int id);
    void delete(int id);


}
