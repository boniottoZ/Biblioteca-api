package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManutencaoRequestDto {

    @NotBlank(message = "A descrição da manutenção não pode estar em branco.")
    private String descricao;

    @NotNull(message = "A data de início é obrigatória.")
    private LocalDateTime dataInicio;

    private LocalDateTime dataTermino;

    @NotBlank(message = "O status inicial é obrigatório.")
    private String status;
}