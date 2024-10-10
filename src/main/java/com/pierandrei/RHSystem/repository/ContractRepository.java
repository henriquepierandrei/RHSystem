package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<EmployeeContractModel, Long> {


    Page<EmployeeContractModel> findByTypeContract(TypeContract typeContract, Pageable pageable);

    Optional<EmployeeContractModel> findByEmployee(EmployeeModel employeeModel);

    Page<EmployeeContractModel> findByWageLessThanEqual(Double value, Pageable pageable);

    Page<EmployeeContractModel> findByStatusContract(StatusContract statusContract, Pageable pageable);

    Page<EmployeeContractModel> findByShift(ShiftContract shiftContract, Pageable pageable);

    Page<EmployeeContractModel> findByPosition(String position, Pageable pageable);
}
