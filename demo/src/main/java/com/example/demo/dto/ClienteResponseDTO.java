package com.example.demo.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {
 
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String documento;
}
 