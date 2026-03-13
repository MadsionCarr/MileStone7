package app;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * AdministrationService
 * Client-side networking helper for sending admin commands.
 */

public class AdministrationService {
	
	private String host;
    private int port;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public AdministrationService(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws AdminServiceException {
        // open socket, set up in/out
    }

    public void sendUpdate(List<SalableProduct> products) throws AdminServiceException {
        // send "U" + JSON payload
    }

    public List<SalableProduct> requestInventory() throws AdminServiceException {
        // send "R", parse JSON response
        return null;
    }

    public void cleanup() {
        // close streams and socket
    }

}
