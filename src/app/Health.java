package app;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a health-type product in the store. Health items are salable
 * products that inherit all attributes and behavior from SalableProduct.
 */
@JsonTypeName("health")
public class Health extends SalableProduct {

	// new Milestone 4 property
	private int duration;

	public Health() {
		super();
	}
	
	/**
	 * Creates a new Health item with the given attributes.
	 * 
	 * @param name        the health item name
	 * @param description the health item description
	 * @param price       the health item price
	 * @param quantity    the available quantity
	 */

	public Health(String name, String description, double price, int quantity, int duration) {
		   super(name, description, price, quantity);
		    this.duration = duration;

	}

	/** * Gets the duration of the health effect. 
	 * 
	 * @return the duration value 
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * * Sets the duration of the health effect. 
	 * 
	 * @param duration the new
	 * duration value
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
