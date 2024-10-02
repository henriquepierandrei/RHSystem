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
    private long id; // Unique identifier

    private long employeeId; // Reference to the employee
    private LocalDate startDate; // Leave start date
    private LocalDate endDate; // Leave end date

    private String type; // Type of leave (medical, maternity, etc.)
    private String reason; // Reason for leave (optional)
}
