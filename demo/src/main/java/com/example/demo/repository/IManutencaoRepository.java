package com.example.demo.repository;

import com.example.demo.Entities.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IManutencaoRepository extends JpaRepository<Manutencao, Long> {

    List<Manutencao> findByStatus(String status);

    List<Manutencao> findByLivroId(Long livroId);
}