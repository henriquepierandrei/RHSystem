package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.dto.Inputs.LoginDto;
import com.pierandrei.RHSystem.dto.Responses.ResponseDto;
import com.pierandrei.RHSystem.infra.security.TokenService;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import com.pierandrei.RHSystem.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final InfoRepository infoRepository;
    private final TokenService tokenService;
    private final CpfValidator cpfValidator;


    // Obtém o histórico de informações
    public List<InfoPayroll> getSupport(long id){
        return this.infoRepository.findByEmployeeId(id);
    }

    // Login do funcionário
    public ResponseDto login(LoginDto loginDto) {
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
        return new ResponseDto(token, user.get().getEmail(), user.get().getName());
    }











}
