package com.pierandrei.RHSystem.dto.Inputs;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterContractDto(@NotNull
                                  @FutureOrPresent(message = "A data de início deve ser no presente ou no futuro")  LocalDate startDate,
                                  @NotEmpty String cpf,
                                  @NotEmpty String rg,
                                  @NotNull TypeContract typeContract,
                                  @NotEmpty String position,
                                  @DecimalMin(value = "0.01", message = "O salário deve ser maior que zero") double wage,
                                  @NotNull ShiftContract shiftContract,
                                  @NotNull StatusContract statusContract,
                                  @DecimalMin(value = "0.01", message = "O salário deve ser maior que zero") double bonus) {
}
