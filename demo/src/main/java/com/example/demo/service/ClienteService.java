package com.example.demo.service;

import com.example.demo.Entities.Cliente;
import com.example.demo.dto.ClienteRequestDTO;
import com.example.demo.dto.ClienteResponseDTO;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.repository.IClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(IClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    // Criar cliente
    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um cliente com esse email.");
        }
        if (clienteRepository.existsByDocumento(dto.getDocumento())) {
            throw new RuntimeException("Já existe um cliente com esse documento.");
        }

        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(salvo);
    }

    // Buscar cliente por ID
    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
        return clienteMapper.toResponseDTO(cliente);
    }

    // Listar todos os clientes
    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Atualizar cliente
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));

        clienteMapper.updateEntityFromDTO(dto, cliente);
        Cliente atualizado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(atualizado);
    }

    // Remover cliente
    public void remover(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}