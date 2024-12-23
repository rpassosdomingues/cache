package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Monitoramento {
    private EmpresaIncubada empresaIncubada;
    private LocalDateTime dataHora;
    private List<String> documentosPendentes; // Lista de documentos pendentes
    private String arquivoExcel; // Nome ou caminho do arquivo Excel com dados de notas, evolução, etc.

    // Lista estática para armazenar os monitoramentos
    private static List<Monitoramento> monitoramentos = new ArrayList<>();

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

    // Janela de Registro de Rodade de Monitoramento
    private void registraRodadaMonitoramento() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Registrar Rodada de Monitoramento");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15));

        Label lblEmpresa = new Label("Escolha a empresa:");
        Button btnPolygon = new Button("Polygon");
        Button btnICRO = new Button("ICRO Digital");
        Button btnSMART = new Button("SMARTComerci");
        Button btnILegis = new Button("iLegis");

        btnPolygon.setOnAction(e -> relatorioMonitoramento("Polygon"));
        btnICRO.setOnAction(e -> relatorioMonitoramento("ICRO Digital"));
        btnSMART.setOnAction(e -> relatorioMonitoramento("SMARTComerci"));
        btnILegis.setOnAction(e -> relatorioMonitoramento("iLegis"));

        vbox.getChildren().addAll(lblEmpresa, btnPolygon, btnICRO, btnSMART, btnILegis);

        subMenuPanel.getChildren().add(vbox); // Adiciona o VBox à área de subMenu
    }

    // Submenu de Detalhes de Monitoramento
    private void relatorioMonitoramento(String empresa) {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding

        Label titleLabel = new Label("Monitoramento " + empresa);
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        Label lblDataHora = new Label("Data e Hora:");
        DatePicker dataHora = new DatePicker();

        Label lblDocumento = new Label("Upload de Documento:");
        Button btnUpload = new Button("Upload PDF");

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            // Exibindo confirmação
            Label successLabel = new Label("Monitoramento para " + empresa + " registrado com sucesso!");
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;"); // Estilo opcional para destacar a mensagem
            subMenuPanel.getChildren().add(successLabel);
        });

        grid.add(lblDataHora, 0, 0);
        grid.add(dataHora, 1, 0);
        grid.add(lblDocumento, 0, 1);
        grid.add(btnUpload, 1, 1);
        grid.add(btnSalvar, 1, 2);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }
}
