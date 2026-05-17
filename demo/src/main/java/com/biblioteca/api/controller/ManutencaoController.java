package com.biblioteca.api.controller;

import com.biblioteca.api.dto.ManutencaoRequestDTO;
import com.biblioteca.api.model.Manutencao;
import com.biblioteca.api.service.ManutencaoService;
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
    public ResponseEntity<Manutencao> registrar(@RequestBody ManutencaoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manutencaoService.registrar(request));
    }

    @GetMapping("/andamento")
    public List<Manutencao> listarEmAndamento() {
        return manutencaoService.listarEmAndamento();
    }

    @PatchMapping("/{id}/concluir")
    public Manutencao concluir(@PathVariable Long id) {
        return manutencaoService.concluir(id);
    }

    @GetMapping("/livro/{livroId}")
    public List<Manutencao> buscarHistoricoDoLivro(@PathVariable Long livroId) {
        return manutencaoService.buscarHistoricoDoLivro(livroId);
    }
}
