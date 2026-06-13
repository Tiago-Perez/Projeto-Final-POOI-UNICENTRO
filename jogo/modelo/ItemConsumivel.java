package jogo.modelo;

/**
 * Representa um item consumível
 * @author Davi e Tiago
 * @version 1.0
 */
public class ItemConsumivel extends Item {
    private int quantidadeCura;

    public ItemConsumivel(String nome, int peso, String descricao, int quantidadeCura) {
        super(nome, peso, descricao);
        this.quantidadeCura = quantidadeCura;
    }

    public int getQuantidadeCura() {return quantidadeCura;}
}