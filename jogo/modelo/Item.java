package jogo.modelo;

/**
 * Classe base que representa qualquer item do jogo
 * @author Davi e Tiago
 * @version 1.0
 */
public class Item {
    private String nome;
    private int peso;
    private String descricao;

    public Item(String nome, int peso, String descricao) {
        this.nome = nome;
        this.peso = peso;
        this.descricao = descricao;
    }

    public String getNome() {return nome;}
    public double getPeso() {return peso;}
    public String getDescricao() {return descricao;}
}