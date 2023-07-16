package br.com.banco.transferencia.respository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.conta.model.Conta;
import br.com.banco.transferencia.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia,Long>{

    List<Transferencia> findAllByConta(Conta conta);
   List<Transferencia> findAllByDataTransferenciaAndConta(LocalDateTime dataTransferencia, Conta conta);

     @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim AND t.conta.id = :contaId")
    List<Transferencia> findAllByIntervaloDataAndConta(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim, @Param("contaId") Long contaId);

     @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :dataInicio AND t.dataTransferencia <= :dataFim "+ 
    "AND t.conta.id = :contaId AND UPPER(t.nomeOperadorTransacao) = UPPER(:nomeOperadorTransacao)")
    List<Transferencia> findAllByIntervaloDataAndNomeOperadorAndConta(
      @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim, 
      @Param("contaId") Long contaId, @Param("nomeOperadorTransacao") String nomeOperadorTransacao);

     
      @Query("SELECT t FROM Transferencia t WHERE UPPER(t.nomeOperadorTransacao) = UPPER(:nomeOperadorTransacao) AND t.conta.id = :contaId")
      List<Transferencia> findByNomeOperadorTransacaoAndConta(@Param("nomeOperadorTransacao") String nomeOperadorTransacao, @Param("contaId") Long contaId);

      @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta.id = :contaId")
      Double sumValorByContaId(Long contaId);

    
}
