package br.com.banco;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.banco.transferencia.model.Transferencia;
import br.com.banco.transferencia.respository.TransferenciaRepository;
import br.com.banco.transferencia.service.imp.TransferenciaServiceImp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransferenciaServiceTests {

    @Mock
    private TransferenciaRepository repository;

    @InjectMocks
    private TransferenciaServiceImp service;

    @Test
    public void testGetAllBydataTransferenciaByConta() {
        // Parâmetros de exemplo
        String dataInicioString = "2023-01-01";
        String dataFimString = "2023-12-31";
        Long contaId = 1L;
        String nomeOperadorTransacao = "Operador";

        LocalDateTime dataInicio = LocalDateTime.parse(dataInicioString + "T00:00:00");
        LocalDateTime dataFim = LocalDateTime.parse(dataFimString + "T23:59:59");

        // Mock do resultado esperado do repositório
        List<Transferencia> expectedTransferencias = new ArrayList<>();
        // Adicione transferências de exemplo à lista de transferências esperada, se necessário

        // Configuração do comportamento simulado do repositório
        when(repository.findAllByIntervaloDataAndConta(dataInicio, dataFim, contaId))
                .thenReturn(expectedTransferencias);

        // Chamar o método a ser testado
        List<Transferencia> result = service.getAllBydataTransferenciaByConta(dataInicioString, dataFimString, contaId, nomeOperadorTransacao);

        // Verificar o resultado
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedTransferencias);

        // Verificar se o método do repositório foi chamado corretamente
        verify(repository, times(1)).findAllByIntervaloDataAndNomeOperadorAndConta(dataInicio, dataFim, contaId, nomeOperadorTransacao);
    }




}
