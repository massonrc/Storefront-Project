package storeapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


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
	  * Constructs a new inventory manager and initializes the inventory by loading the data from the specified inventory file.
	  * @param inventoryFilePath the path to the inventory file
	  */
	 public InventoryManager(String fileName) {
		 inventory = new HashMap<>();
		 loadInventoryFromFile(fileName);
		 
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
	    
	    /**
	     * Loads the inventory data from the specified inventory file and populates the inventory map.
	     * Throws IO Exception if error in finding file and will terminate program.
	     * @param inventoryFilePath the path to the inventory file
	     */
	    public void loadInventoryFromFile(String fileName) {
	        try {
	            File inventoryFile = new File(fileName);
	            ObjectMapper mapper = new ObjectMapper();
	            Map<String, SalableProduct> inventoryMap = mapper.readValue(inventoryFile, new TypeReference<Map<String, SalableProduct>>() {});
	            inventory = new LinkedHashMap<>(inventoryMap);
	            System.out.println("Inventory loaded successfully from file: " + fileName + "\n");
	        } 
	        catch (IOException e) {
	            System.out.println("Failed to load inventory from file: " + e.getMessage());
	            System.exit(0);
	        }
	    }

}
