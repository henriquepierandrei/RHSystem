package com.pierandrei.RHSystem.controller;

import com.pierandrei.RHSystem.dto.Inputs.RegisterContractDto;
import com.pierandrei.RHSystem.dto.Responses.EmployeeContractResponseDto;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import com.pierandrei.RHSystem.service.AdminService;
import com.pierandrei.RHSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final EmployeeRepository employeeRepository;

    // Criar contrato do funcionário
    @PostMapping("/contract")
    public ResponseEntity createContract(@RequestBody @Validated RegisterContractDto registerContractDto){
        try {
            EmployeeContractResponseDto responseDto = this.adminService.createContract(registerContractDto);
            return ResponseEntity.ok(responseDto);
        }catch (Exception e){
            // Retorna erro com a mensagem apropriada
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Deletar contrato do funcionário e funcionário
    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployeeAndContract(@RequestParam(value = "cpf") String cpf, @RequestParam(value = "rg") String rg){
        Optional<EmployeeModel> employeeModel = employeeRepository.findByCpfAndRg(cpf, rg);

        try {
            return ResponseEntity.ok(this.adminService.deleteEmployee(employeeModel.get()));
        }catch (Exception e){
            // Retorna erro com a mensagem apropriada
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }


}
