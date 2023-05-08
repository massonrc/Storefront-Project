import java.util.HashMap;
import java.util.Map;

/**
 * The InventoryManager class represents a collection of salable products
 * available for purchase in a store.
 */
public class InventoryManager {

    /** 
     * The list of salable products in the inventory. 
     */
	
	private Map<String, SalableProduct> inventory;

    /**
     * Constructs a new inventory manager with the given initial inventory.
     * @param initialInventory a map of salable products and their quantities representing the initial inventory
     */
     
    
	 public InventoryManager(Map<SalableProduct, Integer> initialInventory) {
	        inventory = new HashMap<>();
	        for (SalableProduct product : initialInventory.keySet()) {
	            int quantity = initialInventory.get(product);
	            inventory.put(product.getName(), new SalableProduct(product.getName(), product.getDescription(), product.getPrice(), quantity));
	        }
	 }

	 /**
	  * Returns the inventory map.
	  *
	  * @return the inventory map
	  */
	  
	 public Map<String, SalableProduct> getInventory() {
	        return inventory;
	 }

	 /**
	  * Updates the inventory with the given map of salable products and their quantities.
	  *
	  * @param updatedInventory a map of salable products and their quantities representing the updated inventory
	  */
	   
	 public void updateInventory(ShoppingCart cart) {
		    Map<SalableProduct, Integer> products = cart.getProducts();
		    for (SalableProduct product : products.keySet()) {
		        int quantity = products.get(product);
		        increaseQuantity(product, quantity);
		    }
		}
	    
	/**
	 * Reduces the quantity of the specified salable product in the inventory by the given amount.
	 *
	 * @param product the salable product to reduce the quantity of
	 * @param amount the amount to reduce the quantity by
	 */
	 public void reduceQuantity(SalableProduct product, int amount) {
	        String productName = product.getName();
	        if (inventory.containsKey(productName)) {
	            SalableProduct storedProduct = inventory.get(productName);
	        }    
	 }
	 
	 /**
	  * This method processes a sale by reducing the quantity of salable products in the inventory based on the
	  * contents of a given shopping cart.
	  * @param cart the shopping cart containing the products to be sold
	  */
	 
	 public void processSale(ShoppingCart cart) {
	        Map<SalableProduct, Integer> products = cart.getProducts();
	        for (SalableProduct product : products.keySet()) {
	            int quantity = products.get(product);
	            reduceQuantity(product, quantity);
	        }
	    }
	 
	 /**
	  * Increases the quantity of the specified salable product in the inventory by the given amount.
	  *
	  * @param product the salable product to increase the quantity of
	  * @param amount the amount to increase the quantity by
	  */
	  public void increaseQuantity(SalableProduct product, int amount) {
	      String productName = product.getName();
	      if (inventory.containsKey(productName)) {
	           SalableProduct storedProduct = inventory.get(productName);
	           storedProduct.setQuantity(storedProduct.getQuantity() + amount);
	      } 
	   }	        
}
