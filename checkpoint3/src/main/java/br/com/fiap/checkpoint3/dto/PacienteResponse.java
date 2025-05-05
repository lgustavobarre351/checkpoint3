package br.com.fiap.checkpoint3.dto;

public record PacienteResponse(
    Long id, 
    String nome,
    String cpf, 
    String email
    ) 
    {}
