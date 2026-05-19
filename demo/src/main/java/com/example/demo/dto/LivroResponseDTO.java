package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private Integer anoPublicacao;
    private String isbn;
    private Integer quantidade;
    private String categoria;
    private Boolean disponibilidade;
}