package app;
/**
 * AdminServerThread
 * Runs the AdminNetworkServer in a background thread.
 */

public class AdminServerThread extends Thread{
	
	 private AdminNetworkServer adminServer;

	    public AdminServerThread(AdminNetworkServer adminServer) {
	        this.adminServer = adminServer;
	    }

	    @Override
	    public void run() {
	        // call adminServer.start(...) or server loop
	    }

}
