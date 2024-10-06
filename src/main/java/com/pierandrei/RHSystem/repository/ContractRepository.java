package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.ShiftContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.StatusContract;
import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<EmployeeContractModel, Long> {
    Optional<EmployeeContractModel> findByCpfAndRg(String cpf, String rg);

    List<EmployeeModel> findByTypeContract(TypeContract typeContract);


    List<EmployeeModel> findByWageLessThanEqual(Double value);

    List<EmployeeModel> findByStatusContract(StatusContract statusContract);

    List<EmployeeModel> findByShift(ShiftContract shiftContract);
}
