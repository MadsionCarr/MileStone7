package app;
/**
 * AdminCommandHandler
 * Processes admin commands and interacts with InventoryManager.
 */

public class AdminCommandHandler {
	private InventoryManager inventoryManager;

    public AdminCommandHandler(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void handleUpdate(String jsonPayload) throws AdminInvalidException {
        // parse JSON, call inventoryManager.updateInventory(...)
    }

    public String handleRetrieve() {
        // call inventoryManager.getAllProducts() and return JSON
        return null;
    }

}
