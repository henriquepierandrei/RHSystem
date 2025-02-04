package com.pierandrei.RHSystem.controller;

import com.pierandrei.RHSystem.dto.Inputs.RegisterContractDto;
import com.pierandrei.RHSystem.dto.Responses.EmployeeContractResponseDto;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.EmployeeModels.LeaveModel;
import com.pierandrei.RHSystem.repository.ContractRepository;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import com.pierandrei.RHSystem.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;

    // Criar contrato do funcionário
    @Operation(summary = "Criar contrato do funcionário")
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
    @Operation(summary = "Deletar contrato e funcionário")
    @DeleteMapping("/delete/all")
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
    @Operation(summary = "Atualizar informações de contato do funcionário")
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
    @Operation(summary = "Buscar todos os funcionários com paginação e ordenação definida")
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






    // Buscar todos os funcionários com paginação e ordenação definida através do tipo do contrato
    @Operation(summary = "Buscar todos os funcionários com paginação e ordenação definida através do tipo do contrato")
    @GetMapping("/employees/type")
    public ResponseEntity getAllEmployeesBType(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "typeContract") TypeContract typeContract) {

        try {
            // Define a ordenação (opcional)
            Sort sort = Sort.by("id").ascending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<EmployeeModel> employees = this.adminService.getEmployeesByContractType(typeContract, pageable);

            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        }
    }



    // Buscar todos os funcionários com paginação e ordenação definida através do cargo
    @Operation(summary = "Buscar todos os funcionários com paginação e ordenação definida através do cargo")
    @GetMapping("/employees/position")
    public ResponseEntity getAllEmployeesByWage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "position") String position) {

        try {
            // Define a ordenação (opcional)
            Sort sort = Sort.by("statusContract").ascending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<EmployeeModel> employees = this.adminService.getEmployeesByPosition(position, pageable);

            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        }
    }



    // Buscar todos os funcionários com paginação e ordenação definida através do status do contrato
    @Operation(summary = "Buscar todos os funcionários com paginação e ordenação definida através do status do contrato")
    @GetMapping("/employees/status")
    public ResponseEntity getAllEmployeesByStatus(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "statusContract") StatusContract statusContract) {

        try {
            // Define a ordenação (opcional)
            Sort sort = Sort.by("id").ascending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<EmployeeModel> employees = this.adminService.getEmployeesByStatus(statusContract, pageable);

            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        }
    }



    // Buscar todos os funcionários com paginação e ordenação definida através do turno do contrato
    @Operation(summary = "Buscar todos os funcionários com paginação e ordenação definida através do turno do contrato")
    @GetMapping("/employees/shift")
    public ResponseEntity getAllEmployeesByShift(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "shift")ShiftContract shiftContract) {

        try {
            // Define a ordenação (opcional)
            Sort sort = Sort.by("id").ascending();
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<EmployeeModel> employees = this.adminService.getEmployeesByShift(shiftContract, pageable);

            return ResponseEntity.ok(employees);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        }
    }



    // Atualizar o status do contrato do funcionário
    @Operation(summary = "Atualizar o status do contrato do funcionário")
    @PutMapping("/update/contract/status")
    public ResponseEntity updateStatusOfTheContract(
            @RequestParam(value = "cpf") String cpf,
            @RequestParam(value = "rg") String rg,
            @RequestParam(value = "statusContract") StatusContract status) {

        // Buscar funcionário pelo CPF e RG
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findByCpfAndRg(cpf, rg);
        if (employeeModelOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe nenhum funcionário com esse CPF e RG!");
        }

        EmployeeModel employeeModel = employeeModelOptional.get();

        // Buscar contrato vinculado ao funcionário
        Optional<EmployeeContractModel> employeeContractModelOptional = contractRepository.findByEmployee(employeeModel);
        if (employeeContractModelOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe contrato vinculado com o funcionário!");
        }

        EmployeeContractModel employeeContractModel = employeeContractModelOptional.get();

        // Atualizar e salvar o status do contrato
        employeeContractModel.setStatusContract(status);
        contractRepository.save(employeeContractModel);

        return ResponseEntity.ok("Status modificado para: " + status);
    }


    // Atualizar o turno do contrato do funcionário
    @Operation(summary = "Atualizar o turno do contrato do funcionário")
    @PutMapping("/update/contract/shift")
    public ResponseEntity updateShiftOfTheContract(
            @RequestParam(value = "cpf") String cpf,
            @RequestParam(value = "rg") String rg,
            @RequestParam(value = "shift") ShiftContract shift) {

        // Buscar funcionário pelo CPF e RG
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findByCpfAndRg(cpf, rg);
        if (employeeModelOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe nenhum funcionário com esse CPF e RG!");
        }

        EmployeeModel employeeModel = employeeModelOptional.get();

        // Buscar contrato vinculado ao funcionário
        Optional<EmployeeContractModel> employeeContractModelOptional = contractRepository.findByEmployee(employeeModel);
        if (employeeContractModelOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe contrato vinculado com o funcionário!");
        }

        EmployeeContractModel employeeContractModel = employeeContractModelOptional.get();

        // Atualizar e salvar o status do contrato
        employeeContractModel.setShift(shift);
        contractRepository.save(employeeContractModel);

        return ResponseEntity.ok("Turno modificado para: " + shift);
    }



    // Atualizar o tipo do contrato do funcionário
    @Operation(summary = "Atualizar o tipo do contrato do funcionário")
    @PutMapping("/update/contract/type")
    public ResponseEntity updateTypeOfTheContract(
            @RequestParam(value = "cpf") String cpf,
            @RequestParam(value = "rg") String rg,
            @RequestParam(value = "typeContract") TypeContract typeContract) {

        // Buscar funcionário pelo CPF e RG
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findByCpfAndRg(cpf, rg);
        if (employeeModelOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe nenhum funcionário com esse CPF e RG!");
        }

        EmployeeModel employeeModel = employeeModelOptional.get();

        // Buscar contrato vinculado ao funcionário
        Optional<EmployeeContractModel> employeeContractModelOptional = contractRepository.findByEmployee(employeeModel);
        if (employeeContractModelOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe contrato vinculado com o funcionário!");
        }

        EmployeeContractModel employeeContractModel = employeeContractModelOptional.get();

        // Atualizar e salvar o status do contrato
        employeeContractModel.setTypeContract(typeContract);
        contractRepository.save(employeeContractModel);

        return ResponseEntity.ok("Tipo modificado para: " + typeContract);
    }



    // Adicionar licença ao funcionário
    @PutMapping("/add/leave")
    public ResponseEntity<String> addEmployeeLeave(
            @RequestParam(value = "rg") String rg,
            @RequestParam(value = "cpf") String cpf,
            @RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "reason") String reason) {

        // Buscar funcionário pelo CPF e RG
        Optional<EmployeeModel> employeeModelOptional = employeeRepository.findByCpfAndRg(cpf, rg);

        // Verificar se o funcionário existe
        if (employeeModelOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe nenhum funcionário com esse CPF e RG!");
        }

        // Adicionar licença ao funcionário
        try {
            LeaveModel leaveModel = adminService.setLeave(employeeModelOptional.get(), start, end, type, reason);
            return ResponseEntity.ok("Licença adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar licença: " + e.getMessage());
        }
    }





}
