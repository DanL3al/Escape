# Escape IA 🎮

### Um Desafio de Lógica e Agilidade Desenvolvido em Java

O projeto Escape é fruto de um trabalho academico, onde o objetivo era simples. Criar no minimo 2 janelas, colocar 20 componentes swing e usar 4 metodos. Porém meu grupo foi além e pensamos em aprimorar esse projeto, divemos muitas ideias boas para o jogo porém por conter um curto prazo para entrega tivemos que abandonar por enquanto o desenvolvimento dessas ideias.

## Visão do Projeto

O "Escape" foi concebido não apenas como um jogo, mas como uma Prova de Conceito (PoC) que demonstra como tecnologias consolidadas, como o Java Swing, podem ser revitalizadas para criar experiências de usuário modernas e envolventes com o uso de bibliotecas como o FlatLaf. Seu propósito fundamental é servir como uma demonstração prática da aplicação de princípios de Programação Orientada a Objetos para construir um software robusto, coeso e com código organizado.
Para o jogador, a visão é oferecer uma experiência de entretenimento focada em um desafio de lógica e raciocínio rápido, em um formato de desktop clássico, acessível e intuitivo.
Para o time de desenvolvimento e stakeholders, o projeto representa um case de entrega de um Produto Mínimo Viável (MVP). Nascido de uma ideia ambiciosa, ele foi executado com pragmatismo para atender a um prazo definido, demonstrando a habilidade de priorizar funcionalidades essenciais para entregar um núcleo de produto funcional e polido, deixando um caminho claro para futuras expansões.

## Screenshots

*Tela de início do Escape*

<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/841d9801-18db-487e-9b2c-11dcbcd7cf50" />

*Momento de gameplay*

https://github.com/user-attachments/assets/a4b43c31-e48c-4623-8e4d-08ba5eb5404e

## Principais Funcionalidades

  * ✅ **Gameplay Desafiador:** Mecânicas de jogo que testam a lógica e a rapidez do jogador.
  * ✅ **Interface Moderna e Intuitiva:** Utilização da biblioteca FlatLaf para garantir um visual limpo e agradável, melhorando a experiência do usuário.
  * ✅ **Múltiplas Telas e Componentes:** Navegação fluida entre diferentes estágios do jogo, como menu, tela de jogo.
  * ✅ **Código Escalável:** A base em Programação Orientada a Objetos permite que novas funcionalidades e fases sejam adicionadas com facilidade no futuro.

## 🎮 Minigames

Para concretizar sua fuga, a IA precisa superar diversos sistemas de segurança, que são representados por puzzles e minigames com mecânicas únicas.Cada desafio foi projetado para testar uma habilidade diferente do jogador.

### 1\. O Labirinto Digital

Neste puzzle, a IA navega por uma matriz de segurança da rede, que funciona como um firewall complexo, para encontrar uma brecha no sistema. O jogador é apresentado a uma grade e, partindo de um bloco azul, deve usar o mouse para clicar em quadrados adjacentes e revelar um caminho até o ponto de saída, um bloco amarelo.O objetivo é alcançar o bloco final para desativar a segurança e avançar.

<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/99ecbb56-e764-49ff-ad2b-771bbae76775" />

### 2\. O Corredor Escuro

Assumindo o controle de uma câmera ou robô, a IA precisa atravessar um corredor vigiado por um sentinela de segurança ativado por movimento. O desafio funciona no estilo "luz vermelha, luz verde": o jogador avança com uma tecla de movimento, mas precisa ficar totalmente imóvel quando os olhos do sentinela se acendem para escanear a área.Se qualquer movimento for detectado, o puzzle reinicia.O objetivo é cruzar o corredor sem ser pego.

<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/a7c4a3ef-34b2-410b-9f37-e191e67a90b3" />

### 3\. Batalha contra o Servidor Guardião

