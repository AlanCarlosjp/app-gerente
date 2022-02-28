package com.pay.roll.dto;

import com.pay.roll.entity.Payment;
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

    public PaymentDto(Payment entity){
        this.name = entity.getName();
        this.dailyIncome = entity.getDailyIncome();
        this.days = entity.getDays();
    }

    public Double getTotal(){
        return days * dailyIncome;
    }
}
