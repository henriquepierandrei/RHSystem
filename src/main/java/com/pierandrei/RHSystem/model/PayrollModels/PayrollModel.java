package com.pierandrei.RHSystem.model.PayrollModels;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class PayrollModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private List<Long> employeesIds;

    private List<Double> wageForEmployeeId;

    private List<Double> bonusForEmployeeId;

    private LocalDate PaymentDate;

    private double paidTotal;


}
