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
    private long id; // ID único da folha de pagamento

    private List<Long> employeesIds; // Lista de IDs dos funcionários

    private List<Double> wageForEmployeeId; // Lista de salários correspondentes aos IDs dos funcionários

    private List<Double> bonusForEmployeeId; // Lista de bônus correspondentes aos IDs dos funcionários

    private List<Double> discountForEmployeeId; // Lista de descontos correspondentes aos IDs dos funcionários

    private LocalDate paymentDate; // Data do pagamento

    private double paidTotal; // Total pago na folha
}
