package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Livro;
import com.example.demo.dto.LivroRequestDTO;
import com.example.demo.dto.LivroResponseDTO;
import com.example.demo.mapper.LivroMapper;
import com.example.demo.repository.ILivroRepository;

@Service
public class LivroService {

    private final ILivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(ILivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    // Adicionar livro
    public LivroResponseDTO adicionar(LivroRequestDTO dto) {
        if (livroRepository.findByIsbn(dto.getIsbn()).isPresent()) {
            throw new RuntimeException("Já existe um livro com esse ISBN.");
        }

        Livro livro = livroMapper.toEntity(dto);
        Livro salvo = livroRepository.save(livro);
        return livroMapper.toResponseDTO(salvo);
    }

    // Buscar livro por ID
    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));
        return livroMapper.toResponseDTO(livro);
    }

    // Listar todos os livros
    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(livroMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Listar livros disponíveis (quantidade > 0 e disponibilidade = true)
    public List<LivroResponseDTO> listarDisponiveis() {
        return livroRepository.findByQuantidadeGreaterThan(0)
                .stream()
                .filter(Livro::getDisponibilidade)
                .map(livroMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Atualizar livro
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));

        livroMapper.updateEntityFromDTO(dto, livro);
        Livro atualizado = livroRepository.save(livro);
        return livroMapper.toResponseDTO(atualizado);
    }

    // Remover livro
    public void remover(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com id: " + id);
        }
        livroRepository.deleteById(id);
    }
}