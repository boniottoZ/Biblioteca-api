package com.example.demo.controller;

import com.example.demo.dto.MultaRequestDTO;
import com.example.demo.dto.MultaResponseDTO;
import com.example.demo.service.MultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pagamentos")
public class MultaController {

    @Autowired
    private MultaService multaService;

    @PostMapping // registra pagamentos(novos)
    public ResponseEntity<MultaResponseDTO> registrarPagamento(@Valid @RequestBody MultaRequestDTO request) {
        MultaResponseDTO response = multaService.registrarPagamento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/cliente/{clienteId}")//lista pagamento
    public ResponseEntity<List<MultaResponseDTO>> listarPagamentosPorCliente(@PathVariable Long clienteId) {
        List<MultaResponseDTO> pagamentos = multaService.listarPagamentosPorCliente(clienteId);
        return ResponseEntity.ok(pagamentos);
    }

    @PatchMapping("/{id}/status")//atualiza status
    public ResponseEntity<MultaResponseDTO> atualizarStatus(
            @PathVariable Long id, 
            @RequestBody Map<String, String> body) {
        
        String novoStatus = body.get("status");
        MultaResponseDTO response = multaService.atualizarStatus(id, novoStatus);
        
        return ResponseEntity.ok(response);
    }
}