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

    @NotNull(message = "O id do livro e obrigatorio.")
    private Long livroId;

    @NotBlank(message = "A descricao da manutencao nao pode estar em branco.")
    private String descricao;

    @NotNull(message = "A data de inicio e obrigatoria.")
    private LocalDateTime dataInicio;

    private LocalDateTime dataTermino;

    private String status;
}
