package jogo.modelo;

/**
 * Classe que representa uma opção de escolha dentro de um cenário
 * @author Davi e Tiago
 * @version 1.0
 */
public class Escolha {
    private String texto;
    private String proximoNoId;

    /**
     * Construtor da classe Escolha
     * @param texto : Texto descritivo da opção
     * @param proximoNoId : ID do cenário de destino
     */
    public Escolha(String texto, String proximoNoId) {
        this.texto = texto;
        this.proximoNoId = proximoNoId;
    }

    public String getTexto() {return texto;}
    public String getProximoNoId() {return proximoNoId;}
}