package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MultaRequestDTO {

    @NotNull(message = "O ID do empréstimo é obrigatório")
    private Long emprestimoId;

    @NotNull(message = "O valor da multa é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor da multa deve ser maior que zero")
    private BigDecimal valor;

    private LocalDateTime dataPagamento;

    @NotBlank(message = "O status da multa é obrigatório")
    private String status;
}