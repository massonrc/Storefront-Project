import java.util.HashMap;
import java.util.Map;

/**
 * The StoreFront class represents a store with an inventory of salable products
 * available for purchase.
 */
public class StoreFront {
	private InventoryManager inventoryManager;

	/**
	 * Constructs a new StoreFront object with an empty inventory.
	 */

	public StoreFront() {
		this.inventoryManager = new InventoryManager(new HashMap<>());
	}

	/**
	 * Returns the inventory manager for this store.
	 * @return the inventory manager for this store
	 */
	
	public InventoryManager getInventoryManager() {
		return inventoryManager;
	}

	/**
	 * Updates the inventory with the given map of salable products and their quantities.
	 * @param updatedInventory a map of salable products and their quantities representing the updated inventory
	 */
	
	public void updateInventory(ShoppingCart cart) {
	    this.inventoryManager.updateInventory(cart);
	}
	
	/**
	 * Processes a sale by updating the store's inventory with the products and quantities in the given shopping cart.
	 * The quantity of each product in the inventory is reduced by the quantity of that product in the cart.
	 * @param cart the shopping cart containing the products and quantities to be sold
	 */
	
	public void processSale(ShoppingCart cart) {
	    Map<SalableProduct, Integer> products = cart.getProducts();
	    for (SalableProduct product : products.keySet()) {
	        int quantity = products.get(product);
	        inventoryManager.reduceQuantity(product, quantity);
	    }
	}
	
	/**
	 * Processes cancel of sale by updating the store's inventory with the products and quantities in the given shopping cart.
	 * The quantity of each product in the inventory is increased by the quantity of that product in the cart.
	 * @param cart the shopping cart containing the products and quantities to be returned
	 */
	
	public void processCancel(ShoppingCart cart) {
	    Map<SalableProduct, Integer> products = cart.getProducts();
	    for (SalableProduct product : products.keySet()) {
	        int quantity = products.get(product);
	        inventoryManager.increaseQuantity(product, quantity);
	    }
	}

	/**
	 * Main method for testing the StoreFront class and its functionality.
	 */
	
	public static void main(String[] args) {	
		// We create a new store with an empty inventory
		StoreFront store = new StoreFront();

		// Add some products to the inventory for testing purposes
		SalableProduct product1 = new SalableProduct("Product 1", "This is product 1", 10, 100);
		SalableProduct product2 = new SalableProduct("Product 2", "This is product 2", 20, 50);
		
		//Display the current inventory
		Map<SalableProduct, Integer> initialInventory = new HashMap<>();
		initialInventory.put(product1, product1.getQuantity());
		initialInventory.put(product2, product2.getQuantity());
		for (SalableProduct product : initialInventory.keySet()) {
		    System.out.println(product.getName() + ": " + product.getDescription() + ", it costs: $" + product.getPrice() + ". We currently have in stock: "+ initialInventory.get(product));
		}
		
		
		// Confirming current inventory
		Map<String, SalableProduct> inventory = store.getInventoryManager().getInventory();
		

		// Add some products to the shopping cart
		ShoppingCart cart = new ShoppingCart();
		cart.addProduct(product1, 2);
		cart.addProduct(product2, 3);

		// Display the contents of the shopping cart
		System.out.println("\nYour shopping cart has:");
		Map<SalableProduct, Integer> cartContents = cart.getProducts();
		for (SalableProduct product : cartContents.keySet()) {
			int quantity = cartContents.get(product);
			System.out.println(product.getName() + ": " + quantity);
		}
		
		// Purchase the contents of the shopping cart
		store.processSale(cart);

		// Display what's left in the inventory after the purchase
		System.out.println("\nInventory after purchase:");
		if (inventory.size() == 0) {
		    System.out.println("No products left in inventory.");
		} 
		else {
			for (SalableProduct product : inventory.values()) {
				int quantity = product.getQuantity();
				int purchasedQuantity = cart.getProducts().get(product);
				if (purchasedQuantity > 0) {
	            quantity -= purchasedQuantity;
				}
				System.out.println(product.getName() + ": " + quantity);
			}
		}
		
		// Cancel the purchase and return the products to the inventory
		store.getInventoryManager().updateInventory(cart);

		// Display what's left in the inventory after the return
		System.out.println("\nInventory after return:");
		inventory = store.getInventoryManager().getInventory();
		for (SalableProduct product : inventory.values()) {
		    System.out.println(product.getName() + ": " + product.getQuantity());
		}
	}
}