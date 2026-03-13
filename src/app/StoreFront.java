package app;

import java.util.List;
import java.util.Scanner;

/**
 * StoreFront class that manages user interaction with the inventory and
 * shopping cart.
 */

public class StoreFront {

	private InventoryManager inventory;
	private ShoppingCart cart;
	
	private AdminServerThread adminServerThread;

    public void startAdminServer(int port, InventoryManager inventoryManager) {
        AdminCommandHandler handler = new AdminCommandHandler(inventoryManager);
        AdminNetworkServer server = new AdminNetworkServer(handler);
        this.adminServerThread = new AdminServerThread(server);
        this.adminServerThread.start();
        // optionally call server.start(port) inside thread
    }

	/**
	 * entry point of store front
	 * 
	 * @param no real arguments at this time
	 */
	public static void main(String[] args) {
		StoreFront storeFront = new StoreFront();
		storeFront.run();
	}

	/**
	 * sets up the store and begins menu loop
	 */

	public void run() {

		System.out.println("=== Welcome to Madison's Game Store! ===");
		System.out.println("Your adventure begins here. Browse items, gear up, and prepare for battle!");

		inventory = new InventoryManager();
		cart = new ShoppingCart();
		try {
			inventory.loadInventory("inventory.json");
			System.out.println("Inventory loaded from file.");
		} catch (InventoryFileException e) {
			e.printStackTrace();
			System.out.println("Could not load inventory file. Using default inventory.");
			inventory.initializeInventory();
		}

		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			displayMenu();
			int choice = getUserChoice(sc);
			sc.nextLine();

			switch (choice) {
			case 1:
				viewProducts(sc);
				break;

			case 2:
				purchaseProduct(sc);
				break;
			case 3:
				cancelPurchase(sc);
				break;

			case 4:
				viewCart();
				break;

			case 5:
				checkout(sc);
				break;

			case 0:
				running = false;
				System.out.println("Exiting Store... Goodbye!");
// save inventory on exit
				try {
					inventory.saveInventory("inventory.json");
					System.out.println("Inventory saved.");
				} catch (InventoryFileException e) {
					System.out.println("Error saving inventory: " + e.getMessage());
					//inventory.initializeInventory();
				}
				break;

			default:
				System.out.println("Invalid option. Please try again.");

			}
		}

		sc.close();

	}

	/**
	 * display menu options
	 */
	public void displayMenu() {
		System.out.println("\n===== STORE MENU =====");
		System.out.println("1. View Products");
		System.out.println("2. Purchase Product");
		System.out.println("3. Cancel Purchase");
		System.out.println("4. View Cart");
		System.out.println("5. Checkout");
		System.out.println("0. Exit");
		System.out.print("Choose an option: ");
	}

	/**
	 * reads the user's menu choice
	 * 
	 * @param sc
	 * @return
	 */
	private int getUserChoice(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a number.");
			sc.next();
		}
		return sc.nextInt();

	}

	/**
	 * displays all products in the inventory
	 */

	private void viewProducts(Scanner sc) {
		System.out.println("\n--- Available Products ---");
		System.out.println("1. Sort Ascending (A -> Z, then price");
		System.out.println("2. Sort Descending (Z -> A, then price");
		System.out.println("Choose sorting option");
		
		
		int sortChoice = getUserChoice(sc);
		sc.nextLine();
		
		if(sortChoice == 1) {
			inventory.sortAscending();
			System.out.println("\nProducts sorted ascending: ");
		} else if (sortChoice == 2) {
			inventory.sortDescending();
			System.out.println("\nProducts sorted descending: ");
		} else {
			System.out.println("Invalid choice. showing unsorted list.");
		}
		for (SalableProduct p : inventory.listProducts()) {
			System.out.println(p);

		}
	}

	/**
	 * handles purchasing a product
	 * 
	 * @param sc
	 */
	private void purchaseProduct(Scanner sc) {

		System.out.print("Enter product name: ");
		String name = sc.nextLine();

		// check if product exists
		SalableProduct product = inventory.findByName(name);

		if (product == null) {
			System.out.println("Product not found.");
			return;
		}
		System.out.print("Enter quantity: ");
		int qty = getUserChoice(sc);
		sc.nextLine();
		
        if (qty <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

		// if product available, can sell
		if (inventory.purchaseProduct(name, qty)) {
			for (int i = 0; i < qty; i++) {
				cart.addItem(product);

			}

			System.out.println("Purchase added to cart!");

		} else {
			System.out.println("Product not found or out of stock");
		}
	}

	

	/**
	 * handles canceling a purchase
	 * 
	 * @param sc
	 */

	private void cancelPurchase(Scanner sc) {
		System.out.println("Enter product name to remove: ");
		String name = sc.nextLine();

		SalableProduct product = inventory.findByName(name);

		if (product == null) {
			System.out.println("Product not found.");
			return;
		}

		System.out.print("Enter quantity to remove: ");
		int qty = getUserChoice(sc);
		sc.nextLine();
		
        int countInCart = cart.countItem(name);
        if (qty > countInCart) {
            System.out.println("You only have " + countInCart + " in your cart.");
            return;
        }

		for (int i = 0; i < qty; i++) {
			cart.removeItem(name);
		}

		inventory.cancelPurchase(name, qty);
		System.out.println("Item(s) removed and restored to inventory.");
	}

	/**
	 * displays the contents of the shopping cart
	 * 
	 * @param sc
	 */
	private void viewCart() {
		System.out.println("\n--- Shopping Cart ---");

		List<SalableProduct> items = cart.getItems();

		if (items.isEmpty()) {
			System.out.println("Your cart is empty.");
			return;
		}

		for (SalableProduct p : items) {
			System.out.println(p.getName() + " - $" + p.getPrice());
		}

		System.out.println("Total: $" + cart.getTotal());
	}

	/** * Handles checkout logic. */
	private void checkout(Scanner sc) {
		System.out.println("\n--- Checkout ---");
//changes viewCart() to getItems()
		List<SalableProduct> items = cart.getItems();

		if (items.isEmpty()) {
			System.out.println("Your cart is empty.");
			return;
		}
		for (SalableProduct p : items) {
			System.out.println(p.getName() + " - $" + p.getPrice());
		}

		System.out.println("Total: $" + cart.getTotal());

		System.out.print("Confirm purchase? (y/n): ");

		String confirm = sc.nextLine();

		if (confirm.equalsIgnoreCase("y")) {
			//changes empty cart to clear cart
			cart.clearCart();
			System.out.println("Checkout complete! Thank you for your purchase.");
		} else {
			System.out.println("Checkout canceled.");
		}
	}
}
