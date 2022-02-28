package com.api.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private String name;
    private Double dailyIncome;
    private Integer days;


    public Double getTotal(){
        return days * dailyIncome;
    }
}
