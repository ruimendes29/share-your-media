package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Base64;

public final class Connection implements Runnable {
    private static final String SERVER_DATA_DIR = System.getenv("SYM_SERVER_DATA_DIR");

    private static Logger log = LogManager.getLogger(Connection.class);

    private int id;
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Connection(final int id, final Socket socket) {
        this.id = id;
        this.socket = socket;
    }

    public void run() {
        log.info("Session " + this.id + " established on " + this.socket.getRemoteSocketAddress());

        try {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);

            String message;

            while ((message = this.in.readLine()) != null) {
                String[] argv = message.split("\\s+");
                byte[] r;

                switch (argv[0].toLowerCase()) {
                    case "download" :
                        log.trace("(" + this.id + ") downloading " + argv[1] + "...");
                        r = com.google.common.io.Files.toByteArray(new File(SERVER_DATA_DIR + argv[1]));
                        this.out.println(Base64.getEncoder().encodeToString(r));
                        log.info("(" + this.id + ") download " + argv[1] + " done");
                        break;
                    case "upload" :
                        log.trace("(" + this.id + ") uploading " + argv[1] + "...");
                        com.google.common.io.Files.write(Base64.getDecoder().decode(argv[2]),
                                new File(SERVER_DATA_DIR + argv[1]));
                        log.info("(" + this.id + ") upload " + argv[1] + " done");
                        break;
                    default :
                        log.error("Unkown request " + argv[0]);
                }
            }

            this.socket.shutdownOutput();
            this.socket.shutdownInput();
            this.socket.close();

        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }
}
