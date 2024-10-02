package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
}
