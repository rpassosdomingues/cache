package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Monitoramento {
    private EmpresaIncubada empresaIncubada;
    private LocalDateTime dataHora;
    private List<String> documentosPendentes; // Lista de documentos pendentes
    private String arquivo; // Nome ou caminho do arquivo Excel com dados de notas, evolução, etc.

    // Lista estática para armazenar os monitoramentos
    private static List<Monitoramento> monitoramentos = new ArrayList<>();

    // Painel do submenu (declarado como variável de instância)
    private VBox subMenuPanel;

    // Construtor
    public Monitoramento(EmpresaIncubada empresaIncubada, LocalDateTime dataHora, List<String> documentosPendentes, String arquivo) {
        this.empresaIncubada = empresaIncubada;
        this.dataHora = dataHora;
        this.documentosPendentes = documentosPendentes;
        this.arquivo = arquivo;

        // Inicializa o painel de submenu
        this.subMenuPanel = new VBox();
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

    public String getArquivo() {
        return arquivo;
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
