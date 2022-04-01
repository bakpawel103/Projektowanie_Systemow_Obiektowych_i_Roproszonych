package com.psoir.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ClientAcceptor implements Runnable {
    private ServerSocket serverSocket;
    private Server server;
    private static boolean serverRun = false;

    ClientAcceptor(ServerSocket serverSocket, Server server) throws IOException {
        this.serverSocket = serverSocket;
        this.server = server;
        serverRun = true;
    }

    @Override
    public void run() {
        while(serverRun) {
            try {
                Socket socket = serverSocket.accept();
                ClientSocket clientSocket = new ClientSocket(socket);
                server.addClient(clientSocket);
                Thread serverInputReader = new Thread(new ServerInputReader(clientSocket));
                serverInputReader.start();
                System.out.println("Accepted new client on port: " + socket.getPort());

                clientSocket.writeMessage("Hi sent from server!");
            } catch(IOException exception) {
                System.out.println("Error in Server run method: " + exception);
            }
        }
    }

    void stopServer() {
        serverRun = false;
    }
}