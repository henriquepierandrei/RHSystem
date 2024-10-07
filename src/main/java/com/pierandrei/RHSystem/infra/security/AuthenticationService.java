package com.pierandrei.RHSystem.infra.security;


import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmployeeRepository employeeRepository;

    public EmployeeModel loadEmployeeByCpf(String cpf) {
        return employeeRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado!"));
    }

    public List<SimpleGrantedAuthority> getAuthorities(EmployeeModel employeeModel) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));

        if (employeeModel.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }
}
