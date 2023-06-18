package test;

import static org.junit.Assert.*;

import org.junit.Test;

import storeapp.Health;

/**
 * A JUnit test for the Health class
 * @author rargueta
 *
 */
public class HealthTest {

	/**
	 * Test case for the Health default constructor.
	*/
	@Test
	public void testHealth() {
	// Create a Health object using the default constructor
	Health health = new Health();

	// Verify that the health amount is initialized to 0
	assertEquals(0, health.getHealthAmount());
	}

	/**
	 * Test case for the Health constructor with parameters.
	*/
	@Test
	public void testHealthStringStringIntIntInt() {
	// Create sample values for the constructor parameters
	String name = "Ginseng";
	String description = "Health item";
	int price = 5;
	int quantity = 25;
	int healthAmount = 25;

	// Create a Health object using the constructor with parameters
	Health health = new Health(name, description, price, quantity, healthAmount);

	// Verify that the Health object is initialized correctly
	assertEquals(name, health.getName());
	assertEquals(description, health.getDescription());
	assertEquals(healthAmount, health.getHealthAmount());
	}

	/**
	 * Test case for the Health#getHealthAmount() method.
	*/
	@Test
	public void testGetHealthAmount() {
	// Create a Health object
	Health health = new Health();

	// Set the health amount to a specific value
	int healthAmount = 75;
	health.setHealthAmount(healthAmount);

	// Verify that the getHealthAmount method returns the correct value
	assertEquals(healthAmount, health.getHealthAmount());
	}

	/**
	 * Test case for the setHealthAmount(int) method.
	*/
	@Test
	public void testSetHealthAmount() {
	// Create a Health object
	Health health = new Health();

	// Set the health amount to a specific value
	int healthAmount = 50;
	health.setHealthAmount(healthAmount);

	// Verify that the health amount is set correctly
	assertEquals(healthAmount, health.getHealthAmount());
	}
}
