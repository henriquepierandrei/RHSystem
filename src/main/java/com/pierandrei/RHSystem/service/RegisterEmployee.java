package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.dto.Inputs.RegisterEmployeeDto;
import com.pierandrei.RHSystem.dto.Responses.RegisterResponseDto;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterEmployee {
    private final EmployeeRepository employeeRepository;                // Repositório da entidade funcionário
    private final CpfValidator cpfValidator;                            // Validar CPF

    public boolean isValidEmployeeRg(String rg){
        if (employeeRepository.existsByRg(rg)) return false;
        return true;
    }

    public boolean isValidEmployeeEmail(String email){
        if (employeeRepository.existsByEmail(email)) return false;
        return true;
    }

    public boolean isValidEmployeePhone(String phone){
        if (employeeRepository.existsByPhone(phone)) return false;
        return true;
    }



    // Método para cadastrar funcionário
    public Object registerNewEmployee(RegisterEmployeeDto registerEmployeeDto){
        EmployeeModel employeeModel = new EmployeeModel();

        // Validações para evitar duplicidade de dados!
        if (!cpfValidator.isValid(registerEmployeeDto.cpf()) || !isValidEmployeeRg(registerEmployeeDto.rg())) return "CPF ou RG já existentem!";

        if (!isValidEmployeeEmail(registerEmployeeDto.email())) return "Esse email já existe!";

        if (!isValidEmployeePhone(registerEmployeeDto.phone())) return "Esse número de telefone já existe!";


        // Adicionando os dados à nova entidade
        employeeModel.setRg(registerEmployeeDto.rg());
        employeeModel.setCpf(registerEmployeeDto.cpf());
        employeeModel.setName(registerEmployeeDto.name());
        employeeModel.setDateBorn(registerEmployeeDto.dateBorn());
        employeeModel.setEmail(registerEmployeeDto.email());
        employeeModel.setPhone(registerEmployeeDto.phone());
        this.employeeRepository.save(employeeModel);
        return (new RegisterResponseDto(registerEmployeeDto.name(), registerEmployeeDto.cpf(), registerEmployeeDto.rg()));

    }


}
