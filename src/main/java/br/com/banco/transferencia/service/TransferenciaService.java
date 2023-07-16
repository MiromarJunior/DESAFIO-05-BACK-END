package br.com.banco.transferencia.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.banco.conta.model.Conta;
import br.com.banco.transferencia.model.Transferencia;

public interface TransferenciaService {

     List<Transferencia> getAllTransferencia();

     List<Transferencia> getAllTransferenciaByConta(Long id);

      List<Transferencia> getAllBydataTransferenciaByConta(String dataInicioString,String dataFimString, Long id,String nomeOperadorTransacao);
      
      List<Transferencia> getAllBydataTransferenciaByContaByNomeOperador(LocalDateTime dataInicio,LocalDateTime dataFim, Long id,String nomeOperadorTransacao);
      
     Conta getContaById(Long id);

     List<Transferencia> getAllTransferenciaByNomeOperadorTransacao(String nomeOperadorTransacao, Long contaId);

     Double saldoTotalConta(Long contaId);


    
}
