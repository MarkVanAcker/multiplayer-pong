package com.mygdx.desktop;

import client.ClientGame;

import java.io.IOException;

public class Main {


    final static String hostname = "localhost";

    public static void main(String[] args) throws IOException {
        ClientGame game = new ClientGame(hostname);
    }

}
