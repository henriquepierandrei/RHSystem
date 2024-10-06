package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.ContractRepository;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;


    // Busca por todos os funcionários
    public List<EmployeeModel> getAllEmployee(){
        return this.employeeRepository.findAll();

    }


    // Busca por todos os funcionários de acordo com o tipo de contrato (CLT, PJ, etc.)
    public List<EmployeeModel> getEmployeesByContractType(TypeContract typeContract) {
        List<EmployeeModel> employeeModels = this.contractRepository.findByTypeContract(typeContract);

        // Verificação opcional para tratar caso não encontre funcionários
        if (employeeModels.isEmpty()) {
            throw new IllegalArgumentException("Nenhum funcionário encontrado para o tipo de contrato: " + typeContract);
        }

        return employeeModels;
    }


    // Busca funcionários de acordo com seus salários (EX: value = 5000, funcionários com até R$5000,00 de salário serão visualizados!)
    public List<EmployeeModel> getEmployeesByWage(Double value) {
        // Busca todos os funcionários com salários menores ou iguais ao valor especificado
        List<EmployeeModel> employeeModels = this.contractRepository.findByWageLessThanEqual(value);

        // Verificação para tratar caso não encontre funcionários
        if (employeeModels.isEmpty()) {
            throw new IllegalArgumentException("Nenhum funcionário encontrado com salários até: R$" + value);
        }

        return employeeModels;
    }


    // Busca funcionários de acordo com o status do contrato
    public List<EmployeeModel> getEmployeesByStatus(StatusContract statusContract){
        // Busca todos os funcionários com status especificado
        List<EmployeeModel> employeeModels = this.contractRepository.findByStatusContract(statusContract);

        // Verificação para tratar caso não encontre funcionários
        if (employeeModels.isEmpty()) {
            throw new IllegalArgumentException("Nenhum funcionário encontrado com status: " + statusContract);
        }

        return employeeModels;
    }


    // Busca funcionários de acordo com o turno de trabalho
    public List<EmployeeModel> getEmployeesByShift(ShiftContract shiftContract){
        // Busca todos os funcionários com status especificado
        List<EmployeeModel> employeeModels = this.contractRepository.findByShift(shiftContract);

        // Verificação para tratar caso não encontre funcionários
        if (employeeModels.isEmpty()) {
            throw new IllegalArgumentException("Nenhum funcionário encontrado trabalhando no turno: " + shiftContract);
        }

        return employeeModels;
    }
}

    








}