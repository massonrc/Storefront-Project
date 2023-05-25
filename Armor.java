import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Armor class represents a type of salable product that provides defense to the player.
 * It extends the SalableProduct class and adds an integer field to hold the defense value of the armor.
 * @author rargueta
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Armor extends SalableProduct {
	private int defense;
	
	public Armor() {
		
	}
	
	/**
	 * Constructs a new Armor object with the given name, description, price, quantity, and defense value.
	 * @param name the name of the armor
	 * @param description a description of the armor
	 * @param price the price of the armor
	 *@param quantity the quantity of the armor in inventory
	 *@param defense the defense value provided by the armor
	 */
	public Armor(String name, String description, int price, int quantity, int defense) {
	    super(name, description, price, quantity);
	    this.defense = defense;
	}

	/**
	 * Returns the defense value provided by the armor.
	 *@return the defense value of the armor
	 */
	public int getDefense() {
	    return defense;
	}

	/**
	 * Sets the defense value provided by the armor.
	 *@param defense the new defense value of the armor
	 */
	public void setDefense(int defense) {
	    this.defense = defense;
	}
}
