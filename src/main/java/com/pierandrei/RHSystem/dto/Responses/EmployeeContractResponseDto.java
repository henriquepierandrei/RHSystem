package com.pierandrei.RHSystem.dto.Responses;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;

import java.time.LocalDate;

public record EmployeeContractResponseDto(
        EmployeeModel employeeModel,
        LocalDate startDate,
        LocalDate endDate,
        TypeContract typeContract,
        String position,
        double wage,
        ShiftContract shift,
        StatusContract statusContract,
        double bonus,
        double absentValue
) {

}
