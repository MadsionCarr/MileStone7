package app;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a weapon-type product in the store. Inherits all attributes and
 * behavior from SalableProduct.
 */
@JsonTypeName("weapon")
public class Weapon extends SalableProduct {

	// New Milestone 4 property
	private String damageType;
	
	public Weapon() {
		super();
	}

	/**
	 * Creates a new Weapon with the given attributes
	 * 
	 * @param name        the weapon name
	 * @param description the weapon description
	 * @param price       the weapon price
	 * @param quantity    the available quantity
	 */
	public Weapon(String name, String description, double price, int quantity, String damageType) {
		super(name, description, price, quantity);
		this.damageType = damageType;
	}

	/** * Gets the type of damage this weapon deals. 
	 * 
	 *  @return the damage type 
	 */
	public String getDamageType() {
		return damageType;
	}

	/**
	 * * Sets the type of damage this weapon deals.
	 * 
	 *  @param damageType the new
	 * damage type
	 */
	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}

}
