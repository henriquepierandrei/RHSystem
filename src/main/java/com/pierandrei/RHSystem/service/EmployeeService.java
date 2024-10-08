package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.dto.Inputs.LoginDto;
import com.pierandrei.RHSystem.dto.Inputs.RegisterDto;
import com.pierandrei.RHSystem.dto.Responses.EmployeeContractResponseDto;
import com.pierandrei.RHSystem.dto.Responses.LoginResponseDto;
import com.pierandrei.RHSystem.dto.Responses.ResponseRegisterDto;
import com.pierandrei.RHSystem.infra.security.TokenService;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import com.pierandrei.RHSystem.repository.ContractRepository;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import com.pierandrei.RHSystem.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;
    private final PasswordEncoder passwordEncoder;
    private final InfoRepository infoRepository;
    private final TokenService tokenService;
    private final CpfValidator cpfValidator;



    // Obtém o histórico de informações (CHECK)
    public List<InfoPayroll> getSupport(long id){
        return this.infoRepository.findByEmployeeId(id);
    }

    // Login do funcionário (CHECK)
    public LoginResponseDto login(LoginDto loginDto) {
        Optional<EmployeeModel> user = employeeRepository.findByCpf(loginDto.cpf());

        // Verifica se o CPF está correto
        if (user.isEmpty()) {
            throw new IllegalArgumentException("CPF incorreto!");
        }

        // Verifica se o RG está correto
        if (!passwordEncoder.matches(loginDto.rg(), user.get().getRg())) {
            throw new IllegalArgumentException("RG incorreto!");
        }


        // Gera o token se o CPF e RG forem válidos
        String token = this.tokenService.generateToken(user.get());

        // Retorna o token e informações do usuário
        return new LoginResponseDto(token, user.get().getEmail(), user.get().getName());
    }

    // Validar Email (USADO)
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    // Validar Telefone (USADO)
    private boolean isValidPhone(String phone) {
        // Exemplo simples: verifica se o telefone contém apenas dígitos e tem um tamanho específico (ex: 10 ou 11 dígitos)
        return phone != null && phone.matches("\\d{10,11}");
    }


    // Registro do funcionário (CHECK)
    public ResponseRegisterDto register(RegisterDto body) {
        // Verifica se já existe um funcionário com o mesmo CPF
        Optional<EmployeeModel> employeeModel = employeeRepository.findByCpf(body.cpf());

        if (employeeModel.isEmpty()) {
            // Valida o CPF antes de prosseguir
            if (!this.cpfValidator.isValid(body.cpf())) {
                throw new IllegalArgumentException("CPF Inválido!");
            }

            // Cria um novo funcionário
            EmployeeModel newEmployee = new EmployeeModel();
            newEmployee.setRg(passwordEncoder.encode(body.rg())); // Verifique se você realmente deseja codificar o RG
            newEmployee.setEmail(body.email());
            newEmployee.setName(body.name());
            newEmployee.setPhone(body.phone());
            newEmployee.setDateBorn(body.dateBorn());
            newEmployee.setCpf(body.cpf());

            // Gera o token para o novo funcionário
            String token = this.tokenService.generateToken(newEmployee);

            // Validações adicionais para e-mail e telefone
            if (!isValidEmail(body.email())) {
                throw new IllegalArgumentException("E-mail inválido!");
            }

            if (!isValidPhone(body.phone())) {
                throw new IllegalArgumentException("Número de telefone inválido!");
            }

            // Salva o novo funcionário no banco de dados
            this.employeeRepository.save(newEmployee);

            // Retorna o DTO com os dados do registro
            return new ResponseRegisterDto(token, body.name(), body.cpf(), body.rg());
        }

        // Exceção lançada caso o CPF e RG já estejam cadastrados
        throw new IllegalArgumentException("Já existe um funcionário com o CPF e RG cadastrados!");
    }


    // Obtém o contrato do funcionário (CHECK)
    public EmployeeContractResponseDto getContract(String cpf, EmployeeModel employeeModel) {
        // Buscar contrato pelo CPF
        Optional<EmployeeContractModel> employeeContractModelOptional = this.contractRepository.findByCpf(cpf);

        if (!employeeModel.getCpf().equals(cpf)){
            throw new IllegalArgumentException("Esse CPF não te pertence!");
        }

        if (employeeContractModelOptional.isEmpty()) {
            throw new IllegalArgumentException("Não existe nenhum contrato registrado!");
        }

        EmployeeContractModel employeeContractModel = employeeContractModelOptional.get();


        return new EmployeeContractResponseDto(
                employeeContractModel.getCpf(),
                employeeContractModel.getStartDate(),
                employeeContractModel.getEndDate(),
                employeeContractModel.getTypeContract(),
                employeeContractModel.getPosition(),
                employeeContractModel.getWage(),
                employeeContractModel.getShift(),
                employeeContractModel.getStatusContract(),
                employeeContractModel.getBonus(),
                employeeContractModel.getAbsentValue()
        );
    }




}
