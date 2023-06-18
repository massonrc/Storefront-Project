package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import storeapp.InventoryManager;
import storeapp.SalableProduct;
import storeapp.ShoppingCart;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * A JUnit test class for the InventoryManager class
 * @author rargueta
 *
 */
public class InventoryManagerTest {

	private InventoryManager inventoryManager;
	
	/**
	 * Initializes the inventory manager and inventory file path for each test case.
	*/
	@Before
	public void setUp() {
		// Initialize the InventoryManager with a sample inventory file
        String inventoryFilePath = "inventorytest.json";
        createSampleInventoryFile(inventoryFilePath);
        inventoryManager = new InventoryManager(inventoryFilePath);
	}
	
	/**
     * Helper method to create a sample inventory file for testing.
     *
     * @param filePath the path to the sample inventory file
     */
    private void createSampleInventoryFile(String filePath) {
       File inventoryFile = new File(filePath);
        try (FileWriter writer = new FileWriter(inventoryFile)) {
            writer.write("{\"Product 1\":{\"name\":\"Product 1\",\"description\":\"Description 1\",\"price\":10,\"quantity\":5},\"Product 2\":{\"name\":\"Product 2\",\"description\":\"Description 2\",\"price\":20,\"quantity\":3}}");
        } 
        catch (IOException e) {
            e.printStackTrace();
       }
    }
    
	/**
	 * Test case for the getInventory() method.
	*/
	@Test
public void testGetInventory() {
	// Add some products to the inventory
	SalableProduct product1 = new SalableProduct("Product 1", "Description 1", 10, 5);
	SalableProduct product2 = new SalableProduct("Product 2", "Description 2", 20, 3);
	inventoryManager.addProduct(product1);
	inventoryManager.addProduct(product2);

	// Get the inventory map
	Map<String, SalableProduct> inventory = inventoryManager.getInventory();

	// Verify that the map is not empty and contains the added products
	assertFalse(inventory.isEmpty());
	assertTrue(inventory.containsKey("Product 1"));
	assertTrue(inventory.containsKey("Product 2"));
	assertEquals(product1, inventory.get("Product 1"));
	assertEquals(product2, inventory.get("Product 2"));
	}
	

	/**
	 * Test case for the updateInventory(ShoppingCart) method.
	*/
	@Test
	public void testUpdateInventory() {
	 // Create a ShoppingCart object
	ShoppingCart cart = new ShoppingCart();

	// Add some products to the cart
	SalableProduct product1 = new SalableProduct("Product 1", "Description 1", 10, 5);
	SalableProduct product2 = new SalableProduct("Product 2", "Description 2", 20, 3);
	cart.addProduct(product1, 2);
	cart.addProduct(product2, 4);

	// Update the inventory using the cart
	inventoryManager.updateInventory(cart);

	// Get the inventory map
	Map<String, SalableProduct> inventory = inventoryManager.getInventory();

	// Verify that the quantities are updated correctly in the inventory
	assertEquals(7, inventory.get("Product 1").getQuantity());
	assertEquals(7, inventory.get("Product 2").getQuantity());
	}

	/**
	 * Test case for the reduceQuantity(SalableProduct, int) method.
	*/
	@Test
	public void testReduceQuantity() {
		// Get the initial quantity of a product from the inventory
        int initialQuantity = inventoryManager.getInventory().get("Product 1").getQuantity();

        // Create a sample SalableProduct object
       SalableProduct product = new SalableProduct("Product 1", "Description 1", 10, 5);

        // Reduce the quantity of the product in the inventory
        int reductionAmount = 2;
        inventoryManager.reduceQuantity(product, reductionAmount);

        // Get the updated quantity from the inventory
        int updatedQuantity = inventoryManager.getInventory().get("Product 1").getQuantity();

        // Verify that the quantity is reduced by the specified amount
        assertEquals(initialQuantity - reductionAmount, updatedQuantity);
	}

