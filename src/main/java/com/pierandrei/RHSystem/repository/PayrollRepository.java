package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.PayrollModels.PayrollModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<PayrollModel, Long> {
}
