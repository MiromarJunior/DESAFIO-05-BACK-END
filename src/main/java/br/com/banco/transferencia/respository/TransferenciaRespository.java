package br.com.banco.transferencia.respository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.banco.conta.model.Conta;
import br.com.banco.transferencia.model.Transferencia;

public interface TransferenciaRespository extends JpaRepository<Transferencia,Long>{

    List<Transferencia> findAllByConta(Conta conta);
   List<Transferencia> findAllByDataTransferenciaAndConta(LocalDate dataTransferencia, Conta conta);

     @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :dataInicio AND t.dataTransferencia < :dataFim AND t.conta.id = :contaId")
    List<Transferencia> findAllByIntervaloDataAndConta(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim, @Param("contaId") Long contaId);


    
}
