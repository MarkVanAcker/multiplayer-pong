package server;

import com.esotericsoftware.kryonet.Server;

public class Game implements Runnable {

    private World world;
    private ServerProgram serverProgram;

    public Game(ServerProgram serverProgram) {
        this.serverProgram = serverProgram;
    }

    @Override
    public void run() {

    }
}
