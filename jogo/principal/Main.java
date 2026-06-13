package jogo.principal;

import jogo.servico.GerenciadorJogo;
import jogo.excecoes.ArquivoHistoriaException;

/**
 * Classe responsável por
 * @author Davi e Tiago
 * @version 1.0
 */
public class Main {
    public static void main(String[] args){
        GerenciadorJogo historia = new GerenciadorJogo();

        try{
            historia.carregarHistoriaDoArquivo("historia.txt");

            historia.iniciarJogo();

        } catch (ArquivoHistoriaException e) {
            System.err.println("Falha ao inicializar o jogo:");
            System.err.println(e.getMessage());
        }
    }
}