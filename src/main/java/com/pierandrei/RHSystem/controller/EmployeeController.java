package com.pierandrei.RHSystem.controller;


import com.pierandrei.RHSystem.dto.Inputs.LoginDto;
import com.pierandrei.RHSystem.dto.Inputs.RegisterDto;
import com.pierandrei.RHSystem.dto.Responses.LoginResponseDto;
import com.pierandrei.RHSystem.dto.Responses.ResponseRegisterDto;
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

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TokenService tokenService;




    @GetMapping
    public ResponseEntity getSupport(@AuthenticationPrincipal EmployeeModel employeeModel) {
        List<InfoPayroll> infoPayrolls = this.employeeService.getSupport(employeeModel.getId());
        if (infoPayrolls.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhum registro de pagamento!");

        return ResponseEntity.status(HttpStatus.OK).body(infoPayrolls);

    }
}
