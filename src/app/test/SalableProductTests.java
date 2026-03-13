package app.test;

import static org.junit.Assert.*;

import org.junit.Test;
import app.SalableProduct;

public class SalableProductTests {
	
	  private static class TestProduct extends SalableProduct {
	        public TestProduct(String name, String description, double price, int quantity) {
	            super(name, description, price, quantity);
	        }
	    }
	  
	   @Test
	    public void testConstructorSetsFields() {
	        SalableProduct p = new TestProduct("Sword", "Sharp blade", 100.0, 5);

	        assertEquals("Sword", p.getName());
	        assertEquals("Sharp blade", p.getDescription());
	        assertEquals(100.0, p.getPrice(), 0.001);
	        assertEquals(5, p.getQuantity());
	    }
	   
	   @Test
	    public void testSettersUpdateFields() {
	        SalableProduct p = new TestProduct("Sword", "Sharp blade", 100.0, 5);

	        p.setName("Axe");
	        p.setDescription("Heavy axe");
	        p.setPrice(150.0);
	        p.setQuantity(10);

	        assertEquals("Axe", p.getName());
	        assertEquals("Heavy axe", p.getDescription());
	        assertEquals(150.0, p.getPrice(), 0.001);
	        assertEquals(10, p.getQuantity());
	    }
	   
	   
	   @Test
	    public void testCompareToDifferentNames() {
	        SalableProduct a = new TestProduct("Apple", "desc", 10.0, 1);
	        SalableProduct b = new TestProduct("Banana", "desc", 10.0, 1);

	        assertTrue(a.compareTo(b) < 0);
	        assertTrue(b.compareTo(a) > 0);
	    }
	   
	   @Test
	    public void testCompareToSameNameDifferentPrice() {
	        SalableProduct cheap = new TestProduct("Sword", "desc", 50.0, 1);
	        SalableProduct expensive = new TestProduct("Sword", "desc", 100.0, 1);

	        assertTrue(cheap.compareTo(expensive) < 0);
	        assertTrue(expensive.compareTo(cheap) > 0);
	    }
	   
	    @Test
	    public void testToStringFormat() {
	        SalableProduct p = new TestProduct("Sword", "Sharp blade", 100.0, 5);

	        String expected = "Sword - $100.0 (5 available)";
	        assertEquals(expected, p.toString());
	    }

}
