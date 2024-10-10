package com.pierandrei.RHSystem.model.EmployeeModels;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Entity
public class LeaveModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // Identificador único

    @ManyToOne
    private EmployeeModel employee;        // Funcionário

    private LocalDate localStart;
    private LocalDate localEnd;

    private String type; // Tipo de licença (médica, maternidade, etc.)
    private String reason; // Motivo da licença (opcional)

    private boolean isActive;     // Verificar se está de licença
}
