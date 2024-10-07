package com.pierandrei.RHSystem.service;

import com.pierandrei.RHSystem.dto.Inputs.RegisterEmployeeDto;
import com.pierandrei.RHSystem.dto.Inputs.UpdateEmailAndPhoneDto;
import com.pierandrei.RHSystem.dto.Responses.RegisterResponseDto;
import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.model.PayrollModels.InfoPayroll;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import com.pierandrei.RHSystem.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final InfoRepository infoRepository;
    private final CpfValidator cpfValidator;


    // Obtém o histórico de informações
    public List<InfoPayroll> getSupport(long id){
        return this.infoRepository.findByEmployeeId(id);
    }










}
