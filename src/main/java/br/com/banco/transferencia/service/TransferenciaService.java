package br.com.banco.transferencia.service;

import java.util.List;

import br.com.banco.conta.model.Conta;
import br.com.banco.transferencia.model.Transferencia;

public interface TransferenciaService {

     List<Transferencia> getAllTransferencia();

     List<Transferencia> getAllTransferenciaByConta(Long id);

      List<Transferencia> getAllBydataTransferenciaByConta(String dataInicioString,String dataFimString, Long id);
      
     Conta getContaById(Long id);


    
}