	/**
	 * Test case for the processSale(ShoppingCart) method.
	*/
	@Test
	public void testProcessSale() {
		// Create a sample ShoppingCart with products
        ShoppingCart cart = new ShoppingCart();
        SalableProduct product1 = new SalableProduct("Product 1", "Description 1", 10, 2);
        SalableProduct product2 = new SalableProduct("Product 2", "Description 2", 20, 4);
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);

        // Process the sale using the ShoppingCart
        inventoryManager.processSale(cart);

        // Get the inventory map
        Map<String, SalableProduct> inventory = inventoryManager.getInventory();

        // Verify that the quantities in the inventory are reduced correctly
        assertEquals(4, inventory.get("Product 1").getQuantity());
        assertEquals(1, inventory.get("Product 2").getQuantity());
	}

	/**
	 * Test case for the {@link InventoryManager#increaseQuantity(SalableProduct, int)} method.
	*/
	@Test
	public void testIncreaseQuantity() {
	// Create a SalableProduct object and add it to the inventory
	SalableProduct product = new SalableProduct("Product", "Description", 10, 5);
	inventoryManager.addProduct(product);

	// Increase the quantity of the product in the inventory
	inventoryManager.increaseQuantity(product, 3);

	// Get the inventory map
	Map<String, SalableProduct> inventory = inventoryManager.getInventory();

	// Verify that the quantity is increased correctly in the inventory
	assertEquals(8, inventory.get("Product").getQuantity());
	}

	/**
	 * Test case for the removeProduct(String) method.
	*/
	@Test
	public void testRemoveProduct() {
	// Create a SalableProduct object and add it to the inventory
	SalableProduct product = new SalableProduct("Product", "Description", 10, 5);
	inventoryManager.addProduct(product);

	// Remove the product from the inventory
	inventoryManager.removeProduct("Product");

	// Get the inventory map
	Map<String, SalableProduct> inventory = inventoryManager.getInventory();

	// Verify that the product is removed from the inventory
	assertFalse(inventory.containsKey("Product"));
	}

	/**
	 * Test case for the addProduct(SalableProduct) method.
	*/
	@Test
	public void testAddProduct() {
	// Create a SalableProduct object and add it to the inventory
	SalableProduct product = new SalableProduct("Product", "Description", 10, 5);
	inventoryManager.addProduct(product);

	// Get the inventory map
	Map<String, SalableProduct> inventory = inventoryManager.getInventory();

	// Verify that the product is added to the inventory
	assertTrue(inventory.containsKey("Product"));
	assertEquals(product, inventory.get("Product"));

	// Add the same product again with a different quantity
	SalableProduct product2 = new SalableProduct("Product", "Description", 10, 3);
	inventoryManager.addProduct(product2);

	// Verify that the quantity is updated correctly in the inventory
	assertEquals(8, inventory.get("Product").getQuantity());
	}

	/**
	 * Test case for the loadInventoryFromFile(String) method.
	*/
	@Test
	public void testLoadInventoryFromFile() {
		// Create a sample inventory file with products
        File inventoryFile = new File("inventorytest.json");
        try (FileWriter writer = new FileWriter(inventoryFile)) {
            writer.write("{\"Product 1\":{\"name\":\"Product 1\",\"description\":\"Description 1\",\"price\":10,\"quantity\":5},\"Product 2\":{\"name\":\"Product 2\",\"description\":\"Description 2\",\"price\":20,\"quantity\":3}}");
        } 
		catch (IOException e) {
            e.printStackTrace();
        }

        // Load the inventory from the file
        inventoryManager.loadInventoryFromFile("inventorytest.json");

        // Get the inventory map
        Map<String, SalableProduct> inventory = inventoryManager.getInventory();

        // Verify that the inventory is loaded correctly from the file
        assertEquals(2, inventory.size());
        assertTrue(inventory.containsKey("Product 1"));
        assertTrue(inventory.containsKey("Product 2"));
        assertEquals("Product 1", inventory.get("Product 1").getName());
        assertEquals("Product 2", inventory.get("Product 2").getName());

        // Delete the temporary inventory file
        inventoryFile.delete();
    }
	
}

