package by.kraskovski.pms.domain.service;

import by.kraskovski.pms.domain.model.Cart;

/**
 * Service for {@link Cart}
 */
public interface CartService extends AbstractService<Cart> {

    /**
     * Save {@link Cart} entity to database table
     */
    void create(String id);

    /**
     * Add product to cart.
     */
    void addProduct(String cartId, String productStockId, int count);

    /**
     * Delete one (or more) product(s) from cart.
     */
    void deleteProduct(String cartId, String productStockId, int count);

    /**
     * Update information about {@link Cart} in database
     */
    Cart update(Cart object);

    @Override
    default Cart create(Cart object) {
        return new Cart();
    }
}
