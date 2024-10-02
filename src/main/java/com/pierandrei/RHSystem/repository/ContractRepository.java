package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<EmployeeContractModel, Long> {
}
