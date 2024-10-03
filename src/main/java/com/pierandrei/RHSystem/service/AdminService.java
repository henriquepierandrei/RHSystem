package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.ContractRepository;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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




}