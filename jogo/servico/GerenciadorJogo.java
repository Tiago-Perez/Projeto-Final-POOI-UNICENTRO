package jogo.servico;

import jogo.cenas.Cena;
import jogo.cenas.CenaCombate;
import jogo.cenas.CenaDialogo;
import jogo.excecoes.ArquivoHistoriaException;
import jogo.excecoes.OpcaoInvalidaException;
import jogo.modelo.Escolha;
import jogo.modelo.Jogador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe responsável por gerenciar o mapa de cenários e o arquivo do roteiro
 * @author Davi e Tiago
 * @version 1.0
 */
public class GerenciadorJogo {
    private Map<String, Cena> mapaCenas;
    private String cenaInicialId;
    public static Jogador jogador;

    public GerenciadorJogo(){
        this.mapaCenas = new HashMap<>();
    }

    /**
     * Lê o arquivo de texto externo e monta a árvore de cenas no HashMap
     * @param caminhoArquivo : Caminho do arquivo .txt da história
     * @throws ArquivoHistoriaException : Caso o arquivo tenha problemas de leitura
     */
    public void carregarHistoriaDoArquivo(String caminhoArquivo) throws ArquivoHistoriaException{
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){
            String linha;
            Cena cenaAtual = null;

            while ((linha = br.readLine()) != null){
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                String[] partes = linha.split(";");
                String tipo = partes[0];

                if (tipo.equals("CENA")){
                    String id = partes[1];
                    String texto = partes[2];

                    cenaAtual = new CenaDialogo(id, texto);
                    mapaCenas.put(id, cenaAtual);

                    if (cenaInicialId == null) {
                        cenaInicialId = id;
                    }
                } else if (tipo.equals("ESCOLHA")) {
                    if (cenaAtual == null) {
                        throw new ArquivoHistoriaException("Arquivo corrompido: Escolha definida sem uma Cena antes.");
                    }
                    String textoEscolha = partes[1];
                    String proximaCenaId = partes[2];

                    cenaAtual.adicionarEscolha(new Escolha(textoEscolha, proximaCenaId));
                } else if (tipo.equals("COMBATE")){
                    String id = partes[1];
                    String texto = partes[2];
                    String nomeInimigo = partes[3];
                    int vidaInim = Integer.parseInt(partes[4]);
                    int atkInim = Integer.parseInt(partes[5]);
                    int velInim = Integer.parseInt(partes[6]);
                    String sucessoId = partes[7];
                    String falhaId = partes[8];

                    cenaAtual = new CenaCombate(id, texto, nomeInimigo, vidaInim, atkInim, velInim, sucessoId, falhaId);
                    mapaCenas.put(id, cenaAtual);

                    if (cenaInicialId == null) {
                        cenaInicialId = id;
                    }
                }
            }
        } catch (IOException e) {
            throw new ArquivoHistoriaException("Erro físico ao abrir o arquivo: " + e.getMessage());
        }
    }

    public void iniciarJogo(){
        String idCenaAtual = cenaInicialId;

        if (idCenaAtual == null || !mapaCenas.containsKey(idCenaAtual)){
            System.out.println("Erro: Nenhuma cena carregada para iniciar o jogo.");
            return;
        }

        System.out.println("--- BEM VINDO À AVENTURA ---");
        System.out.println("Digite o nome do seu herói: ");

        Scanner input = new Scanner(System.in);
        String nomeJogador = input.nextLine();

        jogador = new Jogador(nomeJogador, 100, 5, 12);

        while (idCenaAtual != null){
            Cena cena = mapaCenas.get(idCenaAtual);

            try {
                idCenaAtual = cena.executarCena();
            } catch (OpcaoInvalidaException e) {
                System.out.println("[ERRO] " + e.getMessage());
                System.out.println("Tente novamente esta cena...");
            }
        }

        System.out.println("Obrigado por jogar!");
    }
}