package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.EmployeeModels.LeaveModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<LeaveModel, Long> {


    Optional<LeaveModel> findByEmployeeAndIsActive(EmployeeModel employeeModel, boolean b);


    List<LeaveModel> findByIsActiveAndLocalEnd(boolean b, LocalDate now);
}
