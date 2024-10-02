package com.pierandrei.RHSystem.model.EmployeeModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class LeaveModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // Identificador único

    private long employeeId; // Referência ao funcionário
    private LocalDate startDate; // Data de início da licença
    private LocalDate endDate; // Data de término da licença

    private String type; // Tipo de licença (médica, maternidade, etc.)
    private String reason; // Motivo da licença (opcional)
}
