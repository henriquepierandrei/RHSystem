package com.pierandrei.RHSystem.infra.security;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.infra.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    private final AuthenticationService authenticationService;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        EmployeeModel employeeModel = authenticationService.loadEmployeeByCpf(cpf);
        var authorities = authenticationService.getAuthorities(employeeModel);

        return new org.springframework.security.core.userdetails.User(employeeModel.getCpf(), employeeModel.getRg(), authorities);
    }
}
