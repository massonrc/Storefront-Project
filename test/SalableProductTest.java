package test;

import static org.junit.Assert.*;

import storeapp.SalableProduct;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains the unit tests for the SalableProduct class.
 * These tests verify the behavior of the SalableProduct class and 
 * ensure the methods are returning the expected results
 * @author rargueta
 *
 */

public class SalableProductTest {

    private SalableProduct product;

    /**
     * Sets up the test environment by creating a sample product for testing.
     */
    @Before
    public void setup() {
        // Create a sample product for testing
        product = new SalableProduct("Sword", "A long, two-handed blade", 100, 5);
    }

    /**
     * Tests the getName() method.
     * Verifies that the correct name is returned.
     */
    @Test
    public void testGetName() {
        assertEquals("Sword", product.getName());
    }

    /**
     * Tests the getDescription() method.
     * Verifies that the correct description is returned.
     */
    @Test
    public void testGetDescription() {
        assertEquals("A long, two-handed blade", product.getDescription());
    }

    /**
     * Tests the getPrice() method.
     * Verifies that the correct price is returned.
     */
    @Test
    public void testGetPrice() {
        assertEquals(100, product.getPrice());
    }

    /**
     * Tests the getQuantity() method.
     * Verifies that the correct quantity is returned.
     */
    @Test
    public void testGetQuantity() {
        assertEquals(5, product.getQuantity());
    }

    /**
     * Tests the setQuantity(int) method.
     * Verifies that the quantity is correctly set.
     * Also tests that setting a negative quantity is not allowed.
     */
    @Test
    public void testSetQuantity() {
    	 // Test case 1: Set a positive quantity
        int quantity = 10;
        product.setQuantity(quantity);
        assertEquals(quantity, product.getQuantity());

        // Test case 2: Set a zero quantity
        quantity = 0;
        product.setQuantity(quantity);
        assertEquals(quantity, product.getQuantity());

        // Set negative quantity: should not be allowed and expects an IllegalArgumentException
        quantity = -5;
        try {
            product.setQuantity(quantity);
            fail("Expected IllegalArgumentException was not thrown");
        } 
        catch (IllegalArgumentException e) {
            // Exception was thrown as expected
            assertEquals("Quantity cannot be negative", e.getMessage());
            assertEquals(0, product.getQuantity()); // Ensure the quantity was not changed
        }
    }
}

