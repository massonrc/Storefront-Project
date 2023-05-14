import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;


/**
 * The StoreFront class represents a store with an inventory of salable products
 * available for purchase.
 * @author rargueta
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
     * Displays a list of actions for the user to interact with the store front.
     */
    public void displayActions() {
    	
    	System.out.println("What would you like to do?");
        System.out.println("1. View inventory");
        System.out.println("2. Process sale");
        System.out.println("3. Cancel sale");
        System.out.println("4. Quit");
    }

	/**
	 * Main method for testing the StoreFront class and its functionality.
	 */
	
	public static void main(String[] args) {	
		// We create a new store with an empty inventory, as well as a new shopping cart for the user
		StoreFront store = new StoreFront();
		Scanner scanner = new Scanner(System.in);
	    boolean done = false;
	    ShoppingCart cart = new ShoppingCart();
	    
		// Add some products to the inventory 
		Weapon sword = new Weapon("Sword", "A long, two-handed blade that deals a fair amount of damage.", 100, 2, 20);
		Weapon axe = new Weapon("Axe", "A one-handed double bladed weapon that deals a fair amount of damage.", 150, 1, 35);
		Armor chainmail = new Armor("Chainmail", "A protective layer of armor that goes over the chest.", 250, 3, 50);
		Armor boots = new Armor("Boots", "A protective layer of armor that covers the feet and shins.", 100, 5, 25);
		Health ginseng = new Health("Ginseng", "A root rich in antioxidants that boosts health.", 50, 25, 15);
		
		//Welcome message for the store
		System.out.println("Welcome to the FWA Store! We sell items to prepare you for your arena fight!\n");
		
		// Loop StoreFront options until the user chooses to quit
	    while (!done) {
	        store.displayActions();
	        String input = scanner.next();
	        switch (input) {
	            case "1":
	            	//Display the current inventory
	        		Map<SalableProduct, Integer> initialInventory = new HashMap<>();
	        		initialInventory.put(sword, sword.getQuantity());
	        		initialInventory.put(axe, axe.getQuantity());
	        		initialInventory.put(chainmail, chainmail.getQuantity());
	        		initialInventory.put(boots, boots.getQuantity());
	        		initialInventory.put(ginseng, ginseng.getQuantity());
	        		System.out.println("\nHere's what we've got: \n");
	        		for (SalableProduct product : initialInventory.keySet()) {    	        		
	        			System.out.println(product.getName() + ": " + product.getDescription() + ", it costs: $" + product.getPrice() + ". We currently have in stock: "+ initialInventory.get(product) + "\n");
	        		} 
	                break;
	            case "2":
	            	// Add some products to the shopping cart
	                cart.addProduct(axe, 2);
	                cart.addProduct(ginseng, 3);
	                cart.addProduct(chainmail, 1);

	                // Display the contents of the shopping cart and calculates the grand total
	                System.out.println("\nYour shopping cart has:");
	                Map<SalableProduct, Integer> cartContents = cart.getProducts();
	                int totalPrice = 0;
	                for (SalableProduct product : cartContents.keySet()) {
	                    int quantity = cartContents.get(product);
	                    int price = product.getPrice() * quantity;
	                    System.out.println(product.getName() + ": " + quantity);
	                    totalPrice += price;
	                }
	                /*Provides user with total an option to proceed with sale or not
	                 * displays an error message if user enters incorrect prompt
	                 */
	                System.out.println("Your total price is: $" + totalPrice);
	                System.out.println("Proceed with sale? (Press Y for yes; N for no)");
	                input = scanner.next().toUpperCase();
	                while(true) {                	
	                    if(input.equals("Y")) {
	                        store.processSale(cart);
	                        System.out.println("Thank you for your purchase!\n");
	                        break; // exit the while loop after sale is processed
	                    }
	                    if(input.equals("N")) {
	                        break;
	                    }
	                    else {
	                        System.out.println("Invalid input! Please enter Y/N if you would like to proceed with sale:");
	                        input = scanner.next().toUpperCase();
	                    }                    
	                }
	                break;
	            case "3":
	            	//Cancels sale if user selects this option
	            	System.out.println("\nYour sale has been canceled. Thanks for shopping with us!\n");
	                cart.clear();
	                break;
	            case "4":
	            	//Closes store menu
	                done = true;
	                System.out.println("Thanks for stopping by! Goodbye!");
	                System.exit(0);
	            default:
	                System.out.println("Invalid input. Please choose from the following:");
	                break;
	        }
	    }
	}
}