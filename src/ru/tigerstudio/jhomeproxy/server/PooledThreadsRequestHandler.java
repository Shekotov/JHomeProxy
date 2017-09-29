package ru.tigerstudio.jhomeproxy.server;

import java.util.concurrent.ExecutorService;

/**
 *
 * @author Tiger
 */
public class PooledThreadsRequestHandler implements RequestHandler {

    private ExecutorService executorService;
    
    public PooledThreadsRequestHandler(ExecutorService executorService) {
        this.executorService = executorService;
    }
    
    @Override
    public void handle(RequestTask requestTask) {
        executorService.execute(requestTask);
    }

    @Override
    public void stop() {
        executorService.shutdown();
    }
    
}
