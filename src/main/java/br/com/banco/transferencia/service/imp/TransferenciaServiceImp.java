package br.com.banco.transferencia.service.imp;

import java.time.LocalDateTime;
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
        Conta contaSelecionada = getContaById(id);
        List<Transferencia> transferencias = respository.findAllByConta(contaSelecionada);
        return transferencias;

    }

    @Override
    public Conta getContaById(Long id) {
      Conta conta =  contaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado nenhuma conta com o Nr : " + id));
return conta;
    }

    @Override
    public List<Transferencia> getAllBydataTransferenciaByConta(String dataInicioString, String dataFimString,
            Long contaId) {

        LocalDateTime dataInicio = null;
        LocalDateTime dataFim = null;

        if (!dataFimString.isEmpty()) {
            dataFim = LocalDateTime.parse(dataFimString.trim() + "T23:59:59");
        }
        if (!dataInicioString.isEmpty()) {
            dataInicio = LocalDateTime.parse(dataInicioString.trim() + "T00:00:00");
        }

        if (dataFim == null && dataInicio == null) {
            return getAllTransferenciaByConta(contaId);
        }
        if (dataFim == null) {
            dataFim = LocalDateTime.now();
        }

        List<Transferencia> transferencias = respository.findAllByIntervaloDataAndConta(dataInicio, dataFim, contaId);
        return transferencias;
    }

}
