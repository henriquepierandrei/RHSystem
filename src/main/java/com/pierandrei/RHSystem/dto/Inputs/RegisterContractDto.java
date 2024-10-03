package com.pierandrei.RHSystem.dto.Inputs;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record RegisterContractDto(@NotEmpty LocalDate startDate,
                                  @NotEmpty String cpf,
                                  @NotEmpty String rg,
                                  @NotEmpty TypeContract typeContract,
                                  @NotEmpty String position,
                                  @NotEmpty double wage,
                                  @NotEmpty ShiftContract shiftContract,
                                  @NotEmpty StatusContract statusContract,
                                  @NotEmpty double bonus,
                                  @NotEmpty double absentValue) {
}
