package br.com.fiap.checkpoint3.service;

import br.com.fiap.checkpoint3.dto.*;
import br.com.fiap.checkpoint3.model.Paciente;
import br.com.fiap.checkpoint3.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public PacienteResponse create(PacienteRequestCreate dto) {
        if (dto.nome() == null || dto.cpf() == null || dto.email() == null) {
            throw new IllegalArgumentException("Nome, CPF, and Email cannot be null");
        }

        Paciente paciente = new Paciente(dto.nome(), dto.cpf(), dto.email());
        Paciente savedPaciente = repository.save(paciente);
        logger.info("Paciente created with ID: {}", savedPaciente.getId());
        return toResponse(savedPaciente);
    }

    public Optional<PacienteResponse> update(Long id, PacienteRequestUpdate dto) {
        if (dto.nome() == null || dto.email() == null) {
            throw new IllegalArgumentException("Nome and Email cannot be null");
        }

        return repository.findById(id).map(paciente -> {
            paciente.setNome(dto.nome());
            paciente.setEmail(dto.email());
            Paciente updatedPaciente = repository.save(paciente);
            logger.info("Paciente updated with ID: {}", updatedPaciente.getId());
            return toResponse(updatedPaciente);
        });
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            logger.warn("Paciente with ID: {} does not exist", id);
            return false;
        }
        repository.deleteById(id);
        logger.info("Paciente deleted with ID: {}", id);
        return true;
    }

    public Optional<PacienteResponse> findById(Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isEmpty()) {
            logger.warn("Paciente with ID: {} not found", id);
        }
        return paciente.map(this::toResponse);
    }

    public List<PacienteResponse> findAll() {
        List<Paciente> pacientes = repository.findAll();
        logger.info("Retrieved {} pacientes", pacientes.size());
        return pacientes.stream().map(this::toResponse).toList();
    }

    private PacienteResponse toResponse(Paciente paciente) {
        return new PacienteResponse(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail());
    }
}