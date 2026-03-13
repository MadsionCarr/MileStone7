package app.test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Armor;
/**
 * Unit tests for the {@link app.Armor} class.
 * 
 * <p>This test suite verifies that Armor objects correctly store and update
 * their inherited SalableProduct fields (name, description, price, quantity)
 * as well as their unique {@code material} property.</p>
 * 
 * <p>Tests include:</p>
 * <ul>
 *   <li>Constructor initialization of all fields</li>
 *   <li>Setter methods updating values properly</li>
 *   <li>Correct formatting of the inherited {@code toString()} method</li>
 * </ul>
 */
public class ArmorTest {


	@Test
	public void testConstructorSetsFields() {
		Armor a = new Armor("Iron Chestplate", "Heavy armor", 200.0, 3, "Iron");

		assertEquals("Iron Chestplate", a.getName());
		assertEquals("Heavy armor", a.getDescription());
		assertEquals(200.0, a.getPrice(), 0.001);
		assertEquals(3, a.getQuantity());
		assertEquals("Iron", a.getMaterial());
	}

	@Test
	public void testSettersUpdateFields() {
		Armor a = new Armor("Iron Chestplate", "Heavy armor", 200.0, 3, "Iron");

		a.setName("Steel Chestplate");
		a.setDescription("Reinforced armor");
		a.setPrice(250.0);
		a.setQuantity(5);
		a.setMaterial("Steel");

		assertEquals("Steel Chestplate", a.getName());
		assertEquals("Reinforced armor", a.getDescription());
		assertEquals(250.0, a.getPrice(), 0.001);
		assertEquals(5, a.getQuantity());
		assertEquals("Steel", a.getMaterial());
	}

	@Test
	public void testToStringFormat() {
		Armor a = new Armor("Iron Chestplate", "Heavy armor", 200.0, 3, "Iron");

		String expected = "Iron Chestplate - $200.0 (3 available)";
		assertEquals(expected, a.toString());
	}

}
