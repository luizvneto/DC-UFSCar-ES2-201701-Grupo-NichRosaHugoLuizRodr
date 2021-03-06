package net.sf.jabref.logic.remote.server;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This thread wrapper is required to be able to interrupt the remote listener while listening on a port.
 */
public class RemoteListenerServerThread extends Thread {

    private static final Log LOGGER = LogFactory.getLog(RemoteListenerServerThread.class);

    private final RemoteListenerServer server;

    public RemoteListenerServerThread(MessageHandler messageHandler, int port) throws IOException {
        this.server = new RemoteListenerServer(messageHandler, port);
        this.setName("JabRef - Remote Listener Server on port " + port);
    }

    @Override
    public void interrupt() {
        super.interrupt();

        LOGGER.debug("Interrupting " + this.getName());
        this.server.closeServerSocket();
    }

    @Override
    public void run() {
        this.server.run();
    }

}
