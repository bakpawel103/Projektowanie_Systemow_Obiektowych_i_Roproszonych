package com.psoir.entities;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private String host;
    private int port;
    private Socket socket;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        socket = new Socket(host, port);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }
}