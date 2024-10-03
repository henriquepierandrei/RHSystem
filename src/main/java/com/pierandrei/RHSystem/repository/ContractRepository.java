package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.enuns.Employees.EmploymentContract.TypeContract;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<EmployeeContractModel, Long> {
    Optional<EmployeeContractModel> findByCpfAndRg(String cpf, String rg);

    List<EmployeeModel> findByTypeContract(TypeContract typeContract);
}
