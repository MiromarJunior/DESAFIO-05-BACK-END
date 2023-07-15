package br.com.banco.transferencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.transferencia.model.Transferencia;
import br.com.banco.transferencia.service.imp.TransferenciaServiceImp;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    TransferenciaServiceImp service;

    @GetMapping
    public ResponseEntity<List<Transferencia>> getAllTransferencia(){
        List<Transferencia> transferencias = service.getAllTransferencia();
        return ResponseEntity.ok().body(transferencias);
    }

    @GetMapping("/{idConta}")
    public ResponseEntity<List<Transferencia>> getAllTransferenciaByConta(@PathVariable(name = "idConta") Long id){
        List<Transferencia> transferencias = service.getAllTransferenciaByConta(id);
        return ResponseEntity.ok().body(transferencias);
    }
    
}