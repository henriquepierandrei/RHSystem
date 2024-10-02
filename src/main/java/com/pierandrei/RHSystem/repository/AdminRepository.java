package com.pierandrei.RHSystem.repository;

import com.pierandrei.RHSystem.model.AdminModels.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminModel, Long> {
}
