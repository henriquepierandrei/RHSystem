package com.pierandrei.RHSystem.controller;


import com.pierandrei.RHSystem.dto.Inputs.LoginDto;
import com.pierandrei.RHSystem.dto.Inputs.RegisterDto;
import com.pierandrei.RHSystem.dto.Responses.EmployeeContractResponseDto;
import com.pierandrei.RHSystem.dto.Responses.LoginResponseDto;
import com.pierandrei.RHSystem.dto.Responses.ResponseRegisterDto;
import com.pierandrei.RHSystem.infra.security.TokenService;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import com.pierandrei.RHSystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TokenService tokenService;




    // Obtém o contrato atual do funcionário
    @Operation(summary = "Funcionário obtém as informações do contrato atual.")
    @GetMapping("/contract")
    public ResponseEntity getContract(@AuthenticationPrincipal EmployeeModel employeeModel){
        try {
            // Utilizando o dto de response para obter o contrato
            EmployeeContractResponseDto response =  this.employeeService.getContract(employeeModel);

            // Retorna sucesso com o contrato
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Retorna erro com a mensagem apropriada (Não existir contrato)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
