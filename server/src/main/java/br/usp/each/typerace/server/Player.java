package br.usp.each.typerace.server;

import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{

    private String id;

    @Override
    public int compareTo(Player jogador) {
        if (jogador.getCorrectTyping() > this.correctTyping) return 1;
        else if (jogador.getCorrectTyping() < this.correctTyping) return -1;
        else return Integer.compare(jogador.getWrongTyping(), this.wrongTyping);
    }

    private int correctTyping;
    private int wrongTyping;
    private WebSocket webSocket;
    private List<String> palavrasRestantes;

    /*
            Construtor da classe Player.

            @param webSocket identifica a conexão WebSocket do jogador.
            */
    public Player(WebSocket webSocket) {
        this.webSocket = webSocket;
        this.correctTyping = 0;
        this.wrongTyping = 0;
        palavrasRestantes = new ArrayList<>();
    }

    public void removerPalavraAtual(){
        palavrasRestantes.remove(0);
    }

    // Métodos de acesso:
    public int getCorrectTyping() {
        return correctTyping;
    }

    public void setCorrectTyping(int correctTyping) {
        this.correctTyping = correctTyping;
    }

    public int getWrongTyping() {
        return wrongTyping;
    }

    public void setWrongTyping(int wrongTyping) {
        this.wrongTyping = wrongTyping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public List<String> getPalavrasRestantes() {
        return palavrasRestantes;
    }

    public void setPalavrasRestantes(List<String> palavrasRestantes) {
        this.palavrasRestantes.addAll(palavrasRestantes);
    }
}
