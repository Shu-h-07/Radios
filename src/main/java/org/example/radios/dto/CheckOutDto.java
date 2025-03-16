package org.example.radios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String companyName;

}
