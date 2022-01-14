package br.usp.each.typerace.client;

import org.java_websocket.client.WebSocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ClientMain {

    private WebSocketClient client;

    public ClientMain(WebSocketClient client) {
        this.client = client;
    }

    public void init(String idCliente) {
        System.out.println("Iniciando cliente: " + idCliente);
        client.connect();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Scanner s = new Scanner(System.in);
        System.out.println("Informe a porta que deseja se conectar: ");
        String porta = s.nextLine();
        String servidor = "ws://localhost:"+porta;

        System.out.println("Informe o id do jogador: ");
        String clientId = s.nextLine();

        servidor += "/" + clientId;

        WebSocketClient client = new Client(new URI(servidor));
        ClientMain main = new ClientMain(client);
        main.init(clientId);

        recebeEntradas(client);
    }

    public static void recebeEntradas(WebSocketClient client) throws IOException {
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String in = reader.readLine();
            if (in.equals("sair")) {
                System.exit(0);
            }
            client.send(in);
        }
    }
}
