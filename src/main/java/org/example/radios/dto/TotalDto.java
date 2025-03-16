package org.example.radios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalDto {

    private Double subTotalPrice;
    private Double totalPrice;
    private Double shippingCost;

}
