package com.pierandrei.RHSystem.infra.security;

import com.pierandrei.RHSystem.model.EmployeeModels.EmployeeModel;
import com.pierandrei.RHSystem.repository.EmployeeRepository;
import com.pierandrei.RHSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        // Verificando se existe um funcionário registrado com o CPF fornecido!
        EmployeeModel employeeModel = this.employeeRepository.findByCpf(cpf).orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado!"));

        // ROLE definida como EMPLOYEE de padrão
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));

        // Se o funcionário tiver a ROLE de ADMIN, terá acesso a funcionalidades de administrador através da ROLE_ADMIN
        if (employeeModel.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(employeeModel.getCpf(), employeeModel.getRg(), authorities);
    }
}