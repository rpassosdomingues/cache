package control;

import java.time.LocalDateTime;
import java.util.List;

public class Monitoramento {
    private EmpresaIncubada empresaIncubada;
    private LocalDateTime dataHora;
    private List<String> documentosPendentes; // Lista de documentos pendentes
    private String arquivoExcel; // Nome ou caminho do arquivo Excel com dados de notas, evolução, etc.

    // Construtor
    public Monitoramento(EmpresaIncubada empresaIncubada, LocalDateTime dataHora, List<String> documentosPendentes, String arquivoExcel) {
        this.empresaIncubada = empresaIncubada;
        this.dataHora = dataHora;
        this.documentosPendentes = documentosPendentes;
        this.arquivoExcel = arquivoExcel;
    }

    public void cadastrarMonitoramento(Monitoramento monitoramento) {
        monitoramentos.add(monitoramento);
        System.out.println("Monitoramento cadastrado com sucesso!");
    }

    // Getters
    public EmpresaIncubada getEmpresaIncubada() {
        return empresaIncubada;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public List<String> getDocumentosPendentes() {
        return documentosPendentes;
    }

    public String getArquivoExcel() {
        return arquivoExcel;
    }

    // Setters se necessário
    // ...
}
