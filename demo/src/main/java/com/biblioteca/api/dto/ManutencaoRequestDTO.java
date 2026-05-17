package com.biblioteca.api.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ManutencaoRequestDTO {

    private Long livroId;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
}
