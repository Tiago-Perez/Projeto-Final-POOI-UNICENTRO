package jogo.cenas;

import jogo.excecoes.OpcaoInvalidaException;
import jogo.modelo.Personagem;
import jogo.modelo.Jogador;
import jogo.modelo.Inimigo;
import jogo.modelo.Item;
import jogo.modelo.ItemConsumivel;
import jogo.modelo.ItemDefesa;
import jogo.servico.GerenciadorJogo;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Cena de combate por turnos utilizando uma Fila de Prioridade baseada na velocidade.
 * @author Davi e Tiago
 * @version 1.0
 */
public class CenaCombate extends Cena {
    private Inimigo inimigo;
    private String proximaCenaSucesso;
    private String proximaCenaFalha;
    private String textoVelocidade = " é mais rápido e ataca!";

    public CenaCombate(String id, String textoHistoria, String nomeInimigo, int vidaInim, int atkInim, int velInim, String sucessoId, String falhaId) {
        super(id, textoHistoria);
        this.inimigo = new Inimigo(nomeInimigo, vidaInim, atkInim, velInim);
        this.proximaCenaSucesso = sucessoId;
        this.proximaCenaFalha = falhaId;
    }

    @Override
    public String executarCena() throws OpcaoInvalidaException{
        System.out.println("----------------------------");
        System.out.println("⚔️ SEU COMBATE COMEÇOU! ⚔️");
        System.out.println(getTextoHistoria());
        System.out.println("Inimigo: " + inimigo.getNome() + " [Vida: " + inimigo.getVida() + "]");
        System.out.println("----------------------------");

        Jogador jogador = GerenciadorJogo.jogador;
        Scanner scanner = new Scanner(System.in);

        while (jogador.estaVivo() && inimigo.estaVivo()){
            System.out.println("--- Status Atual ---");
            System.out.println(jogador.getNome() + ": " + jogador.getVida() + " HP | Velocidade Atual: " + jogador.getVelocidade() + " (Peso: " + jogador.getPesoTotal() + ")");
            System.out.println(inimigo.getNome() + ": " + inimigo.getVida() + " HP | Velocidade: " + inimigo.getVelocidade());
            System.out.println("--------------------");

            System.out.println("1) Atacar");
            System.out.println("2) Abrir Inventário");
            System.out.print("Escolha sua ação: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equals("1")) {
                PriorityQueue<Personagem> filaTurnos = new PriorityQueue<>();
                filaTurnos.add(jogador);
                filaTurnos.add(inimigo);

                textoVelocidade = " é mais rápido e ataca!";

                while (!filaTurnos.isEmpty() && jogador.estaVivo() && inimigo.estaVivo()) {
                    Personagem atacante = filaTurnos.poll();

                    if (atacante instanceof Jogador){
                        System.out.println("[TURNO] " + jogador.getNome() + textoVelocidade);
                        inimigo.receberDano(jogador.getAtaque());
                        System.out.println("Deu " + jogador.getAtaque() + " de dano no " + inimigo.getNome());
                    } else {
                        System.out.println("[TURNO] " + inimigo.getNome() + textoVelocidade);
                        jogador.receberDano(inimigo.getAtaque());
                    }
                    textoVelocidade = " ataca!";
                }
            } else if (entrada.equals("2")) {
                boolean noInventario = true;
                while (noInventario) {
                    System.out.println("--- INVENTÁRIO DO JOGADOR ---");
                    List<Item> itens = jogador.getListaItens();

                    if (itens.isEmpty()) {
                        System.out.println("[Vazio]");
                    } else {
                        for (int i = 0; i < itens.size(); i++) {
                            System.out.println((i + 1) + ") " + itens.get(i).getNome() + " (Peso: " + itens.get(i).getPeso() + "kg)");
                        }
                    }
                    System.out.println("0) Voltar");
                    System.out.println("-----------------------------");
                    System.out.print("Escolha um item para interagir ou 0 para voltar: ");

                    String opcaoInv = scanner.nextLine().trim();

                    if (opcaoInv.equals("0")) {
                        noInventario = false;
                    } else {
                        try {
                            int indice = Integer.parseInt(opcaoInv) - 1;
                            if (indice >= 0 && indice < itens.size()) {
                                Item itemSelecionado = itens.get(indice);

                                if (itemSelecionado instanceof ItemConsumivel) {
                                    ItemConsumivel consumivel = (ItemConsumivel) itemSelecionado;

                                    System.out.println("[USANDO ITEM] Você consumiu " + consumivel.getNome() + "!");
                                    jogador.curarVida(consumivel.getQuantidadeCura());

                                    itens.remove(indice);

                                    if (inimigo.estaVivo()) {
                                        System.out.println("[CONTRA-ATAQUE] " + inimigo.getNome() + " atacou enquanto você se distraía!");
                                        jogador.receberDano(inimigo.getAtaque());
                                    }
                                    noInventario = false;
                                } else {
                                    // NOVIDADE: Em vez de bloquear, exibe os detalhes narrativos do item!
                                    System.out.println("=== EXAMINANDO ITEM ===");
                                    System.out.println("Nome: " + itemSelecionado.getNome());
                                    System.out.println("Descrição: " + itemSelecionado.getDescricao());
                                    System.out.println("Peso: " + itemSelecionado.getPeso() + "kg");
                                    if (itemSelecionado instanceof ItemDefesa) {
                                        System.out.println("Proteção: Reduz " + ((ItemDefesa) itemSelecionado).getReducaoDano() + " de dano recebido.");
                                    }
                                    System.out.println("=======================");
                                    System.out.println("Pressione ENTER para voltar ao inventário...");
                                    scanner.nextLine();
                                }
                            } else {
                                System.out.println("[ERRO] Opção de item inválida!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("[ERRO] Digite um número válido!");
                        }
                    }
                }
            } else {
                System.out.println("[OPÇÃO INVÁLIDA] Escolha uma ação existente no menu!");
                System.out.println("Tente novamente...");
            }
        }

        if (jogador.estaVivo()) {
            System.out.println("🎉 Vitória! Você derrotou o " + inimigo.getNome() + "!");
            return proximaCenaSucesso;
        } else {
            System.out.println("💀 Você foi derrotado em combate...");
            return proximaCenaFalha;
        }
    }
}