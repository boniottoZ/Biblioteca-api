package com.example.demo.controller;

import com.example.demo.dto.ManutencaoRequestDto;
import com.example.demo.dto.ManutencaoResponseDto;
import com.example.demo.service.ManutencaoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manutencoes")
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @PostMapping
    public ResponseEntity<ManutencaoResponseDto> registrar(@RequestBody @Valid ManutencaoRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manutencaoService.registrar(request));
    }

    @GetMapping("/andamento")
    public List<ManutencaoResponseDto> listarEmAndamento() {
        return manutencaoService.listarEmAndamento();
    }

    @PatchMapping("/{id}/concluir")
    public ManutencaoResponseDto concluir(@PathVariable Long id) {
        return manutencaoService.concluir(id);
    }

    @GetMapping("/livro/{livroId}")
    public List<ManutencaoResponseDto> buscarHistoricoDoLivro(@PathVariable Long livroId) {
        return manutencaoService.buscarHistoricoDoLivro(livroId);
    }
}
