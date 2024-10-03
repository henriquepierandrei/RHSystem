package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.dto.Inputs.RegisterContractDto;
import com.pierandrei.RHSystem.dto.Inputs.UpdateContractDto;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.ContractRepository;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    // Criar contrato de trabalho do funcionário
    public EmployeeContractModel createContract(RegisterContractDto registerContractDto) {

        // Verificando se existe um funcionário com cpf e rg fornecidos
        EmployeeModel employeeModel = this.employeeRepository.findByCpfAndRg(registerContractDto.cpf(), registerContractDto.rg())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário inexistente!"));

        // Validação para evitar duplicidade de contrato para cada funcionário
        if (this.contractRepository.findByCpfAndRg(registerContractDto.cpf(), registerContractDto.rg()).isPresent()) {
            throw new IllegalArgumentException("Já existe um contrato com esse RG e CPF!");
        }

        // Criação do modelo de contrato
        EmployeeContractModel employeeContractModel = new EmployeeContractModel();

        employeeContractModel.setStatusContract(registerContractDto.statusContract());   // Adicionando o status do contrato
        employeeContractModel.setTypeContract(registerContractDto.typeContract());       // Adicionando o tipo do contrato
        employeeContractModel.setShift(registerContractDto.shiftContract());             // Adicionando o turno

        employeeContractModel.setCpf(registerContractDto.cpf());                        // Adicionando o CPF do funcionário
        employeeContractModel.setRg(registerContractDto.rg());                          // Adicionando o RG do funcionário

        employeeContractModel.setBonus(registerContractDto.bonus());                    // Adicionando o bônus do funcionário se houver
        employeeContractModel.setPosition(registerContractDto.position());              // Adicionando o cargo do funcionário
        employeeContractModel.setAbsentValue(registerContractDto.absentValue());         // Adicionando o valor de faltas

        employeeContractModel.setStartDate(registerContractDto.startDate());            // Adicionando a data de início

        employeeContractModel.setWage(registerContractDto.wage());                      // Adicionando o salário do funcionário

        // Salvando no banco de dados
        contractRepository.save(employeeContractModel);
        return employeeContractModel;
    }


    // Atualizar contrato de trabalho do funcionário
    public EmployeeContractModel updateContract(UpdateContractDto updateContractDto) {
        // Verificando se existe um funcionário com CPF e RG fornecidos
        EmployeeModel employeeModel = this.employeeRepository.findByCpfAndRg(updateContractDto.cpf(), updateContractDto.rg())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário inexistente!"));

        // Verificando se existe um contrato para o funcionário fornecido
        EmployeeContractModel employeeContractModel = this.contractRepository.findByCpfAndRg(updateContractDto.cpf(), updateContractDto.rg())
                .orElseThrow(() -> new IllegalArgumentException("Contrato inexistente!"));

        // Atualizando os dados do contrato, caso não estejam vazios/nulos
        if (updateContractDto.position() != null && !updateContractDto.position().isEmpty()) {
            employeeContractModel.setPosition(updateContractDto.position());
        }

        if (updateContractDto.typeContract() != null) {
            employeeContractModel.setTypeContract(updateContractDto.typeContract());
        }

        if (updateContractDto.wage() != null) {
            employeeContractModel.setWage(updateContractDto.wage());
        }

        if (updateContractDto.shiftContract() != null) {
            employeeContractModel.setShift(updateContractDto.shiftContract());
        }

        if (updateContractDto.statusContract() != null) {
            employeeContractModel.setStatusContract(updateContractDto.statusContract());
        }

        if (updateContractDto.bonus() != null) {
            employeeContractModel.setBonus(updateContractDto.bonus());
        }

        if (updateContractDto.absentValue() != null) {
            employeeContractModel.setAbsentValue(updateContractDto.absentValue());
        }

        // Salvando as alterações no banco de dados
        this.contractRepository.save(employeeContractModel);
        return employeeContractModel;
    }


    // Excluir um contrato já existente
    public void deleteContract(String cpf, String rg){
        // Verificando se existe um contrato para o funcionário fornecido de acordo com se cpf e rg
        EmployeeContractModel employeeContractModel = this.contractRepository.findByCpfAndRg(cpf, rg)
                .orElseThrow(() -> new IllegalArgumentException("Contrato inexistente!"));
        this.contractRepository.delete(employeeContractModel);
    }



}
