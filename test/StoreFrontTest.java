package test;

import org.junit.Before;
import org.junit.Test;

import storeapp.InventoryManager;
import storeapp.SalableProduct;
import storeapp.ShoppingCart;
import storeapp.StoreFront;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * JUnit test class for the StoreFront class.
 * @author rargueta
 *
 */

public class StoreFrontTest {
    private StoreFront store;

    /**
     * Set up the test by creating a new instance of StoreFront.
     */
    @Before
    public void setUp() {
        store = new StoreFront();
    }

    /**
     * Test the updateInventory method of StoreFront.
     * Adds products to a shopping cart and updates the inventory.
     * Asserts that the inventory quantities are updated correctly.
     */
    @Test
    public void testUpdateInventory() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new SalableProduct("Product1", "Description1", 10, 5), 2);
        cart.addProduct(new SalableProduct("Product2", "Description2", 15, 3), 1);

        store.updateInventory(cart);

        InventoryManager inventoryManager = store.getInventoryManager();
        Map<String, SalableProduct> inventory = inventoryManager.getInventory();

        assertEquals(7, inventory.get("Product1").getQuantity());
        assertEquals(2, inventory.get("Product2").getQuantity());
    }

    /**
     * Test the processSale method of StoreFront.
     * Adds products to the inventory, creates a shopping cart with products,
     * and processes the sale. Asserts that the inventory quantities are updated correctly.
     */
    @Test
    public void testProcessSale() {
        InventoryManager inventoryManager = store.getInventoryManager();
        SalableProduct product1 = new SalableProduct("Product1", "Description1", 10, 5);
        SalableProduct product2 = new SalableProduct("Product2", "Description2", 15, 3);
        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(product1, 2);
        cart.addProduct(product2, 1);

        inventoryManager.processSale(cart);

        assertEquals(3, inventoryManager.getInventory().get("Product1").getQuantity());
        assertEquals(1, inventoryManager.getInventory().get("Product2").getQuantity());
    }

    /**
     * Test the processCancel method of StoreFront.
     * Adds products to the inventory, increases their quantities,
     * creates a shopping cart with products, updates the inventory,
     * and cancels the transaction. Asserts that the inventory quantities are updated correctly.
     */
    @Test
    public void testProcessCancel() {
        InventoryManager inventoryManager = store.getInventoryManager();

        SalableProduct product1 = new SalableProduct("Product1", "Description1", 10, 5);
        SalableProduct product2 = new SalableProduct("Product2", "Description2", 15, 3);

        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);

        inventoryManager.increaseQuantity(product1, 3);
        inventoryManager.increaseQuantity(product2, 1);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(product1, 2);
        cart.addProduct(product2, 1);

        inventoryManager.updateInventory(cart);

        assertEquals(5, inventoryManager.getInventory().get(product1.getName()).getQuantity());
        assertEquals(2, inventoryManager.getInventory().get(product2.getName()).getQuantity());
    }
}
