package br.com.banco.transferencia.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.banco.conta.model.Conta;
import br.com.banco.conta.repository.ContaRepository;
import br.com.banco.transferencia.model.Transferencia;
import br.com.banco.transferencia.respository.TransferenciaRespository;
import br.com.banco.transferencia.service.TransferenciaService;

@Service
public class TransferenciaServiceImp implements TransferenciaService {

    @Autowired
    TransferenciaRespository respository;

    @Autowired
    ContaRepository contaRepository;

    @Override
    public List<Transferencia> getAllTransferencia() {
        List<Transferencia> transferencias = respository.findAll();
        return transferencias;
        
 
    }

    @Override
    public List<Transferencia> getAllTransferenciaByConta(Long id) {
     Conta contaSelecionada =   getContaById(id);
    List<Transferencia> transferencias = respository.findAllByConta(contaSelecionada);
    return transferencias;

    }

    @Override
    public Conta getContaById(Long id) {
        return contaRepository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado nenhuma conta com o Nr : " +id));

       
    }

    


    
}
