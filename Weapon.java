/**
 * The Weapon class represents a salable product that is a type of weapon, with a name, description, price, 
 * quantity, and damage value.
 * @author rargueta
 *
 */
public class Weapon extends SalableProduct {
	private int damage;
	
	/**
	 * Creates a new Weapon object with the specified name, description, price, quantity, and damage value.
	 * @param name the name of the weapon
	 * @param description a brief description of the weapon
	 * @param price the price of the weapon
	 * @param quantity the quantity of the weapon
	 * @param damage the damage value of the weapon
	 */
	public Weapon(String name, String description, int price, int quantity, int damage) {
	    super(name, description, price, quantity);
	    this.damage = damage;
	}
	
	/**
	 * Returns the damage value of the weapon.
	 * @return the damage value of the weapon
	 */
	public int getDamage() {
	    return damage;
	}
	
	/**
	 * Sets the damage value of the weapon to the specified value.
	 * @param damage the new damage value of the weapon
	 */
	public void setDamage(int damage) {
	    this.damage = damage;
	}
}
