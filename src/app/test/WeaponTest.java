package app.test;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Weapon;

public class WeaponTest {

    @Test
    public void testConstructorSetsFields() {
        Weapon w = new Weapon("Sword", "Sharp blade", 100.0, 5, "Slashing");

        assertEquals("Sword", w.getName());
        assertEquals("Sharp blade", w.getDescription());
        assertEquals(100.0, w.getPrice(), 0.001);
        assertEquals(5, w.getQuantity());
        assertEquals("Slashing", w.getDamageType());
    }
    
    @Test
    public void testSettersUpdateFields() {
        Weapon w = new Weapon("Sword", "Sharp blade", 100.0, 5, "Slashing");

        w.setName("Axe");
        w.setDescription("Heavy axe");
        w.setPrice(150.0);
        w.setQuantity(10);
        w.setDamageType("Chopping");

        assertEquals("Axe", w.getName());
        assertEquals("Heavy axe", w.getDescription());
        assertEquals(150.0, w.getPrice(), 0.001);
        assertEquals(10, w.getQuantity());
        assertEquals("Chopping", w.getDamageType());
    }
    
    @Test
    public void testToStringFormat() {
        Weapon w = new Weapon("Sword", "Sharp blade", 100.0, 5, "Slashing");

        String expected = "Sword - $100.0 (5 available)";
        assertEquals(expected, w.toString());
    }

}
