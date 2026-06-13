package jogo.modelo;

/**
 * Representa um equipamento de proteção que mitiga o dano recebido
 * @author Davi e Tiago
 * @version 1.0
 */
public class ItemDefesa extends Item {
    private int reducaoDano;

    public ItemDefesa(String nome, int peso, String descricao, int reducaoDano) {
        super(nome, peso, descricao);
        this.reducaoDano = reducaoDano;
    }

    public int getReducaoDano() {return reducaoDano;}
}