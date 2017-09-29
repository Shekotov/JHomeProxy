package ru.tigerstudio.jhomeproxy;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import ru.tigerstudio.jhomeproxy.server.Config;
import ru.tigerstudio.jhomeproxy.server.OneThreadRequestHandler;
import ru.tigerstudio.jhomeproxy.server.Server;

/**
 *
 * @author Tiger
 */
public class Main {
    
    private final static Logger log = Logger.getLogger(Config.LOG_NAME);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BasicConfigurator.configure();
        
        log.info("Init server...");
        Server server = new Server(8080, new OneThreadRequestHandler());
        server.start();
    }
    
}
