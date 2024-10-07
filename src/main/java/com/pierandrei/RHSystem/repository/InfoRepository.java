package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoRepository extends JpaRepository<InfoPayroll, Long> {
    List<InfoPayroll> findByEmployeeId(long id);
}
