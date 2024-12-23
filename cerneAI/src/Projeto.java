package src;

import java.io.FileWriter; // Importar para gravar o arquivo
import java.io.IOException; // Importar para tratar exceções
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter; 
import java.util.ArrayList; 
import java.util.List;

public class Projeto {
    private String nomeProjeto;
    private String descricao;
    private LocalDate dataInicio;

    private static List<Projeto> projetos = new ArrayList<>();

    public Projeto(String nomeProjeto, String descricao, String dataInicioInput) {
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataInicio = LocalDate.parse(dataInicioInput, formatter);

        projetos.add(this);
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public String formatarData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataInicio.format(formatter);
    }

    public static List<Projeto> listarProjetos() {
        return projetos;
    }

    // Janela de Cadastro de Projeto
    private void cadastraProjeto() {
        subMenuPanel.getChildren().clear(); // Limpa a área de detalhes
        subMenuPanel.setStyle("-fx-border-color: lightgray; -fx-padding: 10;"); // Adiciona uma borda e padding
    
        Label titleLabel = new Label("Cadastrar Projeto");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;"); // Estilo do título
        subMenuPanel.getChildren().add(titleLabel); // Adiciona o título ao painel

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        // ComboBox para seleção de projetos
        Label lblProjetos = new Label("Selecione um Projeto:");
        ComboBox<CAEX> cmbProjetos = new ComboBox<>();
        cmbProjetos.getItems().addAll(CAEX.values()); // Adiciona todos os projetos

        // Campo para descrição do projeto
        Label lblDescricao = new Label("Descrição do Projeto:");
        TextField txtDescricao = new TextField();

        // Campo para data de início do projeto
        Label lblDataInicio = new Label("Data de Início:");
        DatePicker txtDataInicio = new DatePicker();

        // Campo para data de término do projeto
        Label lblDataTermino = new Label("Data de Término:");
        DatePicker txtDataTermino = new DatePicker();

        // Botão para salvar o projeto
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            String descricao = txtDescricao.getText();
            String dataInicio = (txtDataInicio != null) ? txtDataInicio.getValue().toString() : "";
            String dataTermino = (txtDataTermino != null) ? txtDataTermino.getValue().toString() : "";
            CAEX projetoSelecionado = cmbProjetos.getValue(); // Obtendo o projeto selecionado

            // Atualiza o subMenuPanel com a mensagem de sucesso
            Label successLabel = new Label("Projeto cadastrado com sucesso!\nProjeto: " + projetoSelecionado +
                                       "\nDescrição: " + descricao + 
                                       "\nData de Início: " + dataInicio + 
                                       "\nData de Término: " + dataTermino);
            successLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;"); // Estilo opcional para destacar a mensagem
            subMenuPanel.getChildren().add(successLabel);
        }); 

        // Adiciona os elementos ao grid
        grid.add(lblProjetos, 0, 0);
        grid.add(cmbProjetos, 1, 0);
        grid.add(lblDescricao, 0, 1);
        grid.add(txtDescricao, 1, 1);
        grid.add(lblDataInicio, 0, 2);
        grid.add(txtDataInicio, 1, 2);
        grid.add(lblDataTermino, 0, 3); // Adiciona rótulo para a data de término
        grid.add(txtDataTermino, 1, 3); // Adiciona campo para a data de término
        grid.add(btnSalvar, 1, 4);

        subMenuPanel.getChildren().add(grid); // Adiciona o grid à área de subMenu
    }
}
