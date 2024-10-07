package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.dto.Inputs.RegisterEmployeeDto;
import com.pierandrei.RHSystem.dto.Inputs.UpdateEmailAndPhoneDto;
import com.pierandrei.RHSystem.dto.Responses.ResponseRegisterDto;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.EmployeeModels.LeaveModel;
import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import com.pierandrei.RHSystem.model.PayrollModels.PayrollModel;
import com.pierandrei.RHSystem.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;
    private final LeaveRepository leaveRepository;
    private final InfoRepository infoRepository;
    private final PayrollRepository payrollRepository;

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




    // Deletar um funcionário
    public void deleteEmployee(EmployeeModel employeeModel) {
        try {
            this.employeeRepository.delete(employeeModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover o funcionário: " + e.getMessage(), e);
        }

    }

    @Transactional
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


    @Transactional
    // Obtém a folha de pagamento
    public PayrollModel getPayroll() {
        // Obtendo todos os contratos de funcionários
        List<EmployeeContractModel> employeeContractModels = this.contractRepository.findAll();

        // Lista que armazenará as informações de pagamento de cada funcionário
        List<InfoPayroll> infoPayrolls = new ArrayList<>();

        PayrollModel payrollModel = new PayrollModel();
        double total = 0; // Variável para somar o total pago na folha

        // Iterando sobre todos os contratos dos funcionários
        for (EmployeeContractModel employeeContractModel : employeeContractModels) {
            InfoPayroll payroll = new InfoPayroll(); // Cria uma nova instância para cada funcionário
            payroll.setBonus(employeeContractModel.getBonus()); // Define o bônus do funcionário
            payroll.setEmployeeId(employeeContractModel.getId()); // Define o ID do funcionário

            // Verifica se o funcionário tem faltas registradas
            Optional<LeaveModel> leaveModelOptional = this.leaveRepository.findByEmployeeId(employeeContractModel.getId());

            if (leaveModelOptional.isPresent()) {
                // Se houver faltas, calcula o desconto baseado nos dias perdidos
                double dailyWage = employeeContractModel.getWage() / 20; // Supondo que sejam 20 dias úteis no mês
                double discount = dailyWage * leaveModelOptional.get().getMissedBusinessDays(); // Calcula o desconto com base nos dias perdidos

                payroll.setDiscount(discount); // Define o valor do desconto no payroll
                payroll.setWage(employeeContractModel.getWage() - discount); // Define o salário subtraído pelo desconto
                total += payroll.getWage(); // Soma o salário ao total pago na folha
            } else {
                // Se o funcionário não tiver faltas, usa o salário completo
                payroll.setDiscount(0); // Sem desconto
                payroll.setWage(employeeContractModel.getWage()); // Salário integral
                total += payroll.getWage(); // Soma o salário ao total pago
            }
            payroll.setDate(LocalDate.now()); // Define a data atual
            // Salva as informações do payroll no banco de dados
            this.infoRepository.save(payroll);
            infoPayrolls.add(payroll); // Adiciona o payroll à lista para ser incluído na folha de pagamento
        }

        // Define os valores no PayrollModel
        payrollModel.setEmployeesPayroll(infoPayrolls); // Define a lista de funcionários com seus pagamentos
        payrollModel.setPaymentDate(LocalDate.now()); // Define a data de pagamento como a data atual
        payrollModel.setPaidTotal(total); // Define o valor total pago na folha

        // Salva a folha de pagamento no banco de dados
        this.payrollRepository.save(payrollModel);

        return payrollModel; // Retorna o objeto PayrollModel preenchido
    }


}

