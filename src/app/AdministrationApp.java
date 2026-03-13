package app;

/**
 * AdministrationApp
 * CLI for admin actions: send R or U commands via AdministrationService.
 */

public class AdministrationApp {
	private AdministrationService adminService;

	public static void main(String[] args) {
		AdministrationApp app = new AdministrationApp();
		app.run();
	}

	public AdministrationApp() {
		this.adminService = new AdministrationService("localhost", 6666);
	}

	public void run() {
		// menu loop (no logic here)
	}

	private void displayMenu() {
		// show options
	}

	private void handleUpdate() {
		// gather payload and call adminService.sendUpdate(...)
	}

	private void handleRetrieve() {
		// call adminService.requestInventory()
	}
}
