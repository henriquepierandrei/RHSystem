package com.pierandrei.RHSystem.dto.Inputs;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record RegisterEmployeeDto(@NotEmpty String name,
                                  @NotEmpty String email,
                                  @NotEmpty LocalDate dateBorn,
                                  @NotEmpty String cpf,
                                  @NotEmpty String phone,
                                  @NotEmpty String rg) {
}
