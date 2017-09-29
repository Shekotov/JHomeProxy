package ru.tigerstudio.jhomeproxy.server;

/**
 *
 * @author Tiger
 */
public interface RequestHandler {
    public void handle(RequestTask requestTask);
    public void stop();
}
