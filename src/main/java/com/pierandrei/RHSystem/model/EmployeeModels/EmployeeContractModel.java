package com.pierandrei.RHSystem.model.EmployeeModels;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class EmployeeContractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // ID do contrato do funcionário

    private String cpf;     // CPF do funcionário
    private String rg;              // RG do funcionário

    private LocalDate startDate; // Data de início do contrato
    private LocalDate endDate; // Data de término do contrato

    @Enumerated(EnumType.STRING)
    private TypeContract typeContract; // Tipo de contrato (CLT, PJ, etc.)

    private String position; // Cargo do funcionário

    private double wage; // Salário do funcionário

    @Enumerated(EnumType.STRING)
    private ShiftContract shift; // Turno de trabalho do funcionário

    @Enumerated(EnumType.STRING)
    private StatusContract statusContract; // Status do contrato (ativo, encerrado, etc.)

    private double bonus; // Valor de bônus do funcionário

    private double absentValue; // Valor associado a faltas

}
