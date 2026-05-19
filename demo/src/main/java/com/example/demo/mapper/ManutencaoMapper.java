package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.demo.Entities.Manutencao;
import com.example.demo.dto.ManutencaoRequestDto;
import com.example.demo.dto.ManutencaoResponseDto;

@Mapper(componentModel = "spring")
public interface ManutencaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "livro", ignore = true)
    Manutencao toEntity(ManutencaoRequestDto dto);

    @Mapping(target = "livroId", source = "livro.id")
    @Mapping(target = "livroTitulo", source = "livro.titulo")
    ManutencaoResponseDto toResponseDTO(Manutencao manutencao);

    @Mapping(target = "livro", ignore = true)
    void updateEntityFromDTO(ManutencaoRequestDto dto, @MappingTarget Manutencao manutencao);
}
