package br.com.banco.conta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.conta.model.Conta;
import br.com.banco.conta.service.imp.ContaServiceImp;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaServiceImp service;

    @GetMapping
    public ResponseEntity<List<Conta>> getAllContas(){
        List<Conta> contas = service.getAllConta();
        return ResponseEntity.ok().body(contas);
        
    }
    
}
