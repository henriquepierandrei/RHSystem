package com.pierandrei.RHSystem.service;

import org.springframework.stereotype.Service;

@Service
public class CpfValidator {

    public boolean isValid(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        // Validação dos dígitos verificadores
        return validateDigits(cpf);
    }

    private boolean validateDigits(String cpf) {
        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (Character.getNumericValue(cpf.charAt(i)) * (10 - i));
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        // Verifica o primeiro dígito verificador
        if (firstDigit != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (Character.getNumericValue(cpf.charAt(i)) * (11 - i));
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        // Verifica o segundo dígito verificador
        return secondDigit == Character.getNumericValue(cpf.charAt(10));
    }
}
