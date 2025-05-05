package br.com.fiap.checkpoint3.dto;

public record PacienteRequestCreate(
    String nome, 
    String cpf, 
    String email
    ) 
{}
