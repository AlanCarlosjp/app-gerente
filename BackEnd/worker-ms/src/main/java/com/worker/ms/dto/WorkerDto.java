package com.worker.ms.dto;


import com.worker.ms.entity.Worker;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {

    private Long id;
    private String name;
    private Double dailyIncome;

    public WorkerDto(Worker entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.dailyIncome = entity.getDailyIncome();
    }
}
