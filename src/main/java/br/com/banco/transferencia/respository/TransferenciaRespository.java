package br.com.banco.transferencia.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.conta.model.Conta;
import br.com.banco.transferencia.model.Transferencia;

public interface TransferenciaRespository extends JpaRepository<Transferencia,Long>{
    List<Transferencia> findAllByConta(Conta conta);
    
}
