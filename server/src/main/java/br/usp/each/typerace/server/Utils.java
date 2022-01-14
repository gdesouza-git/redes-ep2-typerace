package br.usp.each.typerace.server;

import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> IdsList = new ArrayList<>();
    public static List<Player> jogadores = new ArrayList<>();
    public static boolean jogoIniciado = false;
    public static int pontuacaoParaVencer = 0;

    public static String obterIdJogador(WebSocket conn){
        if (conn != null) {
            String id = conn.getResourceDescriptor();
            id = id.substring(1);
            return id;
        } else {
            //Se conn for null não podemos obter o id
            return "connNull";
        }
    }

    public static Player obterJogadorPorId(String id){
        for (Player jogador:jogadores) {
            if (jogador.getId().equals(id)) {
                return jogador;
            }
        }
        return null;
    }

    public static void verificarPalavra(WebSocket conn, String palavra){

        String idJogador = obterIdJogador(conn);
        Player jogador = obterJogadorPorId(idJogador);

        assert jogador != null;

        if (palavra.equals(jogador.getPalavrasRestantes().get(0))) {
            palavraCorreta(jogador);
        } else {
            conn.send("Palavra incorreta");
            jogador.setWrongTyping(jogador.getWrongTyping()+1);
        }
    }

    public static void palavraCorreta(Player jogador){
        assert jogador != null;
        jogador.setCorrectTyping(jogador.getCorrectTyping()+1);
        jogador.getWebSocket().send("Palavra correta.");
        jogador.removerPalavraAtual();
        if (jogador.getPalavrasRestantes().size() > 0) jogador.getWebSocket().send("\nPalavras restantes:");
        for (String palavra : jogador.getPalavrasRestantes())
            jogador.getWebSocket().send(palavra);
    }
}


