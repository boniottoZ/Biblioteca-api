package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Multa;

@Repository
public interface IMultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByStatus(String status);

    List<Multa> findByEmprestimoId(Long emprestimoId);
}