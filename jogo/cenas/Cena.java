package jogo.cenas;

import jogo.modelo.Escolha;

/**
 * Classe abstrata que serve de base para todos os cenários do jogo
 * @author Davi e Tiago
 * @version 1.0
 */
public abstract class Cena implements Interativa{
    private String id;
    private String textoHistoria;

    /**
     * Construtor da classe Cena
     * @param id : Identificador único da cena
     * @param textoHistoria : Texto que narra a cena
     */
    public Cena (String id, String textoHistoria){
        this.id = id;
        this.textoHistoria = textoHistoria;
    }

    /**
     * Metodo base para adicionar escolhas. Subclasses como CenaDialogo
     * irão sobrescrever este comportamento
     * @param escolha : A escolha a ser adicionada
     */
    public void adicionarEscolha(Escolha escolha) {
    }

    public String getId(){return id;}
    public String getTextoHistoria(){return textoHistoria;}
}