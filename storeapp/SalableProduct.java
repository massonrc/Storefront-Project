package storeapp;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
*A class representing a salable product.
*@author rargueta
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalableProduct {

	private String name;
	private String description;
	private int price;
	private int quantity;
	
	/**
	 * Default constructor 
	 */
	public SalableProduct() {
		
	}

	/**
	 * Constructs a new SalableProduct with the given attributes.
	 * 
	 * @param name the name of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param quantity the quantity of the product
	 */

	public SalableProduct(String name, String description, int price, int quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * Returns the name of the product.
	 * @return the name of the product
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of the product.
	 * @return the description of the product
	 */
	
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the price of the product.
	 * 
	 * @return the price of the product
	 */

	public int getPrice() {
		return price;
	}

	/**
	 * Returns the quantity of the product.
	 * 
	 * @return the quantity of the product
	 */
	
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Updates the quantity of the product.
	 * 
	 * @param quantity the new quantity of the product
	 */

	public void setQuantity(int quantity) {
		if (quantity < 0) {
	        throw new IllegalArgumentException("Quantity cannot be negative");
	    }
		this.quantity = quantity;
	}
}