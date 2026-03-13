package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * manages the store inventory of salable products Supports initializing
 * products, purchasing, and canceling purchases, and returning the full
 * inventory list.
 */

public class InventoryManager {

	/** List of all products in the inventory */
	private List<SalableProduct> products = new ArrayList<>();

	/** FileService used for loading and saving inventory */
	private FileService fileService = new FileService();

	/**
	 * initializes the inventory with default products add 2 weapons, 2 armors, 1
	 * health item (Used only if JSON loading fails or for initial setup.)
	 */
	public void initializeInventory() {

		products.add(new Weapon("Bow", "Sharp, long, steele weapon", 50.0, 10, "piercing"));
		products.add(new Weapon("Sword", "Sharp, long, steele weapon", 50.0, 10, "slashing"));
		products.add(new Armor("Iron Shield", "Defensive shield", 60.0, 6, "iron"));
		products.add(new Armor("Iron Chest Plate", "Protective torso armor", 75.0, 5, "iron"));
		products.add(new Health("Health Potion", "Restores health", 25.0, 10, 30));

	}
	
    public void updateInventory(List<SalableProduct> newProducts) {
    	this.products = new ArrayList<>(newProducts);
    }

    public List<SalableProduct> getAllProducts() {
    	return products;
    }

	/**
	 * * Loads inventory from a JSON file. * * @param filename the JSON file to load
	 * * @throws InventoryFileException if loading fails
	 */

	public void loadInventory(String filename) throws InventoryFileException {
		products = fileService.loadInventoryFromFilename(filename);
	}

	/**
	 * Saves the current inventory to a JSON file.
	 *
	 * @param filename the JSON file to save to
	 * @throws InventoryFileException if saving fails
	 */

	public void saveInventory(String filename) throws InventoryFileException {
		fileService.saveInventory(filename, products);
	}

	/**
	 * Adds a product to the inventory.
	 *
	 * @param product the product to add
	 */
	public void addProduct(SalableProduct product) {
		products.add(product);
	}

	/**
	 * Removes a product from the inventory by name.
	 * 
	 * @param name the product name
	 */

	public void removeProduct(String name) {
		products.removeIf(p -> p.getName().equalsIgnoreCase(name));
	}

	/**
	 * Return a product from the inventory by name.
	 * 
	 * @return list of all salable products
	 */

	public List<SalableProduct> listProducts() {
		return new ArrayList<>(products);
	}

	/**
	 * finds products by name (case - sensitive)
	 * 
	 * @param name the product name
	 * @return the matching product or null if not found
	 */
	public SalableProduct findByName(String name) {
		for (SalableProduct p : products) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}

		return null;
	}

	/**
	 * attempts to purchase a product by reducing its qty
	 * 
	 * @param name the product name
	 * @param qty  the quantity to purchase
	 * @return true if successful
	 */
	public boolean purchaseProduct(String name, int qty) {
		if (qty <= 0) return false;
		
		SalableProduct product = findByName(name);
		if (product != null && product.getQuantity() >= qty) {
			product.setQuantity(product.getQuantity() - qty);
			return true;
		}
		return false;
	}

	/**
	 * cancels a purchase by adding qty back to the product
	 * 
	 * @param name the product name
	 * @param qty  the quantity of to restore
	 * @return true if successful
	 */
	public boolean cancelPurchase(String name, int qty) {
		if (qty <= 0) return false;
		
		SalableProduct product = findByName(name);
		if (product != null) {
			product.setQuantity(product.getQuantity() + qty);
			return true;
		}

		return false;
	}

	/** * Sorts the inventory in ascending order by name, then price. */
	public void sortAscending() {
		Collections.sort(products);
	}

	/** * Sorts the inventory in descending order by name, then price. */
	public void sortDescending() {
		Collections.sort(products, Collections.reverseOrder());
	}

}
