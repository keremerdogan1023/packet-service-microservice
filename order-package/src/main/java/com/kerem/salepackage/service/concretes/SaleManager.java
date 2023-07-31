package com.kerem.salepackage.service.concretes;

import com.kerem.commonpackage.kafka.producer.KafkaProducer;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.salepackage.entities.Sale;
import com.kerem.salepackage.repository.SaleRepository;
import com.kerem.salepackage.service.abstracts.SaleService;
import com.kerem.salepackage.service.dto.requests.CreateSaleRequest;
import com.kerem.salepackage.service.dto.requests.UpdateSaleRequest;
import com.kerem.salepackage.service.dto.responses.CreateSaleResponse;
import com.kerem.salepackage.service.dto.responses.GetAllSalesResponse;
import com.kerem.salepackage.service.dto.responses.GetSaleResponse;
import com.kerem.salepackage.service.dto.responses.UpdateSaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;

    @Override
    public List<GetAllSalesResponse> getAll() {
        var sales = repository.findAll();
        var response = sales.stream().map(sale -> mapper.forResponse().map(sale,GetAllSalesResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetSaleResponse getById(int id) {
        var sale = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(sale, GetSaleResponse.class);
        return response;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        var sale = mapper.forRequest().map(request, Sale.class);
        sale.setId(0);
        sale.setSaledAt(LocalDate.now());
        repository.save(sale);
        return null;


    }

    @Override
    public UpdateSaleResponse update(UpdateSaleRequest request, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    private void sendKafkaSaleCreatedEvent()
}
