package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.demo.Entities.Livro;
import com.example.demo.dto.LivroRequestDTO;
import com.example.demo.dto.LivroResponseDTO;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro toEntity(LivroRequestDTO dto);

    LivroResponseDTO toResponseDTO(Livro livro);

    void updateEntityFromDTO(LivroRequestDTO dto, @MappingTarget Livro livro);
}