package br.com.banco.transferencia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.banco.conta.model.Conta;
import br.com.banco.transferencia.model.Transferencia;

public interface TransferenciaService {

     List<Transferencia> getAllTransferencia();

     Page<Transferencia> getAllTransferenciaByConta(Long id, Pageable pageable);

     Page<Transferencia> getAllBydataTransferenciaByConta(String dataInicioString,String dataFimString, Long id,String nomeOperadorTransacao, Pageable pageable);
      
     Page<Transferencia> getAllBydataTransferenciaByContaByNomeOperador(LocalDateTime dataInicio,LocalDateTime dataFim, Long id,String nomeOperadorTransacao, Pageable pageable);
      
     Conta getContaById(Long id);

     Page<Transferencia> getAllTransferenciaByNomeOperadorTransacao(String nomeOperadorTransacao, Long contaId, Pageable pageable);

     Double saldoTotalConta(Long contaId);


    
}
