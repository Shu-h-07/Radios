package org.example.radios.service;

import org.example.radios.dto.YourOrderDto;
import org.example.radios.model.Product;
import org.example.radios.model.Result;
import org.example.radios.model.Total;
import org.example.radios.model.YourOrder;
import org.example.radios.repository.ProductRepo;
import org.example.radios.repository.TotalRepo;
import org.example.radios.repository.YourOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class YourOrderService {
    @Autowired
    YourOrderRepo yourOrderRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    TotalRepo totalRepo;

    public List<YourOrder> findAll() {
        return yourOrderRepo.findAll();
    }

    public YourOrder findById(UUID id) {
        return yourOrderRepo.findById(id).get();
    }

    public Result create(YourOrderDto yourOrderDto) {
        YourOrder yourOrder = new YourOrder();

        Optional<Product> byId = productRepo.findById(yourOrderDto.getProductId());
        Product product = byId.get();
        yourOrder.setProduct((List<Product>) product);

        Optional<Total> byId1 = totalRepo.findById(yourOrderDto.getSubTotalId());
        Total subTotal = byId1.get();
        yourOrder.setSubTotal(subTotal);

        Optional<Total> byId2 = totalRepo.findById(yourOrderDto.getTotalId());
        Total total = byId2.get();
        yourOrder.setTotal(total);

        Optional<Total> byId3 = totalRepo.findById(yourOrderDto.getShippingId());
        Total shipping = byId3.get();
        yourOrder.setShipping(shipping);

        yourOrderRepo.save(yourOrder);
        return new Result(true ,"Your order created and saved" );
    }

    public Result update(YourOrderDto yourOrderDto, UUID id) {
        Optional<YourOrder> byId = yourOrderRepo.findById(id);
        if (byId.isPresent()) {
            YourOrder yourOrder = byId.get();
            Optional<Product> byProductId = productRepo.findById(yourOrderDto.getProductId());
            Product product = byProductId.get();
            yourOrder.setProduct((List<Product>) product);

            Optional<Total> byId1 = totalRepo.findById(yourOrderDto.getSubTotalId());
            Total subTotal = byId1.get();
            yourOrder.setSubTotal(subTotal);

            Optional<Total> byId2 = totalRepo.findById(yourOrderDto.getTotalId());
            Total total = byId2.get();
            yourOrder.setTotal(total);

            Optional<Total> byId3 = totalRepo.findById(yourOrderDto.getShippingId());
            Total shipping = byId3.get();
            yourOrder.setShipping(shipping);

            yourOrderRepo.save(yourOrder);
            return new Result(true ,"Your order updated" );
        }
        return new Result(false ,"Your order not found" );
    }

    public Result delete(UUID id) {
        Optional<YourOrder> byId = yourOrderRepo.findById(id);
        if (byId.isPresent()) {
            yourOrderRepo.delete(byId.get());
            return new Result(true ,"Your order deleted" );
        }
        return new Result(false ,"Your order not found" );
    }
}
