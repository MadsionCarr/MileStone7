package app.test;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import app.InventoryManager;
import app.SalableProduct;
import app.Weapon;
import app.Armor;
import app.Health;

/**
 * Unit tests for the {@link app.InventoryManager} class.
 *
 * <p>
 * This suite verifies correct behavior for inventory initialization, product
 * lookup, adding/removing items, purchasing, canceling purchases, sorting, and
 * the new Milestone 4 admin methods.
 * </p>
 */
public class InventoryManagerTest {

	@Test
	public void testInitializeInventoryAddsDefaultProducts() {
		InventoryManager manager = new InventoryManager();
		manager.initializeInventory();

		List<SalableProduct> products = manager.listProducts();
		assertEquals(5, products.size());
	}

	@Test
	public void testAddProduct() {
		InventoryManager manager = new InventoryManager();
		Weapon w = new Weapon("Axe", "Chopping weapon", 40.0, 5, "Slashing");

		manager.addProduct(w);

		assertEquals(1, manager.listProducts().size());
		assertEquals("Axe", manager.listProducts().get(0).getName());
	}

	@Test
	public void testRemoveProduct() {
		InventoryManager manager = new InventoryManager();
		manager.addProduct(new Weapon("Axe", "Chopping weapon", 40.0, 5, "Slashing"));

		manager.removeProduct("Axe");

		assertEquals(0, manager.listProducts().size());
	}

	@Test
	public void testFindByName() {
		InventoryManager manager = new InventoryManager();
		manager.addProduct(new Armor("Iron Shield", "Shield", 60.0, 3, "Iron"));

		SalableProduct found = manager.findByName("Iron Shield");

		assertNotNull(found);
		assertEquals("Iron Shield", found.getName());
	}

	@Test
	public void testPurchaseProductSuccess() {
		InventoryManager manager = new InventoryManager();
		manager.addProduct(new Health("Potion", "Heals", 20.0, 10, 5));

		boolean result = manager.purchaseProduct("Potion", 3);

		assertTrue(result);
		assertEquals(7, manager.findByName("Potion").getQuantity());
	}

	@Test
	public void testPurchaseProductFailsWhenNotEnoughQuantity() {
		InventoryManager manager = new InventoryManager();
		manager.addProduct(new Health("Potion", "Heals", 20.0, 2, 5));

		boolean result = manager.purchaseProduct("Potion", 5);

		assertFalse(result);
		assertEquals(2, manager.findByName("Potion").getQuantity());
	}

	@Test
	public void testCancelPurchase() {
		InventoryManager manager = new InventoryManager();
		manager.addProduct(new Weapon("Sword", "Sharp", 50.0, 5, "Slashing"));

		boolean result = manager.cancelPurchase("Sword", 2);

		assertTrue(result);
		assertEquals(7, manager.findByName("Sword").getQuantity());
	}

	@Test
	public void testSortAscending() {
		InventoryManager manager = new InventoryManager();
		manager.addProduct(new Weapon("Zeta", "desc", 10.0, 1, "Slash"));
		manager.addProduct(new Weapon("Alpha", "desc", 10.0, 1, "Slash"));

		manager.sortAscending();

		assertEquals("Alpha", manager.listProducts().get(0).getName());
	}

	@Test
	public void testSortDescending() {
		InventoryManager manager = new InventoryManager();
		manager.addProduct(new Weapon("Zeta", "desc", 10.0, 1, "Slash"));
		manager.addProduct(new Weapon("Alpha", "desc", 10.0, 1, "Slash"));

		manager.sortDescending();

		assertEquals("Zeta", manager.listProducts().get(0).getName());
	}

	@Test
	public void testUpdateInventoryReplacesList() {
		InventoryManager manager = new InventoryManager();

		List<SalableProduct> newList = new ArrayList<>();
		newList.add(new Weapon("Hammer", "Tool", 15.0, 4, "Blunt"));

		manager.updateInventory(newList);

		assertEquals(1, manager.getAllProducts().size());
		assertEquals("Hammer", manager.getAllProducts().get(0).getName());
	}

}
