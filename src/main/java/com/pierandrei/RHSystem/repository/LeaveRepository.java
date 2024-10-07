package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.EmployeeModels.LeaveModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveRepository extends JpaRepository<LeaveModel, Long> {
    Optional<LeaveModel> findByEmployeeId(long id);
}
