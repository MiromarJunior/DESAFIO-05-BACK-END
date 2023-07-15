package br.com.banco.conta.service;

import java.util.List;

import br.com.banco.conta.model.Conta;

public interface ContaService {

    List<Conta> getAllConta();

    Boolean getContaById(Long id);
    
    
}
