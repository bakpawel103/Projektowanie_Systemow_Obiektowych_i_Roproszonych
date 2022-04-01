package com.psoir.server;

import java.io.IOException;

public class ServerInputReader implements Runnable {
    private ClientSocket clientSocket;
    private boolean serverReaderRun = false;

    ServerInputReader(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        serverReaderRun = true;
    }

    @Override
    public void run() {
        while (serverReaderRun) {
            try {
                if(clientSocket.getDataInputStream() != null) {
                    if (clientSocket.getDataInputStream().available() > 0) {
                        clientSocket.getDataInputStream().readUTF();
                        String message = clientSocket.getObjectInputStream().readUTF();

                        System.out.println(message);
                    }
                } else {
                    serverReaderRun = false;
                    System.out.println("Client disconnected");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
