package br.com.fiap.checkpoint3.repository;

import br.com.fiap.checkpoint3.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
