package com.pierandrei.RHSystem.dto.Inputs;

import java.time.LocalDate;

public record RegisterDto(
        String name,          // Nome do funcionário
        String password,
        String email,         // E-mail do funcionário
        LocalDate dateBorn,   // Data de nascimento do funcionário
        String cpf,           // CPF do funcionário
        String phone,         // Telefone do funcionário
        String rg           // RG do funcionário
) {}

