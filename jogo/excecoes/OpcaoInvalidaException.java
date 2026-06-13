package jogo.excecoes;

/**
 * Exceção personalizada 1 : lançada quando o jogador faz uma escolha inválida
 * @author Davi e Tiago
 * @version 1.0
 */
public class OpcaoInvalidaException extends Exception{
    /**
     * Construtor da exceção
     * @param mensagem : Mensagem mostrando o erro
     */
    public OpcaoInvalidaException(String mensagem){
        super(mensagem);
    }
}