package com.pierandrei.RHSystem.model.EmployeeModels;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // ID único do funcionário

    // Dados Pessoais
    private String name; // Nome do funcionário
    private String email; // E-mail do funcionário
    private Date dateBorn; // Data de nascimento do funcionário
    private String cpf; // CPF do funcionário
    private String phone; // Telefone do funcionário
    private String rg; // RG do funcionário

    // Dados de Documentos
    private EmployeeContractModel employeeContractModel; // Referência ao modelo de contrato do funcionário
}
