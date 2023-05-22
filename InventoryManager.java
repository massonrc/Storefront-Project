import java.util.HashMap;
import java.util.Map;

/**
 * The InventoryManager class represents a collection of salable products
 * available for purchase in a store.
 * @author rargueta
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
	        initializeInventory();
	 }
	 
	 /**
	  * Initializes the inventory with some predefined products.
	  * This method adds several products to the inventory, including weapons, armor, and health items.
	  * The added products are:
	  * - Sword: A long, two-handed blade that deals a fair amount of damage.
	  * - Axe: A one-handed double-bladed weapon that deals a fair amount of damage.
	  * - Chainmail: A protective layer of armor that goes over the chest.
	  * - Boots: A protective layer of armor that covers the feet and shins.
	  * - Ginseng: A root rich in antioxidants that boosts health.
	  *
	  * The products are added to the inventory using the addProduct() method.
	  * After calling this method, the inventory will contain the initialized products.
	  */
	 
	 private void initializeInventory() {
	        Weapon sword = new Weapon("Sword", "A long, two-handed blade that deals a fair amount of damage.", 100, 2, 20);
	        Weapon axe = new Weapon("Axe", "A one-handed double bladed weapon that deals a fair amount of damage.", 150, 1, 35);
	        Armor chainmail = new Armor("Chainmail", "A protective layer of armor that goes over the chest.", 250, 3, 50);
	        Armor boots = new Armor("Boots", "A protective layer of armor that covers the feet and shins.", 100, 5, 25);
	        Health ginseng = new Health("Ginseng", "A root rich in antioxidants that boosts health.", 50, 25, 15);

	        addProduct(sword);
	        addProduct(axe);
	        addProduct(chainmail);
	        addProduct(boots);
	        addProduct(ginseng);
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
	 public void reduceQuantity(SalableProduct product, int quantity) {
	        String productName = product.getName();
	        if (inventory.containsKey(productName)) {
	            SalableProduct storedProduct = inventory.get(productName);
	            int currentQuantity = storedProduct.getQuantity();
	            if (currentQuantity <= quantity) {
	                inventory.remove(productName);
	            } 
	            else {
	                storedProduct.setQuantity(currentQuantity - quantity);
	            }
	        }
	    }
	 
	 /**
	  * This method processes a sale by reducing the quantity of salable products in the inventory based on the
	  * contents of a given shopping cart.
	  * @param cart the shopping cart containing the products to be sold
	  */
	 
	 public void processSale(ShoppingCart cart) {
		    Map<SalableProduct, Integer> products = cart.getProducts();
		    for (Map.Entry<SalableProduct, Integer> entry : products.entrySet()) {
		        SalableProduct product = entry.getKey();
		        int quantity = entry.getValue();
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
		    if (inventory.containsKey(product.getName())) {
		        SalableProduct storedProduct = inventory.get(product.getName());
		        int currentQuantity = storedProduct.getQuantity();
		        storedProduct.setQuantity(currentQuantity + amount);
		    } else {
		        product.setQuantity(amount);
		        inventory.put(product.getName(), product);
		    }
		}
	 
	  /**
	    * Removes a salable product from the store inventory based on its name.
	    *
	    * @param productName the name of the product to remove
	    */
	    public void removeProduct(String productName) {
	        inventory.remove(productName);
	    }

	    /**
	     * Adds a salable product to the store inventory.
	     *
	     * @param product the product to add to the inventory
	     */
	    public void addProduct(SalableProduct product) {
	        String productName = product.getName();
	        if (inventory.containsKey(productName)) {
	            SalableProduct storedProduct = inventory.get(productName);
	            storedProduct.setQuantity(storedProduct.getQuantity() + product.getQuantity());
	        } 
	        else {
	            inventory.put(productName, product);
	        }
	    }
}
