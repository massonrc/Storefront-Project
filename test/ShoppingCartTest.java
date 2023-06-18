package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import storeapp.ShoppingCart;
import storeapp.SalableProduct;

/**
 * A JUnit test class for the ShoppingCart class
 * @author rargueta
 *
 */
public class ShoppingCartTest {

	/**

	Test case for the ShoppingCart constructor.
	*/
	@Test
	public void testShoppingCart() {
	// Create a new ShoppingCart object
	ShoppingCart cart = new ShoppingCart();

	// Verify that the cart object is not null
	assertNotNull(cart);
	// Verify that the initial map of products is empty
	assertTrue(cart.getProducts().isEmpty());
	}

	/**

	Test case for the addProduct(SalableProduct, int) method.
	*/
	@Test
	public void testAddProduct() {
	// Create a new ShoppingCart object
	ShoppingCart cart = new ShoppingCart();

	// Create a SalableProduct object
	SalableProduct product = new SalableProduct("Product", "Description", 10, 5);

	// Add the product to the cart with a quantity of 2
	cart.addProduct(product, 2);

	// Verify that the product is added to the cart with the correct quantity
	HashMap<SalableProduct, Integer> products = cart.getProducts();
	assertTrue(products.containsKey(product));
	assertEquals(2, (int) products.get(product));

	// Add the same product to the cart with a different quantity
	cart.addProduct(product, 3);

	// Verify that the quantity is updated correctly
	assertEquals(5, (int) products.get(product));
	}

	/**

	Test case for the removeProduct(SalableProduct, int) method.
	*/
	@Test
	public void testRemoveProduct() {
	// Create a new ShoppingCart object
	ShoppingCart cart = new ShoppingCart();

	// Create a SalableProduct object
	SalableProduct product = new SalableProduct("Product", "Description", 10, 5);

	// Add the product to the cart with a quantity of 5
	cart.addProduct(product, 5);

	// Remove the product from the cart with a quantity of 3
	cart.removeProduct(product, 3);

	// Verify that the quantity is updated correctly
	HashMap<SalableProduct, Integer> products = cart.getProducts();
	assertEquals(2, (int) products.get(product));

	// Remove the remaining quantity of the product from the cart
	cart.removeProduct(product, 2);

	// Verify that the product is removed from the cart
	assertFalse(products.containsKey(product));
	}

	/**

	Test case for the getProducts() method.
	*/
	@Test
	public void testGetProducts() {
	// Create a new ShoppingCart object
	ShoppingCart cart = new ShoppingCart();

	// Verify that the initial map of products is empty
	assertTrue(cart.getProducts().isEmpty());

	// Create some SalableProduct objects
	SalableProduct product1 = new SalableProduct("Product 1", "Description 1", 10, 5);
	SalableProduct product2 = new SalableProduct("Product 2", "Description 2", 20, 3);

	// Add the products to the cart with quantities
	cart.addProduct(product1, 2);
	cart.addProduct(product2, 4);

	// Verify that the map of products is returned correctly
	HashMap<SalableProduct, Integer> products = cart.getProducts();
	assertEquals(2, products.size());
	assertTrue(products.containsKey(product1));
	assertTrue(products.containsKey(product2));
	assertEquals(2, (int) products.get(product1));
	assertEquals(4, (int) products.get(product2));
	}
	
	/**

	Test case for the clear() method.
	*/
	@Test
	public void testClear() {
	// Create a new ShoppingCart object
	ShoppingCart cart = new ShoppingCart();

	// Add some products to the cart
	SalableProduct product1 = new SalableProduct("Product 1", "Description 1", 10, 5);
	SalableProduct product2 = new SalableProduct("Product 2", "Description 2", 20, 3);
	cart.addProduct(product1, 2);
	cart.addProduct(product2, 4);

	// Clear the cart
	cart.clear();

	// Verify that the cart is empty
	assertTrue(cart.getProducts().isEmpty());
	}
}
