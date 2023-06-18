package test;

import storeapp.Weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Test;

/**

A JUnit test class for the Weapon class.
*/
public class WeaponTest {

	/**
	 * Test case for the Weapon default constructor.
	 */
	@Test
	public void testWeapon() {
		// Create a Weapon object using the default constructor
		Weapon weapon = new Weapon();

		// Verify that the weapon object is not null
		assertNotNull(weapon);
		
		// Verify that the weapon object inherits properties from SalableProduct
		assertNull(weapon.getName());
		assertNull(weapon.getDescription());
		assertEquals(0, weapon.getPrice());
		assertEquals(0, weapon.getQuantity());
		
		// Verify that the weapon-specific property is initialized to 0
		assertEquals(0, weapon.getDamage());
	}

	/**
	 * Test case for the Weapon constructor with parameters.
	 */
	@Test
	public void testWeaponStringStringIntIntInt() {
		// Create sample values for the constructor parameters
		String name = "Sword";
		String description = "A sharp blade";
		int price = 100;
		int quantity = 5;
		int damage = 50;

		// Create a Weapon object using the constructor with parameters
		Weapon weapon = new Weapon(name, description, price, quantity, damage);

		// Verify that the Weapon object is initialized correctly
		assertEquals(name, weapon.getName());
		assertEquals(description, weapon.getDescription());
		assertEquals(price, weapon.getPrice());
		assertEquals(quantity, weapon.getQuantity());
		assertEquals(damage, weapon.getDamage());
	}

	/**
	 * Test case for the Weapon#getDamage() method.
	 */
	@Test
	public void testGetDamage() {
		// Create a Weapon object
		Weapon weapon = new Weapon();

		// Set the damage value to a specific value
		int damage = 75;
		weapon.setDamage(damage);

		// Verify that the getDamage method returns the correct value
		assertEquals(damage, weapon.getDamage());
	}

	/**
	 * Test case for the Weapon#setDamage(int) method.
	 */
	@Test
	public void testSetDamage() {
		// Create a Weapon object
		Weapon weapon = new Weapon();

		// Set the damage value to a specific value
		int damage = 50;
		weapon.setDamage(damage);

		// Verify that the damage value is set correctly
		assertEquals(damage, weapon.getDamage());
	}

	/**
	 * Test case for the Weapon#compareTo(Weapon) method.
	 */
	@Test
	public void testCompareTo() {
		// Create two Weapon objects with different names
		Weapon weapon1 = new Weapon("Sword", "A sharp blade", 100, 5, 50);
		Weapon weapon2 = new Weapon("Axe", "A heavy chopping tool", 150, 3, 70);

		// Verify that the compareTo method returns a negative value when comparing weapon1 to weapon2
		assertTrue(weapon1.compareTo(weapon2) < 0);
		
		// Verify that the compareTo method returns a positive value when comparing weapon2 to weapon1
		assertTrue(weapon2.compareTo(weapon1) > 0);
		
		// Verify that the compareTo method returns 0 when comparing weapon1 to itself
		assertEquals(0, weapon1.compareTo(weapon1));
	}
}