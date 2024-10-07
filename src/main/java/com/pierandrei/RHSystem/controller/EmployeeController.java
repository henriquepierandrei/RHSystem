package com.pierandrei.RHSystem.controller;


import com.pierandrei.RHSystem.dto.Inputs.LoginDto;
import com.pierandrei.RHSystem.dto.Responses.ResponseDto;
import com.pierandrei.RHSystem.infra.security.TokenService;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import com.pierandrei.RHSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto body) {
        try {
            // Utiliza o método de login do service
            ResponseDto response = employeeService.login(body);

            // Retorna sucesso com o token e informações do usuário
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Retorna erro com a mensagem apropriada (CPF ou RG incorreto)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @GetMapping
    public ResponseEntity getSupport(@AuthenticationPrincipal EmployeeModel employeeModel) {
        List<InfoPayroll> infoPayrolls = this.employeeService.getSupport(employeeModel.getId());
        if (infoPayrolls.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhum registro de pagamento!");

        return ResponseEntity.status(HttpStatus.OK).body(infoPayrolls);

    }
}
