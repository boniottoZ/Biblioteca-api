package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotBlank(message = "A editora é obrigatória")
    private String editora;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1000, message = "Ano de publicação inválido")
    private Integer anoPublicacao;

    @NotBlank(message = "O ISBN é obrigatório")
    @Size(min = 10, max = 13, message = "O ISBN deve ter entre 10 e 13 caracteres")
    private String isbn;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantidade;

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    @NotNull(message = "A disponibilidade é obrigatória")
    private Boolean disponibilidade;
}