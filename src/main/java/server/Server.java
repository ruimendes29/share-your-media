package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server {
    private static final String HOSTNAME = System.getenv("SYM_SERVER_HOSTNAME");
    private static final int PORT = Integer.parseInt(System.getenv("SYM_SERVER_PORT"));

    private static Logger log = LogManager.getLogger(Server.class);

    private ServerSocket socket;

    private Server() {
    }

    public static void main(final String[] ars) {
        clear();
        new Server().startUp();
    }

    public void startUp() {
        log.debug("Working Directory " + System.getProperty("user.dir"));

        try {
            this.socket = new ServerSocket();
            this.socket.bind(new InetSocketAddress(HOSTNAME, PORT));
            log.info("Server is up at " + this.socket.getLocalSocketAddress());
        } catch (IOException e) {
            log.fatal(e.getMessage());
            e.printStackTrace();
        }

        int id = 1;
        while (true) {
            try {
                log.info("Waiting for connection...");
                Socket clientServer = this.socket.accept();
                new Thread(new Connection(id, clientServer)).start();
                log.debug("Connection " + id + " started");
            } catch (IOException e) {
                log.fatal(e.getMessage());
                e.printStackTrace();
            }
            id++;
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
