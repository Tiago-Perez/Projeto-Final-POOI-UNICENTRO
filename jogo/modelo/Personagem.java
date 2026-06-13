package jogo.modelo;

/**
 * Classe abstrata que representa qualquer personagem no jogo
 * Implementa Comparable para ordenar a fila de prioridade do combate por turno
 * @author Davi e Tiago
 * @version 1.0
 */
public abstract class Personagem implements Comparable<Personagem>{
    private String nome;
    private int vida;
    private int ataque;
    private int velocidade;

    public Personagem(String nome, int vida, int ataque, int velocidade){
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.velocidade = velocidade;
    }

    public void receberDano(int dano){
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
    }

    public boolean estaVivo(){
        return this.vida > 0;
    }

    @Override
    public int compareTo(Personagem outro){
        return Integer.compare(outro.getVelocidade(), this.getVelocidade());
    }

    public String getNome() {return nome;}
    public int getVida() {return vida;}
    public int getAtaque() {return ataque;}
    public int getVelocidade() {return velocidade;}
}