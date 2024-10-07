package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<EmployeeModel> findByCpfAndRg(String cpf, String rg);

    Optional<EmployeeModel> findByCpf(String cpf);
}
