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
    private long id; // Unique identifier

    private long employeeId; // Reference to the employee

    private LocalDate startDate; // Vacation start date
    private LocalDate endDate; // Vacation end date

    private String reason; // Reason for vacation (optional)
}
