package app.test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Health;
/**
 * Unit tests for the {@link app.Health} class.
 * 
 * <p>This test suite ensures that Health items correctly initialize and update
 * both their inherited SalableProduct attributes and their unique 
 * {@code duration} property.</p>
 * 
 * <p>Tests include:</p>
 * <ul>
 *   <li>Constructor correctly assigning all fields</li>
 *   <li>Setter methods updating values as expected</li>
 *   <li>Proper formatting of the inherited {@code toString()} output</li>
 * </ul>
 */
public class HealthTest {

	@Test
	public void testConstructorSetsFields() {
		Health h = new Health("Small Potion", "Restores a little HP", 25.0, 10, 5);

		assertEquals("Small Potion", h.getName());
		assertEquals("Restores a little HP", h.getDescription());
		assertEquals(25.0, h.getPrice(), 0.001);
		assertEquals(10, h.getQuantity());
		assertEquals(5, h.getDuration());
	}

	@Test
	public void testSettersUpdateFields() {
		Health h = new Health("Small Potion", "Restores a little HP", 25.0, 10, 5);

		h.setName("Large Potion");
		h.setDescription("Restores a lot of HP");
		h.setPrice(50.0);
		h.setQuantity(20);
		h.setDuration(10);

		assertEquals("Large Potion", h.getName());
		assertEquals("Restores a lot of HP", h.getDescription());
		assertEquals(50.0, h.getPrice(), 0.001);
		assertEquals(20, h.getQuantity());
		assertEquals(10, h.getDuration());
	}

	@Test
	public void testToStringFormat() {
		Health h = new Health("Small Potion", "Restores a little HP", 25.0, 10, 5);

		String expected = "Small Potion - $25.0 (10 available)";
		assertEquals(expected, h.toString());
	}

}
