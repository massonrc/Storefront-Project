package test;

import storeapp.Armor;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This is a JUnit test for the Armor class.
 * @author rargueta
 *
 */

public class ArmorTest {

	/**
	 * Test case for the  Armor constructor and getters.
	 */
    @Test
    public void testArmor() {
        // Test case 1: Test empty constructor
        Armor armor = new Armor();
        assertNull(armor.getName());
        assertNull(armor.getDescription());
        assertEquals(0, armor.getPrice());
        assertEquals(0, armor.getQuantity());
        assertEquals(0, armor.getDefense());

        // Test case 2: Test constructor with parameters
        String name = "Chainmail";
        String description = "A protective layer of armor";
        int price = 200;
        int quantity = 3;
        int defense = 50;
        armor = new Armor(name, description, price, quantity, defense);
        assertEquals(name, armor.getName());
        assertEquals(description, armor.getDescription());
        assertEquals(price, armor.getPrice());
        assertEquals(quantity, armor.getQuantity());
        assertEquals(defense, armor.getDefense());
    }

    /**
     * Test case for the getDefense() method.
     */
    @Test
    public void testGetDefense() {
        // Create an armor with defense value
        String name = "Plate Armor";
        String description = "Heavy armor for maximum protection";
        int price = 500;
        int quantity = 1;
        int defense = 100;
        Armor armor = new Armor(name, description, price, quantity, defense);

        // Test the getDefense method
        assertEquals(defense, armor.getDefense());
    }

    /**
     * Test case for the setDefense(int) method.
     */
    @Test
    public void testSetDefense() {
        // Create an armor
        Armor armor = new Armor();

        // Test case 1: Set a positive defense value
        int defense = 75;
        armor.setDefense(defense);
        assertEquals(defense, armor.getDefense());

        // Test case 2: Set a zero defense value
        defense = 0;
        armor.setDefense(defense);
        assertEquals(defense, armor.getDefense());

        // Test case 3: Set a negative defense value
        defense = -50;
        armor.setDefense(defense);
        assertEquals(defense, armor.getDefense());
    }
}
