import java.util.HashMap;

/**
 * This class represents a shopping cart that stores a list of salable products
 * chosen for purchase by the user.
 * @author rargueta
 */
public class ShoppingCart {

    /**
     * The map of products in the shopping cart.
     */

    private HashMap<SalableProduct, Integer> mapOfProducts;

    /**
     * Constructs a new ShoppingCart object with an empty list of products.
     */

    public ShoppingCart() {
        mapOfProducts = new HashMap<>();
    }

    /**
     * Adds the specified salable product with the given quantity to the shopping cart.
     *
     * @param product the salable product to add
     * @param quantity the quantity of the salable product to add
     */

    public void addProduct(SalableProduct product, int quantity) {
        if (mapOfProducts.containsKey(product)) {
            int currentQuantity = mapOfProducts.get(product);
            mapOfProducts.put(product, currentQuantity + quantity);
        } else {
            mapOfProducts.put(product, quantity);
        }
    }

    /**
     * Removes the specified salable product with the given quantity from the shopping cart.
     *
     * @param product the salable product to remove
     * @param quantity the quantity of the salable product to remove
     */

    public void removeProduct(SalableProduct product, int quantity) {
        if (mapOfProducts.containsKey(product)) {
            int currentQuantity = mapOfProducts.get(product);
            if (currentQuantity <= quantity) {
                mapOfProducts.remove(product);
            } else {
                mapOfProducts.put(product, currentQuantity - quantity);
            }
        }
    }
   
    /**
     * Returns the map of salable products and their quantities in the shopping cart.
     *
     * @return the map of salable products and their quantities in the shopping cart
     */

    public HashMap<SalableProduct, Integer> getProducts() {
        return mapOfProducts;
    }
    
    /**
     * Removes all products from the shopping cart.
     */
    public void clear() {
    mapOfProducts.clear();
    }
}