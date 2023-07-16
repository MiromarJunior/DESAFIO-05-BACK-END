package br.com.banco.transferencia.respository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.conta.model.Conta;
import br.com.banco.transferencia.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia,Long>{

    Page<Transferencia> findAllByConta(Conta conta, Pageable pageable);
   List<Transferencia> findAllByDataTransferenciaAndConta(LocalDateTime dataTransferencia, Conta conta);

     @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim AND t.conta.id = :contaId")
    Page<Transferencia> findAllByIntervaloDataAndConta(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim, @Param("contaId") Long contaId,Pageable pageable);

     @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim "+ 
    "AND t.conta.id = :contaId AND UPPER(t.nomeOperadorTransacao) = UPPER(:nomeOperadorTransacao)")
    Page<Transferencia> findAllByIntervaloDataAndNomeOperadorAndConta(
      @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim, 
      @Param("contaId") Long contaId, @Param("nomeOperadorTransacao") String nomeOperadorTransacao,Pageable pageable);

     
      @Query("SELECT t FROM Transferencia t WHERE UPPER(t.nomeOperadorTransacao) = UPPER(:nomeOperadorTransacao) AND t.conta.id = :contaId")
      Page<Transferencia> findByNomeOperadorTransacaoAndConta(@Param("nomeOperadorTransacao") String nomeOperadorTransacao, @Param("contaId") Long contaId,Pageable pageable);

      @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta.id = :contaId")
      Double sumValorByContaId(Long contaId);

    
}
