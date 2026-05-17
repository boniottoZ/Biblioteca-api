package com.biblioteca.api.repository;

import com.biblioteca.api.model.Manutencao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    List<Manutencao> findByStatus(String status);

    List<Manutencao> findByLivroId(Long livroId);
}
