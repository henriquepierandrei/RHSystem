package com.pierandrei.RHSystem.model.EmployeeModels;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // ID único do funcionário

    // Dados Pessoais
    private String name; // Nome do funcionário

    @Column(unique = true, nullable = false)
    private String email; // E-mail do funcionário (único e não nulo)

    private LocalDate dateBorn; // Data de nascimento do funcionário

    @Column(unique = true, nullable = false)
    private String cpf; // CPF do funcionário (único e não nulo)

    private String phone; // Telefone do funcionário
    private String rg; // RG do funcionário

    // Dados de Documentos
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private EmployeeContractModel employeeContractModel; // Referência ao modelo de contrato do funcionário



    private boolean isAdmin = false;       // ROLE de ADMIN
}
