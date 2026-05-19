package com.example.demo.service;

import com.example.demo.dto.ManutencaoRequestDto;
import com.example.demo.dto.ManutencaoResponseDto;
import com.example.demo.Entities.Livro;
import com.example.demo.Entities.Manutencao;
import com.example.demo.mapper.ManutencaoMapper;
import com.example.demo.repository.ILivroRepository;
import com.example.demo.repository.IManutencaoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ManutencaoService {

    private static final String STATUS_EM_ANDAMENTO = "EM_ANDAMENTO";
    private static final String STATUS_CONCLUIDO = "CONCLUIDO";

    private final IManutencaoRepository manutencaoRepository;
    private final ILivroRepository livroRepository;
    private final ManutencaoMapper manutencaoMapper;

    public ManutencaoService(
            IManutencaoRepository manutencaoRepository,
            ILivroRepository livroRepository,
            ManutencaoMapper manutencaoMapper) {
        this.manutencaoRepository = manutencaoRepository;
        this.livroRepository = livroRepository;
        this.manutencaoMapper = manutencaoMapper;
    }

    @Transactional
    public ManutencaoResponseDto registrar(ManutencaoRequestDto request) {
        Livro livro = livroRepository.findById(request.getLivroId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado."));

        Manutencao manutencao = manutencaoMapper.toEntity(request);
        manutencao.setLivro(livro);
        if (manutencao.getDataInicio() == null) {
            manutencao.setDataInicio(LocalDateTime.now());
        }
        manutencao.setStatus(STATUS_EM_ANDAMENTO);

        Manutencao salvo = manutencaoRepository.save(manutencao);
        return manutencaoMapper.toResponseDTO(salvo);
    }

    @Transactional
    public ManutencaoResponseDto concluir(Long id) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manutencao nao encontrada."));

        manutencao.setStatus(STATUS_CONCLUIDO);
        manutencao.setDataTermino(LocalDateTime.now());

        Manutencao salvo = manutencaoRepository.save(manutencao);
        return manutencaoMapper.toResponseDTO(salvo);
    }

    public List<ManutencaoResponseDto> listarEmAndamento() {
        return manutencaoRepository.findByStatus(STATUS_EM_ANDAMENTO).stream()
                .map(manutencaoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ManutencaoResponseDto> buscarHistoricoDoLivro(Long livroId) {
        return manutencaoRepository.findByLivroId(livroId).stream()
                .map(manutencaoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
