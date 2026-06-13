package jogo.excecoes;

/**
 * Exceção personalizada 2 : Lançada quando ocorre um erro na leitura ou estrutura do arquivo da história
 * @author Davi e Tiago
 * @version 1.0
 */
public class ArquivoHistoriaException extends Exception {
    /**
     * Construtor da exceção
     * @param mensagem : Mensagem mostrando o erro
     */
    public ArquivoHistoriaException(String mensagem) {
        super(mensagem);
    }
}