package br.com.banco.transferencia.service.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.banco.conta.model.Conta;
import br.com.banco.conta.repository.ContaRepository;
import br.com.banco.transferencia.model.Transferencia;
import br.com.banco.transferencia.respository.TransferenciaRepository;
import br.com.banco.transferencia.service.TransferenciaService;

@Service
public class TransferenciaServiceImp implements TransferenciaService {

    @Autowired
    TransferenciaRepository respository;

    @Autowired
    ContaRepository contaRepository;

    @Override
    public List<Transferencia> getAllTransferencia() {
        List<Transferencia> transferencias = respository.findAll();
        return transferencias;

    }

    @Override
    public Page<Transferencia> getAllTransferenciaByConta(Long id,Pageable pageable) {
        Conta contaSelecionada = getContaById(id);
        Page<Transferencia> transferencias = respository.findAllByConta(contaSelecionada,pageable);
        return transferencias;

    }

    @Override
    public Conta getContaById(Long id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Não foi encontrado nenhuma conta com o Nr : " + id));
        return conta;
    }

    @Override
    public Page<Transferencia> getAllBydataTransferenciaByConta(String dataInicioString, String dataFimString,
            Long contaId, String nomeOperadorTransacao,Pageable pageable) {

        LocalDateTime dataInicio = null;
        LocalDateTime dataFim = null;

        if (!dataFimString.isEmpty()) {
            dataFim = LocalDateTime.parse(dataFimString.trim() + "T23:59:59");
        }
        if (!dataInicioString.isEmpty()) {
            dataInicio = LocalDateTime.parse(dataInicioString.trim() + "T00:00:00");
        }

        if (dataFim == null && dataInicio == null && nomeOperadorTransacao.isEmpty()) {
            return getAllTransferenciaByConta(contaId,pageable);
        }

        if (dataFim == null && dataInicio == null && !nomeOperadorTransacao.isEmpty()) {
            return getAllTransferenciaByNomeOperadorTransacao(nomeOperadorTransacao.trim(), contaId,pageable);
        }

        if (dataFim == null) {
            dataFim = LocalDateTime.now();
        }

        if (dataFim != null && dataInicio != null && !nomeOperadorTransacao.isEmpty()) {

            return getAllBydataTransferenciaByContaByNomeOperador(dataInicio, dataFim, contaId,
                    nomeOperadorTransacao.trim(),pageable);
        }

        Page<Transferencia> transferencias = respository.findAllByIntervaloDataAndConta(dataInicio, dataFim, contaId,pageable);
        return transferencias;
    }

    @Override
    public Page<Transferencia> getAllBydataTransferenciaByContaByNomeOperador(LocalDateTime dataInicio,
            LocalDateTime dataFim, Long contaId, String nomeOperadorTransacao,Pageable pageable) {
        Page<Transferencia> transferencias = respository
                .findAllByIntervaloDataAndNomeOperadorAndConta(dataInicio, dataFim, contaId, nomeOperadorTransacao,pageable);
        return transferencias;
    }

    @Override
    public Page<Transferencia> getAllTransferenciaByNomeOperadorTransacao(String nomeOperadorTransacao, Long contaId , Pageable pageable) {
        Page<Transferencia> transferencias = respository.findByNomeOperadorTransacaoAndConta(nomeOperadorTransacao,
                contaId,pageable);
        return transferencias;
    }

    @Override
    public Double saldoTotalConta(Long contaId) {
        return respository.sumValorByContaId(contaId);

    }

}
