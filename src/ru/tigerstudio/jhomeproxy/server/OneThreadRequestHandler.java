package ru.tigerstudio.jhomeproxy.server;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.log4j.Logger;

/**
 *
 * @author Tiger
 */
public class OneThreadRequestHandler implements RequestHandler, Runnable {
    
    private final static Logger log = Logger.getLogger(Config.LOG_NAME);
    
    private final Thread thread;
    private final Queue<RequestTask> requestTaskQueue;

    public OneThreadRequestHandler() {
        requestTaskQueue = new LinkedList<>();
        thread = new Thread(this, "OneThreadRequestHandler");
        thread.start();
    }
    
    @Override
    public void run() {
        while (!thread.isInterrupted()) {
            RequestTask requestTask = null;
            synchronized (requestTaskQueue) {
                while (requestTaskQueue.isEmpty()) {
                    try {
                        requestTaskQueue.wait();
                    } catch (InterruptedException e) {
                        log.warn(e, e);
                    }
                }
                
                if (requestTaskQueue.size() > 0) {
                    requestTask = requestTaskQueue.remove();
                }
            }
            
            if (requestTask != null) {
                processRequest(requestTask);
            }
        }
    }
    
    public void processRequest(RequestTask requestTask) {
        System.out.println(requestTask);
        
    }
    
    @Override
    public void handle(RequestTask requestTask) {
        synchronized (requestTaskQueue) {
            requestTaskQueue.add(requestTask);
            requestTaskQueue.notifyAll();
        }
    }
    
    @Override
    public void stop() {
        thread.interrupt();
    }
}
