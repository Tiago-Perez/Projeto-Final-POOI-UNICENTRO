package jogo.cenas;

import jogo.excecoes.OpcaoInvalidaException;

/**
 * Interface que define o comportamento interativo de qualquer elemento do jogo
 * @author Davi e Tiago
 * @version 1.0
 */
public interface Interativa {
    /**
     * Executa a interação principal com o jogador
     * @return String contendo o ID da próxima cena
     * @throws OpcaoInvalidaException : Caso a entrada do usuário seja inválida
     */
    public String executarCena() throws OpcaoInvalidaException; //Executa a cena escolhida
}