package com.pierandrei.RHSystem.model.PayrollModels;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class PayrollModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // ID único da folha de pagamento

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "payroll_id")
    private List<InfoPayroll> employeesPayroll; // Lista dos detalhes de pagamento dos funcionários

    private LocalDate paymentDate; // Data do pagamento

    private double paidTotal; // Total pago na folha
}

