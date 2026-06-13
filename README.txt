--------------------------------------------------------------------------------
                 HISTÓRIA COM ESCOLHAS EM JAVA - DOCUMENTAÇÃO
                    UNIVERSIDADE ESTADUAL DO CENTRO-OESTE
                TRABALHO DE PROGRAMAÇÃO ORIENTADA A OBJETOS I
--------------------------------------------------------------------------------

AUTORES: Davi e Tiago
VERSÃO: 1.0
DATA: Junho de 2026

--------------------------------------------------------------------------------
                  1. DESCRIÇÃO E CONTEXTUALIZAÇÃO DO JOGO
--------------------------------------------------------------------------------
    O sistema consiste numa história medieval baseada inteiramente em escolhas
textuais interativas. O jogador assume o papel de um camponês num reino, onde
deve tomar decisões cruciais que guiam o rumo da narrativa através de uma árvore
de decisões e cenários.

    O jogo apresenta um sistema dinâmico de progressão de história lido através de
um arquivo externo de texto. O fluxo principal engloba interações pacíficas de
diálogo, caminhos traiçoeiros contendo armadilhas fatais e um sistema de combate
estratégico, onde cada escolha altera o rumo principal da história.

--------------------------------------------------------------------------------
                           2. COMPILAÇÃO E EXECUÇÃO
--------------------------------------------------------------------------------
    Para rodar o projeto corretamente, certifique-se de que a estrutura de pacotes
esteja organizada no mesmo nível do arquivo "história.txt".

    O arquivo "historia.txt" contendo o roteiro deve ser posicionado obrigatoriamente
na raiz principal do projeto para ser lido corretamente.

Execução:
    Para rodar o jogo, basta executar a classe Main presente dentro do package "principal".

--------------------------------------------------------------------------------
                    3. CONCEITOS DE POOI APLICADOS NO PROJETO
--------------------------------------------------------------------------------
ENCAPSULAMENTO:
      Aplicado rigidamente em todas as entidades do sistema. Os atributos de dados
  como vida, ataque, velocidade, id e escolhas foram declarados como privados 
  para evitar acessos externos indevidos. O controle e modificação desses estados
  ocorrem de maneira segura através de métodos acessores (getters) e métodos específicos,
  como o "receberDano(int dano)".

HERANÇA:
    Utilizada para reaproveitamento de código e criação de hierarquias lógicas.

   A classe abstrata 'Cena' serve de superclasse para as subclasses concretas
"CenaDialogo" e "CenaCombate".

  A classe abstrata 'Personagem' serve de superclasse para as subclasses "Jogador"
e "Inimigo", herdando os atributos básicos de combate.

POLIMORFISMO:
      Presente no loop principal de execução contido no "GerenciadorJogo". O mapa
  armazena referências genéricas do tipo da superclasse "Cena". Em tempo de
  execução, ao disparar "cena.executarCena()", o Java identifica dinamicamente
  se deve executar o comportamento narrativo da "CenaDialogo" ou a mecânica de
  combate por turnos da "CenaCombate". Também foi aplicado na sobrescrita do
  método "getVelocidade()" na classe Jogador.

INTERFACES:
      A interface "Interativa" padroniza o comportamento obrigatório de execução
  de qualquer cenário ou evento interativo inserido no jogo, forçando a 
  implementação do método "executarCena()" e o tratamento da exceção de menu.

--------------------------------------------------------------------------------
                      4. DIFERENCIAIS E REQUISITOS EXTRA
--------------------------------------------------------------------------------

SISTEMA DE INVENTÁRIO E COMBATE POR TURNOS:
      A classe "Jogador" possui uma lista interna contendo objetos da classe
  "Item", agindo como o seu inventário.
      Cada item possui uma propriedade de peso. À medida que o jogador passa por
  cenas específicas (como a forja do ferreiro) e adquire equipamentos, o peso 
  total do seu inventário é recalculado, reduzindo proporcionalmente o seu 
  atributo de velocidade, o qual afeta diretamente o combate da história, que
  por sua vez foi implementado por meio de um sistema de turnos.

JUSTIFICATIVA DA ESCOLHA:
      Nota-se que a criação de uma história medieval com perigos e caminhos
  diferentes torna-se desafiadora sem a implementação de um sistema de combate,
  portanto, tal sistema fora estabelecido como o diferencial do projeto.

PASSOS DE IMPLEMENTAÇÃO:
      A classe "Personagem" estende a interface nativa "Comparable". Isso permite
  o uso da estrutura de dados "PriorityQueue" (Fila de Prioridade) durante as
  cenas de batalha. A cada turno do loop de combate, o jogador e o inimigo são
  inseridos na fila, que se encarrega de ordenar e entregar primeiro o turno
  da entidade que possuir o maior atributo de velocidade atual. Caso o jogador
  esteja carregando muito peso, a sua prioridade cai e o inimigo ataca primeiro.
