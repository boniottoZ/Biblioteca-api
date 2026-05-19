package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmprestimoRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O ID do livro é obrigatório")
    private Long livroId;

    @NotNull(message = "A data do empréstimo é obrigatória")
    private LocalDateTime dataEmprestimo;

    @NotNull(message = "A data de devolução prevista é obrigatória")
    private LocalDateTime dataDevolucaoPrevista;

    private LocalDateTime dataDevolucaoReal;

    @NotBlank(message = "O status é obrigatório")
    private String status;
}