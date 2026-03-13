package app;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents an armor-type product in the store. Armor items are salable
 * products that inherit all attributes and behavior from SalableProduct.
 *
 */
@JsonTypeName("armor")
public class Armor extends SalableProduct {
	
	

	// New Milestone 4 property
	private String material;

	public Armor() {
		super();
	}
	
	/**
	 * created new armor with preset attributes
	 * 
	 * @param name        the armor name
	 * @param description the armor description
	 * @param price       the armor price
	 * @param quantity    the available quantity
	 */
	public Armor(String name, String description, double price, int quantity, String material) {
		super(name, description, price, quantity);
		this.material = material;
	}

	/** * Gets the material of the armor.
	 * 
	 * @return the armor material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * Sets the material of the armor.
	 * 
	 *  @param material the new material value
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

}
