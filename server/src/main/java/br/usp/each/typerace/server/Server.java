package br.usp.each.typerace.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;


public class Server extends WebSocketServer {

    private final Map<String, WebSocket> connections;
    public long startTime;

    public Server(int port, Map<String, WebSocket> connections) {
        super(new InetSocketAddress(port));
        this.connections = connections;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        connections.put(Utils.obterIdJogador(conn), conn);
        if (conn != null) {
            Player jogador = new Player(conn);
            Utils.jogadores.add(jogador);
            Utils.IdsList.add(Utils.obterIdJogador(conn));
            jogador.setId(Utils.obterIdJogador(conn));
            broadcast("\n"+Utils.obterIdJogador(conn) + " entrou no jogo.\n Jogadores:");
            for (String player : Utils.IdsList) {
                broadcast(" "+player);
            }
            conn.send("\nInsira 'iniciar [pontuacao para vencer]' para iniciar o jogo.");

        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(Utils.obterIdJogador(conn));
        broadcast(Utils.obterIdJogador(conn)+" saiu da sala.");
        Utils.IdsList.remove(Utils.obterIdJogador(conn));
        Utils.jogadores.remove(Utils.obterJogadorPorId(Utils.obterIdJogador(conn)));
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (!Utils.jogoIniciado) {

            //Verificar comandos
            String[] mensagem = message.split(" ");

            if(mensagem[0].equalsIgnoreCase("iniciar") && mensagem.length == 2) {
                Utils.pontuacaoParaVencer = Integer.parseInt(mensagem[1]);
                if (!Utils.jogoIniciado) {
                    try {
                        iniciarJogo(Integer.parseInt(mensagem[1]));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                conn.send("O comando "+ message +" nao existe.");
            }
        } else {
            //Jogo iniciado
            Utils.verificarPalavra(conn, message);
            String id = Utils.obterIdJogador(conn);
            Player jogador = Utils.obterJogadorPorId(id);
            if (jogador.getCorrectTyping() == Utils.pontuacaoParaVencer) finalizarJogo();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("\nErro: \n"+ex);
    }

    @Override
    public void onStart() {
        System.out.println("O servidor foi iniciado.\nAguardando jogadores.");
    }

    public void iniciarJogo(int numeroPalavras) throws FileNotFoundException {

        startTime = System.currentTimeMillis();

        Typerace jogo = new Typerace(numeroPalavras);
        List<String> palavras;
        palavras = jogo.gerarListaPalavras();

        if (!Utils.jogoIniciado)
            for (Player jogador : Utils.jogadores)
                jogador.setPalavrasRestantes(palavras);

        Utils.jogoIniciado = true;

        broadcast("\nDigite:");
        for (String palavraJogo:palavras) {
            broadcast(palavraJogo);
        }
    }

    public void finalizarJogo() {

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        Typerace.classificacao();
        broadcast("\nJogo finalizado.\nClassificacao\tID\t\tAcertos\t\tErros");
        for (int i = 0; i < Utils.jogadores.size(); i++) {
            broadcast((i+1)+"\t\t"+Utils.jogadores.get(i).getId()+"\t\t"+Utils.jogadores.get(i).getCorrectTyping()+"\t\t"+Utils.jogadores.get(i).getWrongTyping());
        }
        broadcast("Duracao do jogo: "+(int)elapsedTime/60000+" minutos e "+(elapsedTime/1000-(int)elapsedTime/60000)+" segundos");
        System.exit(0);
    }

}
