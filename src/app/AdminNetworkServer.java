package app;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * AdminNetworkServer
 * Listens for admin connections and delegates to AdminCommandHandler.
 */

public class AdminNetworkServer {
	
	 private ServerSocket serverSocket;
	    private boolean running;
	    private AdminCommandHandler commandHandler;

	    public AdminNetworkServer(AdminCommandHandler commandHandler) {
	        this.commandHandler = commandHandler;
	    }

	    public void start(int port) throws AdminServiceException {
	        // bind serverSocket and accept loop
	    }

	    public void stop() {
	        // stop server and close socket
	    }

	    private void handleClient(Socket socket) {
	        // read command, delegate to commandHandler
	    }

}
