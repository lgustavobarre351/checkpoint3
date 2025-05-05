package br.com.fiap.checkpoint3.controller;

import br.com.fiap.checkpoint3.dto.*;
import br.com.fiap.checkpoint3.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public ResponseEntity<PacienteResponse> create(@RequestBody PacienteRequestCreate dto) {
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> update(@PathVariable Long id, @RequestBody PacienteRequestUpdate dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PacienteResponse> findAll() {
        return service.findAll();
    }
}
