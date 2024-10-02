package com.pierandrei.RHSystem.dto.Responses;

import jakarta.validation.constraints.NotEmpty;


public record RegisterResponseDto(@NotEmpty String name,
                                  @NotEmpty String cpf,
                                  @NotEmpty String rg) {

}

