package java_sprint_boot.modelo;

import java.util.ArrayList;
import java.util.List;

public class Destino {
    private Long id;
    private String nome;
    private String localizacao;
    private String detalhes;
    private List<Integer> avaliacoes = new ArrayList<>();

    // Construtores, getters e setters

    public Destino() {}

    public Destino(Long id, String nome, String localizacao, String detalhes) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.detalhes = detalhes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public List<Integer> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Integer> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void adicionarAvaliacao(int nota) {
        this.avaliacoes.add(nota);
    }

    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) return 0;
        int totalNotas = avaliacoes.stream().mapToInt(Integer::intValue).sum();
        return (double) totalNotas / avaliacoes.size();
    }
}
