package com.example.demo.Entities;
 
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false)
    private String nome;
 
    @Column(nullable = false, unique = true)
    private String email;
 
    @Column(nullable = false)
    private String telefone;
 
    @Column(nullable = false)
    private String endereco;
 
    @Column(nullable = false, unique = true)
    private String documento;

    // Getters adicionados manualmente devido a problema com Lombok
    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }
}