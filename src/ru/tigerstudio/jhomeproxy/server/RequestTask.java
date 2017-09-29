package ru.tigerstudio.jhomeproxy.server;

import java.net.Socket;
import org.apache.log4j.Logger;

/**
 *
 * @author Tiger
 */
public class RequestTask implements Runnable {
    
    private final static Logger log = Logger.getLogger(Config.LOG_NAME);
    
    private Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        log.info(socket);
    }
    
}
