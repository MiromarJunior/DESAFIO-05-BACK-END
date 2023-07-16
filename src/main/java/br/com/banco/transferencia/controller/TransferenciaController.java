package br.com.banco.transferencia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.transferencia.model.Transferencia;
import br.com.banco.transferencia.service.imp.TransferenciaServiceImp;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    TransferenciaServiceImp service;



    @GetMapping("/conta/{idConta}")
    public ResponseEntity<Page<Transferencia>> getAllTransferenciaByConta(@PathVariable(name = "idConta") Long id,Pageable pageable) {
        Page<Transferencia> transferencias = service.getAllTransferenciaByConta(id,pageable);
        return ResponseEntity.ok().body(transferencias);
    }

    @GetMapping("/conta")
    public ResponseEntity<Page<Transferencia>> getAllTransferenciaByContaDtTransferencia(
            @RequestParam(name = "idConta") Long contaId,
            @RequestParam(name = "dataInicio") String dataInicioString,
            @RequestParam(name = "dataFim") String dataFimString,
            @RequestParam(name = "nomeOperadorTransacao") String nomeOperadorTransacao,
           Pageable pageable
            ) {

        Page<Transferencia> transferencias = service.getAllBydataTransferenciaByConta(dataInicioString, dataFimString, contaId,nomeOperadorTransacao,pageable);
        return ResponseEntity.ok().body(transferencias);
    }
    @GetMapping("/conta/saldo/{idConta}")
    public ResponseEntity<Double> getSaldoTotalConta(@PathVariable(name = "idConta") Long id) {
        Double  saldoTotal = service.saldoTotalConta(id);
        return ResponseEntity.ok().body(saldoTotal);
    }

}
