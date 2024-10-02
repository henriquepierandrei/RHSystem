package com.pierandrei.RHSystem.model.EmployeeModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class VacationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // Identificador único

    private long employeeId; // Referência ao funcionário

    private LocalDate startDate; // Data de início das férias
    private LocalDate endDate; // Data de término das férias

    private String reason; // Motivo das férias (opcional)
}
