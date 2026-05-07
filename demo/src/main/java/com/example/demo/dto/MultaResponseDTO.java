package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultaResponseDTO {

    private Long id;
    private Long emprestimoId;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private String status;
}