package com.example.demo.controller;

import com.example.demo.dto.ManutencaoRequestDto;
import com.example.demo.dto.ManutencaoResponseDto;
import com.example.demo.service.ManutencaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manutencoes")
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @PostMapping
    public ResponseEntity<ManutencaoResponseDto> registrarManutencao(
            @Valid @RequestBody ManutencaoRequestDto request
    ) {
        ManutencaoResponseDto response = manutencaoService.registrarManutencao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/andamento")
    public ResponseEntity<List<ManutencaoResponseDto>> listarManutencoesEmAndamento() {
        return ResponseEntity.ok(manutencaoService.listarManutencoesEmAndamento());
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<ManutencaoResponseDto> concluirManutencao(@PathVariable Long id) {
        return ResponseEntity.ok(manutencaoService.concluirManutencao(id));
    }

    @GetMapping("/livro/{livroId}")
    public ResponseEntity<List<ManutencaoResponseDto>> consultarHistoricoPorLivro(@PathVariable Long livroId) {
        return ResponseEntity.ok(manutencaoService.consultarHistoricoPorLivro(livroId));
    }
}
