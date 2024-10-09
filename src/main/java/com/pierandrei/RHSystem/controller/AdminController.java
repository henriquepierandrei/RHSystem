package com.pierandrei.RHSystem.controller;

import com.pierandrei.RHSystem.dto.Inputs.RegisterContractDto;
import com.pierandrei.RHSystem.dto.Responses.EmployeeContractResponseDto;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import com.pierandrei.RHSystem.service.AdminService;
import com.pierandrei.RHSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    // Atualizar informações de contato do funcionário
    @PutMapping("/update/contact")
    public ResponseEntity updateContactOfEmployee (@RequestParam(value = "cpf") String cpf, @RequestParam(value = "rg") String rg,
                                                   @RequestParam(value = "email", required = false) String email, @RequestParam(value = "phone", required = false) String phone){

        Optional<EmployeeModel> employeeModel = employeeRepository.findByCpfAndRg(cpf, rg);
        try {
            return ResponseEntity.ok(this.adminService.updatePhoneAndEmail(cpf, rg, email, phone));
        }catch (Exception e){
            // Retorna erro com a mensagem apropriada
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    // Buscar todos os funcionários com paginação e ordenação definida
    @GetMapping("/employees")
    public ResponseEntity<Page<EmployeeModel>> getAllEmployees(
            @RequestParam(value = "page", defaultValue = "0") int page) {

        // Define o tamanho da página e a ordenação padrão (ordenar por 'name' ascendente)
        int size = 10; // Defina o tamanho fixo da página
        Sort sort = Sort.by("name").ascending(); // Defina o campo e a ordem (ascendente)
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EmployeeModel> employees = employeeRepository.findAll(pageable);
        return ResponseEntity.ok(employees);
    }





}
