package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.EmployeeModels.LeaveModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<LeaveModel, Long> {
}
