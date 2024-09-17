package control;

import java.time.LocalDate; // Importar para usar LocalDate
import java.time.format.DateTimeFormatter; // Importar para formatação de data
import java.util.ArrayList; // Importar ArrayList
import java.util.List; // Importar List

public class Projeto {
    private String nomeProjeto;
    private String descricao;
    private LocalDate dataInicio; // Usar LocalDate diretamente

    // Lista estática para armazenar projetos
    private static List<Projeto> projetos = new ArrayList<>();

    // Construtor com nome, descrição e data de início
    public Projeto(String nomeProjeto, String descricao, String dataInicioInput) {
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;

        // Converter a string da data para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formato esperado
        this.dataInicio = LocalDate.parse(dataInicioInput, formatter); // Converte para LocalDate

        // Adiciona o projeto à lista de projetos
        projetos.add(this);
    }

    // Getters
    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio; // Retorna a data de início como LocalDate
    }

    // Metodo auxiliar para formatar a data
    public String formatarData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato desejado
        return dataInicio.format(formatter); // Retorna a data formatada
    }

    // Metodo para listar todos os projetos (opcional)
    public static List<Projeto> listarProjetos() {
        return projetos;
    }
}
