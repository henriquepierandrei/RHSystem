package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeContractModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<EmployeeContractModel, Long> {
    Optional<EmployeeContractModel> findByCpfAndRg(String cpf, String rg);
}
