package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManutencaoResponseDto {

    private Long id;
    private Long livroId;
    private String livroTitulo; // Facilitar a exibição sem precisar de outra chamada
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String status;
}