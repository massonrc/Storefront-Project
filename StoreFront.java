import java.util.*;

/**
 * The StoreFront class represents a store with an inventory of salable products
 * available for purchase.
 * @author rargueta
 */
public class StoreFront {
	private InventoryManager inventoryManager;
	private ShoppingCart cart;


	/**
	 * Constructs a new StoreFront object with an empty inventory.
	 */

	public StoreFront() {
		this.inventoryManager = new InventoryManager(new HashMap<>());
		this.cart = new ShoppingCart();
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
        System.out.println("2. Add items to shopping cart");
        System.out.println("3. Process sale");
        System.out.println("4. Cancel sale");
        System.out.println("5. Quit");
    }

	/**
	 * Main method for testing the StoreFront class and its functionality.
	 */
	
	public static void main(String[] args) {	
		// We create a new store with an established inventory, as well as an empty shopping cart for the user
		StoreFront store = new StoreFront();
		Scanner scanner = new Scanner(System.in);
	    boolean done = false;
	    ShoppingCart cart = new ShoppingCart();
		
		//Welcome message for the store
		System.out.println("Welcome to the FWA Store! We sell items to prepare you for your arena fight!\n");
		
		// Loop StoreFront options until the user chooses to quit
	    while (!done) {
	        store.displayActions();
	        String input = scanner.next();
	        switch (input) {
	            case "1":
	            	//Display the current inventory
	            	Map<String, SalableProduct> initialInventory = store.getInventoryManager().getInventory();

	                System.out.println("\nHere's what we've got: \n");

	                List<Weapon> weaponList = new ArrayList<>();
	                List<SalableProduct> otherProducts = new ArrayList<>();

	                for (SalableProduct product : initialInventory.values()) {
	                    if (product instanceof Weapon) {
	                        weaponList.add((Weapon) product);
	                    } else {
	                        otherProducts.add(product);
	                    }
	                }

	                weaponList.sort(Comparator.comparing(Weapon::getName));

	                System.out.println("Weapons:\n");
	                for (Weapon weapon : weaponList) {
	                    System.out.println(weapon.getName() + ": " + weapon.getDescription() + " It costs: $" + weapon.getPrice() + ". We currently have in stock: " + weapon.getQuantity() + "\n");
	                }

	                System.out.println("Other Products:\n");
	                for (SalableProduct product : otherProducts) {
	                    System.out.println(product.getName() + ": " + product.getDescription() + " It costs: $" + product.getPrice() + ". We currently have in stock: " + product.getQuantity() + "\n");
	                }
	                break;
	                
	            case "2":
	            	// Allow user to add product to the shopping cart
	            	 boolean validInput = false;
	            	 while(!validInput) {
 		         		 
	            		 System.out.println("\nHere's what we've got: \n");
	            	    	Map<String, SalableProduct> inventory = store.getInventoryManager().getInventory();
	            	    	for (SalableProduct product : inventory.values()) {
	            	    		System.out.println(product.getName() + ": " + product.getDescription() +
	            	                ", it costs: $" + product.getPrice() + ". We currently have in stock: " +
	            	                product.getQuantity() + "\n");
	            	    	}
	            	    	
	            	    	System.out.println("Please enter the name of the product you want to add to your shopping cart:");
	            	    	String productName = scanner.next().toLowerCase(); // Convert input to lowercase for case-insensitive comparison
	            	    	System.out.println("Please enter the quantity of " + productName + " you want to add:");
	            	    	int quantity = scanner.nextInt();

	            	    	SalableProduct product = null;
	            	    	for (SalableProduct p : inventory.values()) {
	            	    		if (p.getName().toLowerCase().equals(productName)) { // Compare lowercase product names
	            	    			product = p;
	            	    			break;
	            	    		}
	            	    	}
	            	    	if (product != null) {
	            	    		int availableQuantity = product.getQuantity();
	            	    		if (quantity > availableQuantity) {
	            	    			System.out.println("Sorry, we don't have that many. We currently have: " + availableQuantity + " " +
	            	                    product.getName() + "(s) available.\n");
	            	    		} 
	            	    		else {
	            	    			cart.addProduct(product, quantity);
	            	    			System.out.println(quantity + " " + product.getName() + "(s) added to your shopping cart.\n");            	        
	            	            	validInput = true;
	            	            	
	            	    		}
	            	    	} 
	            	    	else {
	            	    		System.out.println("Sorry, we don't have " + productName + " in our inventory.\n");
	            	    	}
	            	    	
	            	    	// Reduce the quantity of products in the inventory
	            	        Map<SalableProduct, Integer> lessInventory = cart.getProducts();
	            	        for (SalableProduct prods : lessInventory.keySet()) {
	            	            int newQuantity = lessInventory.get(product);
	            	            store.getInventoryManager().reduceQuantity(product, quantity);
	            	        }                  
	            	    }
	            	   break;
	            	    
	            case "3":
	            	// Display the contents of the shopping cart and calculate the grand total
	                System.out.println("\nYour shopping cart has:");
	                Map<SalableProduct, Integer> cartContents = cart.getProducts();
	                int totalPrice = 0;
	                for (Map.Entry<SalableProduct, Integer> entry : cartContents.entrySet()) {
	                    SalableProduct product = entry.getKey();
	                    int quantity = entry.getValue();
	                    int price = product.getPrice() * quantity;
	                    System.out.println(product.getName() + ": " + quantity);
	                    totalPrice += price;
	                }
	                if (totalPrice == 0) {
	                    System.out.println("Your shopping cart is empty.");
	                } 
	                else {
	                    /* Provide user with the option to proceed with the sale or not
	                     * Display an error message if the user enters an incorrect prompt
	                     */
	                    System.out.println("Your total price is: $" + totalPrice);
	                    System.out.println("Proceed with sale? (Press Y for yes; N for no)");
	                    String userInput = scanner.next().toUpperCase();
	                    while (true) {
	                        if (userInput.equals("Y")) {
	                            store.processSale(cart);
	                            System.out.println("Thank you for your purchase!\n");
	                            cart.clear();
	                            break;
	                        }
	                        if (userInput.equals("N")) {
	                            break;
	                        } 
	                        else {
	                            System.out.println("Invalid input! Please enter Y/N if you would like to proceed with the sale:");
	                            userInput = scanner.next().toUpperCase();
	                        }
	                    }
	                }
	                break;
	            
	            case "4":
	            	//Gives the user the choice to clear shopping cart contents or to remove singular items
	            	System.out.println("What would you like to do?");
	                System.out.println("1. Clear shopping cart");
	                System.out.println("2. Remove a single item");
	                System.out.println("3. Return to main menu");
	                
	                String cartOption = scanner.next();
	                
	                switch (cartOption) {
	                    case "1":
	                        cart.clear();
	                        System.out.println("Your shopping cart has been cleared.\n");
	                        break;
	                    case "2":
	                        System.out.println("Enter the name of the product to remove:");
	                        String productName = scanner.next().toLowerCase();

	                        // Check if the product is in the cart
	                        SalableProduct productToRemove = null;

	                        for (SalableProduct product : cart.getProducts().keySet()) {
	                            if (product.getName().toLowerCase().equals(productName)) {
	                                productToRemove = product;
	                                break;
	                            }
	                        }
	                        if (productToRemove != null) {
	                            cart.removeProduct(productToRemove, 1); // Remove only one quantity of the product
	                            System.out.println("Removed 1 " + productToRemove.getName() + " from your shopping cart.\n");
	                            store.getInventoryManager().increaseQuantity(productToRemove, 1); // Increase the quantity in the store's inventory
	                        } else {
	                            System.out.println("The product is not in your shopping cart.\n");
	                        }
	                        break;
	                    case "3":
	                        break;
	                    default:
	                        System.out.println("Invalid option. Please choose a valid option.");
	                        break;
	                }
	                break;
	                
	            case "5":
	            	//Closes store menu and exits program
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