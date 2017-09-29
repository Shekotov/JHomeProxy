package ru.tigerstudio.jhomeproxy.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

/**
 *
 * @author Tiger
 */
public class Server implements Runnable {
    
    private final static Logger log = Logger.getLogger(Config.LOG_NAME);
    
    private Thread serverThread;
    private int port;
    private RequestHandler requestHandler;
    
    public Server(int port, RequestHandler requestHandler) {
        this.port = port;
        this.requestHandler = requestHandler;
    }
    
    public void start() {
        if (serverThread != null) {
            log.warn("Server still started!");
        }
        
        serverThread = new Thread(this, "serverThread");
        serverThread.start();
    }
    
    public void stop() {
        serverThread.interrupt();
    }
    
    public void run() {
        service();
    }
    
    public void service() {
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            log.error(e, e);
            return;
        }
        
        log.info("Server started on "+port+" port...");
        
        while (!serverThread.isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                requestHandler.handle(new RequestTask(socket));
            } catch (IOException e) {
                log.error(e, e);
            }
        }
        
        log.info("Server finished...");
    }
}
