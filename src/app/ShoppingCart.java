package app;

import java.util.ArrayList;
import java.util.List;

/**
 * represents users cart
 * provides methods for adding, removing, viewing items, 
 * and calculating totals.
 */
public class ShoppingCart {
	/** List of all items currently in the cart */
	private List<SalableProduct> items = new ArrayList<>();
	
	/**
	 * add a product to the cart 
	 * @param product
	 */
	
	public void addItem(SalableProduct product) {
		if(product != null) {
			items.add(product);
		}
	
	}
	
	/**
	 * removes product from cart by name
	 * 
	 * @param name the product name
	 */
	public void removeItem(String name) {
		items.removeIf(p -> p.getName().equalsIgnoreCase(name));
	}
	
    /**
     * Counts how many items of a given name are in the cart.
     * @param name the product name
     * @return number of matching items
     */
	
    public int countItem(String name) {
        int count = 0;
        for (SalableProduct p : items) {
            if (p.getName().equalsIgnoreCase(name)) {
                count++;
            }
        }
        return count;
    }
	
	/**
	 *  Empties all items from the cart. 
	 */ 
	public void clearCart() { 
		items.clear(); 
		}
	
	/**
	 * displays content of the cart plus price
	 * 
	 * @return list of items in the cart
	 */
	public List<SalableProduct> getItems() {
			return items;
		}
		
	
	/**
	 * calculates total cost of items in cart
	 * 
	 * @return
	 */
	public double getTotal() {
		double total = 0;
		
		for (SalableProduct p : items) {
			total += p.getPrice();
		}
		
		return total;
	}
}
