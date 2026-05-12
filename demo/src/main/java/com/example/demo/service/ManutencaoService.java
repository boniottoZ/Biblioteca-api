package com.example.demo.service;

import com.example.demo.Entities.Livro;
import com.example.demo.Entities.Manutencao;
import com.example.demo.dto.ManutencaoRequestDto;
import com.example.demo.dto.ManutencaoResponseDto;
import com.example.demo.repository.ILivroRepository;
import com.example.demo.repository.IManutencaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManutencaoService {

    private static final String STATUS_EM_ANDAMENTO = "EM_ANDAMENTO";
    private static final String STATUS_CONCLUIDA = "CONCLUIDA";

    private final IManutencaoRepository manutencaoRepository;
    private final ILivroRepository livroRepository;

    public ManutencaoService(
            IManutencaoRepository manutencaoRepository,
            ILivroRepository livroRepository
    ) {
        this.manutencaoRepository = manutencaoRepository;
        this.livroRepository = livroRepository;
    }

    @Transactional
    public ManutencaoResponseDto registrarManutencao(ManutencaoRequestDto request) {
        Livro livro = livroRepository.findById(request.getLivroId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado."));

        Manutencao manutencao = new Manutencao();
        manutencao.setLivro(livro);
        manutencao.setDescricao(request.getDescricao());
        manutencao.setDataInicio(request.getDataInicio());
        manutencao.setDataTermino(request.getDataTermino());
        manutencao.setStatus(obterStatusInicial(request.getStatus()));

        livro.setDisponibilidade(false);
        livroRepository.save(livro);

        return toResponseDto(manutencaoRepository.save(manutencao));
    }

    public List<ManutencaoResponseDto> listarManutencoesEmAndamento() {
        return manutencaoRepository.findByStatus(STATUS_EM_ANDAMENTO)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Transactional
    public ManutencaoResponseDto concluirManutencao(Long id) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manutencao nao encontrada."));

        manutencao.setStatus(STATUS_CONCLUIDA);
        manutencao.setDataTermino(LocalDateTime.now());

        Livro livro = manutencao.getLivro();
        livro.setDisponibilidade(true);
        livroRepository.save(livro);

        return toResponseDto(manutencaoRepository.save(manutencao));
    }

    public List<ManutencaoResponseDto> consultarHistoricoPorLivro(Long livroId) {
        return manutencaoRepository.findByLivroId(livroId)
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    private ManutencaoResponseDto toResponseDto(Manutencao manutencao) {
        Livro livro = manutencao.getLivro();

        return new ManutencaoResponseDto(
                manutencao.getId(),
                livro.getId(),
                livro.getTitulo(),
                manutencao.getDescricao(),
                manutencao.getDataInicio(),
                manutencao.getDataTermino(),
                manutencao.getStatus()
        );
    }

    private String obterStatusInicial(String status) {
        if (status == null || status.isBlank()) {
            return STATUS_EM_ANDAMENTO;
        }

        return status;
    }
}
