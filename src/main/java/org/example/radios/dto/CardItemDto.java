package org.example.radios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CardItemDto {
    private UUID subTotal;
    private UUID productName;
    private UUID productPrice;
    private UUID productId;

}
