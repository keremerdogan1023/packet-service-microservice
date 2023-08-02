package com.kerem.salepackage.service.concretes;

import com.kerem.commonpackage.events.sale.SaleCreatedEvent;
import com.kerem.commonpackage.kafka.producer.KafkaProducer;
import com.kerem.commonpackage.utils.dto.SaleClientResponse;
import com.kerem.commonpackage.utils.mappers.ModelMapperService;
import com.kerem.salepackage.api.clients.AudiobookClient;
import com.kerem.salepackage.api.clients.PodcastClient;
import com.kerem.salepackage.api.clients.RecordClient;
import com.kerem.salepackage.api.clients.SongClient;
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
    private final AudiobookClient audiobookClient;
    private final PodcastClient podcastClient;
    private final RecordClient recordClient;
    private final SongClient songClient;

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
        var saleClientResponse = filterClientRequest(request);
        var totalPrice = getTotalPrice(saleClientResponse, sale.getQuantity());
        sale.setTotalPrice(totalPrice);
        var createdSale = repository.save(sale);
        sendKafkaSaleCreatedEvent(createdSale,saleClientResponse);
        var response = mapper.forResponse().map(sale, CreateSaleResponse.class);
        return response;

    }

    @Override
    public UpdateSaleResponse update(UpdateSaleRequest request, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    private double getTotalPrice(SaleClientResponse response, int quantity){
        return response.getPrice() * quantity;
    }
    private void sendKafkaSaleCreatedEvent(Sale createdSale, SaleClientResponse saleClientResponse){
        var createdSaleEvent = mapper.forRequest().map(createdSale, SaleCreatedEvent.class);
        createdSaleEvent.setQuantity(saleClientResponse.getQuantity()- createdSale.getQuantity());
        if (createdSale.getPackageId() == 1){
            producer.sendMessage(createdSaleEvent,"audiobook-order-created");
        } else if (createdSale.getPackageId() == 2) {
            producer.sendMessage(createdSaleEvent,"podcast-order-created");
        } else if (createdSale.getPackageId() == 3) {
            producer.sendMessage(createdSaleEvent,"record-order-created");
        } else if (createdSale.getPackageId() == 4) {
            producer.sendMessage(createdSaleEvent,"song-order-created");
        }
    }
    private SaleClientResponse filterClientRequest(CreateSaleRequest request){
        SaleClientResponse response = null;
        if (request.getPackageId() == 1){
            response = audiobookClient.getAudiobook(request.getMediaId());
        } else if (request.getPackageId() == 2) {
            response = podcastClient.getPodcast(request.getMediaId());
        } else if (request.getPackageId() == 3) {
            response = recordClient.getRecord(request.getMediaId());
        } else if (request.getPackageId() == 4) {
            response = songClient.getSong(request.getMediaId());
        }
        return response;
    }

}
