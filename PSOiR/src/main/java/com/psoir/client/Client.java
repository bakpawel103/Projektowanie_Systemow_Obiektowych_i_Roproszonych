package com.psoir.client;

import com.psoir.server.ClientSocket;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private ClientSocket clientSocket;
    private ClientInputReader clientInputReader;

    private boolean clientRun = false;

    private Client(int portNumber) throws IOException {
        Socket socket = new Socket("localhost", portNumber);
        clientSocket = new ClientSocket(socket);
        clientRun = true;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(3050);
        client.clientInputReader = new ClientInputReader(client.clientSocket);
        Thread clientInputReaderThread = new Thread(client.clientInputReader);
        clientInputReaderThread.start();

        client.clientSocket.writeMessage("Hello sent from client!");
        while(client.clientRun) {

        }
    }

    private void stopClient() throws IOException {
        clientInputReader.stopClientInputReader();
        clientRun = false;
        if(clientSocket.getDataInputStream() != null)
            clientSocket.getDataInputStream().close();
        if(clientSocket.getDataOutputStream() != null)
            clientSocket.getDataOutputStream().close();
        if(clientSocket.getSocket() != null)
            clientSocket.getSocket().close();
        System.out.println("\n=============== Client closed ===============");
    }
}
