package com.biblioteca.api.service;

import com.biblioteca.api.dto.ManutencaoRequestDTO;
import com.biblioteca.api.model.Livro;
import com.biblioteca.api.model.Manutencao;
import com.biblioteca.api.repository.LivroRepository;
import com.biblioteca.api.repository.ManutencaoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ManutencaoService {

    private static final String STATUS_EM_ANDAMENTO = "EM_ANDAMENTO";
    private static final String STATUS_CONCLUIDO = "CONCLUIDO";

    private final ManutencaoRepository manutencaoRepository;
    private final LivroRepository livroRepository;

    public ManutencaoService(ManutencaoRepository manutencaoRepository, LivroRepository livroRepository) {
        this.manutencaoRepository = manutencaoRepository;
        this.livroRepository = livroRepository;
    }

    @Transactional
    public Manutencao registrar(ManutencaoRequestDTO request) {
        Livro livro = livroRepository.findById(request.getLivroId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado."));

        Manutencao manutencao = new Manutencao();
        manutencao.setLivro(livro);
        manutencao.setDescricao(request.getDescricao());
        manutencao.setDataInicio(request.getDataInicio() != null ? request.getDataInicio() : LocalDateTime.now());
        manutencao.setDataTermino(request.getDataTermino());
        manutencao.setStatus(STATUS_EM_ANDAMENTO);

        return manutencaoRepository.save(manutencao);
    }

    @Transactional
    public Manutencao concluir(Long id) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manutencao nao encontrada."));

        manutencao.setStatus(STATUS_CONCLUIDO);
        manutencao.setDataTermino(LocalDateTime.now());

        return manutencaoRepository.save(manutencao);
    }

    public List<Manutencao> listarEmAndamento() {
        return manutencaoRepository.findByStatus(STATUS_EM_ANDAMENTO);
    }

    public List<Manutencao> buscarHistoricoDoLivro(Long livroId) {
        return manutencaoRepository.findByLivroId(livroId);
    }
}
