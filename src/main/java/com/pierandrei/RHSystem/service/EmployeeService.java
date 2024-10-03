package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.dto.Inputs.RegisterEmployeeDto;
import com.pierandrei.RHSystem.dto.Inputs.UpdateEmailAndPhoneDto;
import com.pierandrei.RHSystem.dto.Responses.RegisterResponseDto;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CpfValidator cpfValidator;

    // Validação do RG, EMAIL & TELEFONE
    public boolean isValidEmployeeRg(String rg) {
        return !employeeRepository.existsByRg(rg);
    }
    public boolean isValidEmployeeEmail(String email) {
        return !employeeRepository.existsByEmail(email);
    }
    public boolean isValidEmployeePhone(String phone) {
        return !employeeRepository.existsByPhone(phone);
    }


    // Registrar um funcionário
    public RegisterResponseDto registerNewEmployee(RegisterEmployeeDto registerEmployeeDto) {
        // Validações para evitar duplicidade de dados
        if (!cpfValidator.isValid(registerEmployeeDto.cpf()) || !isValidEmployeeRg(registerEmployeeDto.rg())) {
            throw new IllegalArgumentException("CPF ou RG já existentes!");
        }

        if (!isValidEmployeeEmail(registerEmployeeDto.email())) {
            throw new IllegalArgumentException("Esse email já existe!");
        }

        if (!isValidEmployeePhone(registerEmployeeDto.phone())) {
            throw new IllegalArgumentException("Esse número de telefone já existe!");
        }

        // Criar nova entidade de funcionário
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setRg(registerEmployeeDto.rg());
        employeeModel.setCpf(registerEmployeeDto.cpf());
        employeeModel.setName(registerEmployeeDto.name());
        employeeModel.setDateBorn(registerEmployeeDto.dateBorn());
        employeeModel.setEmail(registerEmployeeDto.email());
        employeeModel.setPhone(registerEmployeeDto.phone());

        // Salvar no repositório
        employeeRepository.save(employeeModel);

        // Retornar resposta formatada
        return new RegisterResponseDto(registerEmployeeDto.name(), registerEmployeeDto.cpf(), registerEmployeeDto.rg());
    }


    // Deletar um funcionário
    public void deleteEmployee(EmployeeModel employeeModel) {
        try {
            this.employeeRepository.delete(employeeModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover o funcionário: " + e.getMessage(), e);
        }

    }


    // Atualizar informações de contato
    public String updatePhoneAndEmail(UpdateEmailAndPhoneDto updateEmailAndPhoneDto) {
        Optional<EmployeeModel> employeeModelOptional = this.employeeRepository.findByCpfAndRg(
                updateEmailAndPhoneDto.cpf(),
                updateEmailAndPhoneDto.rg());

        if (employeeModelOptional.isEmpty()) {
            throw new IllegalArgumentException("Funcionário inexistente!");
        }

        EmployeeModel employeeModel = employeeModelOptional.get();

        // Atualiza o email se não estiver vazio e se for válido (não duplicado)
        if (!updateEmailAndPhoneDto.email().isEmpty()) {
            if (!isValidEmployeeEmail(updateEmailAndPhoneDto.email())) {
                throw new IllegalArgumentException("Esse email já está em uso!");
            }
            employeeModel.setEmail(updateEmailAndPhoneDto.email());
        }

        // Atualiza o telefone se não estiver vazio e se for válido (não duplicado)
        if (!updateEmailAndPhoneDto.phone().isEmpty()) {
            if (!isValidEmployeePhone(updateEmailAndPhoneDto.phone())) {
                throw new IllegalArgumentException("Esse número de telefone já está em uso!");
            }
            employeeModel.setPhone(updateEmailAndPhoneDto.phone());
        }

        // Salva o modelo atualizado
        this.employeeRepository.save(employeeModel);
        return "Dados do usuário atualizados com sucesso!";
    }










}
