package com.example.demo.mapper;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaRequestDTO;
import com.example.demo.dto.MultaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MultaMapper {

    public Multa toEntity(MultaRequestDTO request) {
        if (request == null) {
            return null;
        }

        Multa multa = new Multa();
        multa.setValor(request.getValor());
        
        if (request.getEmprestimoId() != null) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setId(request.getEmprestimoId());
            multa.setEmprestimo(emprestimo);
        }
        return multa;
    }

    public MultaResponseDTO toDTO(Multa multa) {
        if (multa == null) {
            return null;
        }

        MultaResponseDTO dto = new MultaResponseDTO();
        dto.setId(multa.getId());
        dto.setValor(multa.getValor());
        dto.setDataPagamento(multa.getDataPagamento());
        dto.setStatus(multa.getStatus());
   
        if (multa.getEmprestimo() != null) {
            dto.setEmprestimoId(multa.getEmprestimo().getId());
        }
        
        return dto;
    }
}