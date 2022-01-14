package br.usp.each.typerace.server;

import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ServerMain {

    private WebSocketServer server;

    public ServerMain(WebSocketServer server) {
        this.server = server;
    }

    public void init() {
        System.out.println("Iniciando servidor...");
        server.start();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        //Entrada da porta:
        int porta;
        System.out.println("Informe o numero da porta que deseja iniciar: ");
        String entradaPorta = reader.readLine();

        porta = Integer.parseInt(entradaPorta);

        WebSocketServer server = new Server(porta, new HashMap<>());
        ServerMain main = new ServerMain(server);
        main.init();

    }

}
