package com.example.demo.service;

import com.example.demo.Entities.Emprestimo;
import com.example.demo.Entities.Multa;
import com.example.demo.dto.MultaRequestDTO;
import com.example.demo.dto.MultaResponseDTO;
import com.example.demo.mapper.MultaMapper;
import com.example.demo.repository.IEmprestimoRepository;
import com.example.demo.repository.IMultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MultaService {

    @Autowired
    private IMultaRepository multaRepository;

    @Autowired
    private IEmprestimoRepository emprestimoRepository;

    @Autowired
    private MultaMapper multaMapper;

    public MultaResponseDTO registrarPagamento(MultaRequestDTO request) {
        Emprestimo emprestimo = emprestimoRepository.findById(request.getEmprestimoId())
                .orElseThrow(() -> new RuntimeException("Empréstimo com ID " + request.getEmprestimoId() + " não encontrado"));

        Multa multa = multaMapper.toEntity(request);
        multa.setEmprestimo(emprestimo);
        multa.setStatus("Pendente");
        multa.setDataPagamento(null);

        Multa multaSalva = multaRepository.save(multa);

        return multaMapper.toDTO(multaSalva);
    }

    public List<MultaResponseDTO> listarPagamentosPorCliente(Long clienteId) {
        List<Emprestimo> emprestimosDoCliente = emprestimoRepository.findByClienteId(clienteId);
        
        List<Multa> todasAsMultas = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimosDoCliente) {
            List<Multa> multasDesteEmprestimo = multaRepository.findByEmprestimoId(emprestimo.getId());
            todasAsMultas.addAll(multasDesteEmprestimo);
        }

        return todasAsMultas.stream()
                .map(multaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MultaResponseDTO atualizarStatus(Long id, String novoStatus) {
        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento com ID " + id + " não encontrado"));
        
        multa.setStatus(novoStatus);
        
        Multa multaAtualizada = multaRepository.save(multa);
        
        return multaMapper.toDTO(multaAtualizada);
    }
}