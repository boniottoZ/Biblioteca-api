package com.example.demo.dto;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {
 
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
 
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
 
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
 
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;
 
    @NotBlank(message = "Documento é obrigatório")
    private String documento;

    // Getters adicionados manualmente devido a problema com Lombok
    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }
}
 