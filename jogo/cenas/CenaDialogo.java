package jogo.cenas;

import jogo.excecoes.OpcaoInvalidaException;
import jogo.modelo.Escolha;
import jogo.modelo.Item;
import jogo.modelo.ItemConsumivel;
import jogo.modelo.ItemDefesa;
import jogo.servico.GerenciadorJogo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Representa uma cena de diálogo
 * @author Davi e Tiago
 * @version 1.0
 */
public class CenaDialogo extends Cena{
    private List<Escolha> escolhas;

    public CenaDialogo(String id, String textoHistoria){
        super(id, textoHistoria);
        this.escolhas = new ArrayList<>();
    }

    @Override
    public void adicionarEscolha(Escolha escolha){
        this.escolhas.add(escolha);
    }

    @Override
    public String executarCena() throws OpcaoInvalidaException{

        if (getId().equals("cena7Bruto") && GerenciadorJogo.jogador != null) {
            System.out.println("[ITEM RECEBIDO] O Ferreiro te entrega uma Espada (Peso 3), um Escudo (Peso 2) e uma Armadura de Aço completa (Peso 5)!");
            GerenciadorJogo.jogador.adicionarItem(new Item("Espada Medieval", 3, "Uma espada rústica"));
            GerenciadorJogo.jogador.adicionarItem(new ItemDefesa("Escudo de Ferro", 2, "Um escudo robusto e circular", 5));
            GerenciadorJogo.jogador.adicionarItem(new ItemDefesa("Armadura de Aço", 5, "Uma armadura reluzente de aço", 10));
        }
        if (getId().equals("cena7Veloz") && GerenciadorJogo.jogador != null) {
            System.out.println("[ITEM RECEBIDO] O Ferreiro te entrega um Arco (Peso 2) e uma Armadua leve (Peso 1)!");
            GerenciadorJogo.jogador.adicionarItem(new Item("Arco Rústico", 2, "Um arco da guarda real"));
            GerenciadorJogo.jogador.adicionarItem(new ItemDefesa("Armadura leve", 1, "Uma armadura clássica dos arqueiros do reino", 2));
        }
        if (getId().equals("cena8Mago") && GerenciadorJogo.jogador != null) {
            System.out.println("[ITEM RECEBIDO] O Mago te ensina uma magia (Bola de fogo)!");
            GerenciadorJogo.jogador.adicionarItem(new Item("Magia de Fogo", 0, "Magia ensinada pelo Mago do rei"));
        }
        if (getId().equals("cena8Pocao") && GerenciadorJogo.jogador != null) {
            System.out.println("[ITEM RECEBIDO] O Mago te da uma Poção de vida (Peso 1)!");
            GerenciadorJogo.jogador.adicionarItem(new ItemConsumivel("Poção de Vida", 1, "Poção de vida", 50));
        }
        if (getId().equals("cena10_vitoria") && GerenciadorJogo.jogador != null) {
            System.out.println("[ITEM RECEBIDO] Carne de Lobo (Peso 0)!");
            GerenciadorJogo.jogador.adicionarItem(new ItemConsumivel("Carne de Lobo", 0, "Carne de Lobo", 15));
        }

        System.out.println("----------------------------");
        System.out.println(getTextoHistoria());
        System.out.println("----------------------------");

        if (escolhas.isEmpty()){
            System.out.println("[FIM DA AVENTURA]");
            return null;
        }

        for(int i=0; i< escolhas.size(); i++){
            System.out.println((i + 1) + ") " + escolhas.get(i).getTexto());
        }

        System.out.println("Escolha sua ação: ");
        Scanner scanner = new Scanner(System.in);

        if(!scanner.hasNextInt()){
            throw new OpcaoInvalidaException("Por favor, digite um número válido!");
        }

        int opcao = scanner.nextInt();

        if(opcao < 1 || opcao > escolhas.size()){
            throw new OpcaoInvalidaException("Opção inexistente, foco na missão!");
        }

        return escolhas.get(opcao - 1).getProximoNoId();
    }
}