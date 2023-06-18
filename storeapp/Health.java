package storeapp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Health class represents a type of SalableProduct that can restore the player's health.
 * It extends the abstract class SalableProduct and has an additional field of healthAmount.
 * @author rargueta
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Health extends SalableProduct {
	private int healthAmount;
	
	public Health() {
		
	}

	/**
	 * Constructs a new Health object with a name, description, price, quantity, and healingAmount.
	 *@param name the name of the health item
	 *@param description a description of the health item
	 *@param price the price of the health item
	 *@param quantity the initial quantity of the health item
	 *@param healthAmount the amount of health the item can restore
	 */
	public Health(String name, String description, int price, int quantity, int healthAmount) {
	    super(name, description, price, quantity);
	    this.healthAmount = healthAmount;
	}
	
	/**
	 * Returns the amount of health that this health item can restore.
	 *@return the amount of health that this health item can restore
	 */
	public int getHealthAmount() {
	    return healthAmount;
	}

	/**
	 * Sets the amount of health that this health item can restore.
	 *@param healingAmount the amount of health that this health item can restore
	 */
	public void setHealthAmount(int healthAmount) {
	    this.healthAmount = healthAmount;
	}
}
