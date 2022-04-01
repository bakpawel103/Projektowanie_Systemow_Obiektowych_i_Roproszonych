package com.psoir.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

public class Server {
    private static Server server;
    private ServerSocket serverSocket;
    private List<ClientSocket> clientSocketList = new ArrayList<>();

    private Server(int portNumber) throws IOException {
        serverSocket = new ServerSocket(portNumber);
    }

    void addClient(ClientSocket clientSocket) {
        clientSocketList.add(clientSocket);
    }

    public static void main(String[] args) throws IOException {
        server = new Server(3050);

        ClientAcceptor clientAcceptor = new ClientAcceptor(server.serverSocket, server);
        Thread clientAcceptorThread = new Thread(clientAcceptor);
        clientAcceptorThread.start();
    }
}