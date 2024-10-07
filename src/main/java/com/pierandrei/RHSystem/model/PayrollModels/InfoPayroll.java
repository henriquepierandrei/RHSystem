package com.pierandrei.RHSystem.model.PayrollModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class InfoPayroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // ID único para o pagamento do funcionário

    private long employeeId; // ID do funcionário

    private double wage; // Salário do funcionário

    private double bonus; // Bônus do funcionário

    private double discount; // Descontos do funcionário

    private LocalDate date; // Data

}
