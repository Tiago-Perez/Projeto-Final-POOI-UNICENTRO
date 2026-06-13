package jogo.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o jogador controlado pelo usuário
 * @author Davi e Tiago
 * @version 1.0
 */
public class Jogador extends Personagem{
    private List<Item> inventario;

    public Jogador(String nome, int vida, int ataque, int velocidade){
        super(nome, vida, ataque, velocidade);
        this.inventario = new ArrayList<>();
    }

    /**
     * Adiciona um item ao inventário do jogador
     * @param item : Item a ser guardado
     */
    public void adicionarItem(Item item) {
        this.inventario.add(item);
    }
    /**
     * Retorna a referência direta da lista de itens do inventário
     * @return List : Lista contendo os itens atuais do jogador
     */
    public List<Item> getListaItens() {
        return this.inventario;
    }

    /**
     * Cura os pontos de vida do jogador
     * @param quantidade : Quantidade de pontos a serem restaurados
     */
    public void curarVida(int quantidade) {
        this.receberDano(-quantidade);
    }

    /**
     * Calcula o peso total de todos os itens no inventário
     * @return int : Somatório dos pesos dos itens
     */
    public int getPesoTotal() {
        int total = 0;
        for (Item i : inventario) {
            total += i.getPeso();
        }
        return total;
    }

    @Override
    public int getVelocidade() {
        int velFinal = super.getVelocidade() - getPesoTotal();
        if (velFinal < 1) {
            return 1;
        }
        return velFinal;
    }

    @Override
    public void receberDano(int dano) {
        if (dano < 0) {
            super.receberDano(dano);
            return;
        }

        int defesaTotal = 0;
        for (Item item : getListaItens()) {
            if (item instanceof ItemDefesa) {
                defesaTotal += ((ItemDefesa) item).getReducaoDano();
            }
        }

        int danoFinal = dano - defesaTotal;
        if (danoFinal < 0) {
            danoFinal = 0;
        }

        super.receberDano(danoFinal);
    }
    @Override
    public int getAtaque() {
        int ataqueModificado = super.getAtaque();

        for (Item item : getListaItens()) {
            if (item.getNome().equalsIgnoreCase("Espada Medieval")) {
                ataqueModificado += 10; // Espada soma +15 de dano
            } else if (item.getNome().equalsIgnoreCase("Arco Rústico")) {
                ataqueModificado += 20; // Arco soma +10 de dano
            } else if (item.getNome().equalsIgnoreCase("Magia de Fogo")) {
                ataqueModificado += 45;  // Adaga soma +5 de dano
            }
        }

        return ataqueModificado;
    }
}