package br.com.banco.conta.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.conta.model.Conta;
import br.com.banco.conta.repository.ContaRepository;
import br.com.banco.conta.service.ContaService;


@Service
public class ContaServiceImp implements ContaService{

    @Autowired
    ContaRepository repository;

    @Override
    public List<Conta> getAllConta() {
        List<Conta> contas = repository.findAll();
        return contas;
    
    }

    @Override
    public Boolean getContaById(Long id) {
        return repository.findById(id).isPresent();
    }
       
    
}
