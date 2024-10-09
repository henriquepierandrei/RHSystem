package com.pierandrei.RHSystem.controller;

import com.pierandrei.RHSystem.dto.Inputs.LoginDto;
import com.pierandrei.RHSystem.dto.Inputs.RegisterDto;
import com.pierandrei.RHSystem.dto.Responses.LoginResponseDto;
import com.pierandrei.RHSystem.dto.Responses.ResponseRegisterDto;
import com.pierandrei.RHSystem.infra.security.TokenService;
import com.pierandrei.RHSystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final EmployeeService employeeService;
    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Funcionário faz o login.")
    public ResponseEntity<?> login(@RequestBody LoginDto body) {
        try {
            // Utiliza o método de login do service
            LoginResponseDto response = employeeService.login(body);

            // Retorna sucesso com o token e informações do usuário
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Retorna erro com a mensagem apropriada (CPF ou RG incorreto)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Operation(summary = "Funcionário faz o registro.")
    @PostMapping("/register")
    public ResponseEntity<?> login(@RequestBody RegisterDto body) {
        try {
            // Utiliza o método de login do service
            ResponseRegisterDto response = employeeService.register(body);

            // Retorna sucesso com o token e informações do usuário
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // Retorna erro com a mensagem apropriada (CPF ou RG incorreto)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
