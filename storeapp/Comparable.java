package storeapp;

public interface Comparable <Weapon>{
	/**
     * Compares this weapon with another weapon for order.
     * Returns a negative integer, zero, or a positive integer
     * as this weapon is less than, equal to, or greater than
     * the specified weapon.
     *
     * @param other the weapon to be compared
     * @return a negative integer, zero, or a positive integer
     *         as this weapon is less than, equal to, or greater than the specified weapon
     */
    int compareTo(Weapon other);
}
