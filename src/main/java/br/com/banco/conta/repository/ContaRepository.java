package br.com.banco.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.conta.model.Conta;

public interface ContaRepository extends JpaRepository<Conta,Long> {
    
}
