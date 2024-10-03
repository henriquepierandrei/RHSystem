package com.pierandrei.RHSystem.dto.Inputs;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import jakarta.validation.constraints.NotEmpty;

public record UpdateContractDto(String cpf,
                                String rg,
                                TypeContract typeContract,
                                String position,
                                Double wage,
                                ShiftContract shiftContract,
                                StatusContract statusContract,
                                Double bonus,
                                Double absentValue
                                ) {
}
