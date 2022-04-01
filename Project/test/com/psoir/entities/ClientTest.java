package com.psoir.entities;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void shouldInstantiateSocket() {
        String host = "localhost";
        int port = 2019;

        Client client = new Client(host, port);

        assertSame(client.getHost(), host);
        assertSame(client.getPort(), port);
    }

    @Test
    public void shouldCreateSocket() {
        String host = "localhost";
        int port = 2019;

        Client client = new Client(host, port);
        assertThrows(IOException.class, client::start);
        assertNotNull(client.getSocket());
    }
}