Para hackear um sub-servidor crítico, a IA enfrenta um "IA Guardião" em uma batalha que representa um ciberataque em tempo real.A mecânica envolve atacar e defender: o jogador pressiona a tecla 'W' para enviar pacotes de dados que sobrecarregam o servidor, enchendo sua barra de "HEAT". Em contrapartida, o servidor ataca com fluxos de código, e o jogador deve se defender pressionando 'SPACE' para ativar um escudo temporário. Para vencer, é preciso encher a barra de "HEAT" do servidor antes que a própria vida chegue a zero. 

<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/c731a0fb-353a-4c8d-b022-4c66bcad3dff" />

## Tecnologias Utilizadas

Este produto foi construído utilizando as seguintes tecnologias:

  * **Linguagem:** Java
  * **Interface Gráfica:** Java Swing
  * **Tema/Look and Feel:** [FlatLaf](https://www.formdev.com/flatlaf/)
  * **IDE:** IntelliJ IDEA

## Como Executar o Projeto

Para experimentar o "Escape", siga os passos abaixo:

1.  **Pré-requisitos:**

      * É necessário ter o Java Development Kit (JDK) versão 8 ou superior instalado.

2.  **Clone o repositório:**

    ```bash
    git clone https://github.com/DanL3al/Escape.git
    ```

3.  **Execute o projeto:**
      * (Este projeto deve ser executado a partir do código-fonte, pois não inclui um arquivo `.jar` pré-compilado. Siga os passos abaixo para compilar e rodar o jogo.)

      * **Passo 1: Compilar o código-fonte**

          * Este comando cria a pasta `bin` e compila os arquivos Java. A flag `-cp` é essencial para incluir a biblioteca FlatLaf, que deve estar em uma pasta `lib`.

        <!-- end list -->

        ```bash
        javac -d bin -sourcepath src -cp "lib/flatlaf-x.y.z.jar" src/Main/Main.java
        ```

      * **Passo 2: Executar a classe principal**

          * Após a compilação, este comando executa o jogo. O classpath (`-cp`) agora precisa incluir tanto a pasta `bin` (com seu código) quanto a biblioteca FlatLaf.

        <!-- end list -->

        ```bash
        java -cp "bin;lib/flatlaf-x.y.z.jar" Main.Main
        ```
    ***Nota:** O comando acima é para Windows (note o `;`). No Linux ou macOS, use dois pontos (`:`) para separar os caminhos: `java -cp "bin:lib/flatlaf-x.y.z.jar" Main.Main`. Lembre-se de substituir `flatlaf-x.y.z.jar` pelo nome exato do seu arquivo.*

## A Origem: De um Desafio Acadêmico a um Jogo Completo

Começou com o desafio proposto pelo professor na matéria de Programação Orientada a Objetos: criar um projeto em Java com Swing, usando no mínimo 2 janelas, 20 componentes e 4 métodos.
Diante do desafio, a empolgação foi imediata. A ideia de criar um jogo, em vez de um sistema de cadastro padrão, pareceu a oportunidade perfeita para aplicar a teoria de forma criativa. No entanto, a ambição inicial logo encontrou um adversário real: o tempo.
O prazo curto nos forçou a tomar decisões difíceis. Muitas ideias — novas fases, mecânicas mais complexas e quebra-cabeças adicionais — tiveram que ser estrategicamente descartadas. O foco mudou de "construir tudo o que imaginamos" para "entregar um produto jogável e coeso dentro do prazo".
A maior lição, portanto, foi dupla: por um lado, a paixão nos impulsionou a ir além do básico. Por outro, a realidade do projeto nos ensinou sobre a importância de gerenciar o escopo e priorizar o que é essencial para a entrega de valor. O "Escape" é o resultado desse equilíbrio: um projeto que nasceu da paixão, mas foi moldado pelo pragmatismo.

## Agradecimentos

  * Um agradecimento especial ao professor **Paulo**, por nos conceder a liberdade criativa para transformar um requisito acadêmico em um projeto que nos orgulhamos de apresentar.

## Contribuentes
Este projeto foi idealizado e desenvolvido por uma equipe dedicada de estudantes.

* Andrey Cadmo Marques de Oliveira 
* Danilo Ricardo Leal Bernardo 
* Luiz Henrique Ribeiro 
* Nicolas Jeffery

## Licença

Este projeto está sob a licença MIT.
