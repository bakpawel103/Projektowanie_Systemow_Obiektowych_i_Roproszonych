package com.psoir.client;

import com.psoir.server.ClientSocket;

import java.io.IOException;

public class ClientInputReader implements Runnable {
    private ClientSocket clientSocket;

    private boolean clientReaderRun = false;

    ClientInputReader(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        clientReaderRun = true;
    }

    @Override
    public void run() {
        while (clientReaderRun) {
            try {
                if (clientSocket.getSocket() != null) {
                    if(clientSocket.getDataInputStream().available() > 0) {
                        clientSocket.getDataInputStream().readUTF();
                        String message = clientSocket.getObjectInputStream().readUTF();

                        System.out.println(message);
                    }
                } else {
                    clientReaderRun = false;
                    System.out.println("Client disconnected");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void stopClientInputReader() {
        clientReaderRun = false;
    }
}