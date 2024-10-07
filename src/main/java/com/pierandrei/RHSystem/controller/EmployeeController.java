package com.pierandrei.RHSystem.controller;


import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import com.pierandrei.RHSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity getSupport(@AuthenticationPrincipal EmployeeModel employeeModel) {
        List<InfoPayroll> infoPayrolls = this.employeeService.getSupport(employeeModel.getId());
        if (infoPayrolls.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhum registro de pagamento!");

        return ResponseEntity.status(HttpStatus.OK).body(infoPayrolls);

    }
}
