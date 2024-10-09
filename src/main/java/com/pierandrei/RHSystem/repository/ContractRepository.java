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


//    List<EmployeeContractModel> findByWageLessThanEqual(Double value);
//
//    List<EmployeeContractModel> findByStatusContract(StatusContract statusContract);
//
//    List<EmployeeContractModel> findByShift(ShiftContract shiftContract);




    Optional<EmployeeContractModel> findByEmployee(EmployeeModel employeeModel);
}
