# redes-ep2-typerace
Repositório para o EP2 de Redes de Computadores, EACH-USP - 2021/2

# Integrantes
* Integrante 1 - Bruno Fonseca Taufner - 11953140
* Integrante 2 - Gustavo Oliveira de Souza - 11851042
* Integrante 3 - João Vitor Verissimo Barbosa de Paiva - 11837272 
* Integrante 4 - Nathália Luiza Souza e Silva - 6477087

## Pré-requisitos
* JDK 11 ou maior (testado com a JDK11 OpenJDK)
* Gradle (incluso no repositório, não é necessário instalá-lo)

## Executando
Primeiramente, o usuário deve executar o seguinte comando para inicializar o servidor:
```
./gradlew server:run
```
Após inicializar o servidor, em outra janela do terminal, o usuário deve inserir uma porta válida:

![image](https://user-images.githubusercontent.com/85649951/149584725-80b15d4d-d994-4a78-b67f-4ab7f23d5955.png)

Depois de inserir uma porta válida, o servidor será iniciado.

Agora o usuário deverá executar o seguinte comando em outra janela do terminal para instanciar um novo cliente:

```
./gradlew client:run
```
Será necessário inserir a mesma porta do servidor para efetuar uma conexão:

![image](https://user-images.githubusercontent.com/85649951/149585652-47f61f71-cbf4-48ee-b62b-d3a7a1e852b8.png)

Após isso, será solicitado ao usuário que escolha um ID, que deve ser composto por uma única String, sem espaços:

![image](https://user-images.githubusercontent.com/85649951/149586314-e7138122-a05e-49aa-bd65-d863a3c9a771.png)

Outros jogadores podem ser adicionados executando o mesmo comando em outras janelas no terminal.

Neste ponto, o jogar pode iniciar o jogo digitando "iniciar" seguido da pontuação necessária para considerar uma vitória.

![image](https://user-images.githubusercontent.com/85649951/149586613-8e5dde89-289a-4e9a-a2b1-5d522a6efcc3.png)

O Jogador deve escrever a palavra corretamente para registar um acerto, digitar de forma incorreta registra um erro.

O jogador que alcançar a pontuação máxima primeiro vence o jogo, sendo o critério de desempate o número de erros.

![image](https://user-images.githubusercontent.com/85649951/149586696-c9848df7-2be7-4d3c-a790-22cb718787a0.png)



