package ordering.utils;

import ordering.domain.Dish;

/**
 * 购物车子项类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/22 17:11
 */
public class ShoppingCartItem {
    private Dish dish;
    private int amount;
    private float itemPrice;

    /**
     * 构造函数
     */
    public ShoppingCartItem() {
    }

    /**
     * 构造函数，默认新购物车子项数量为1
     *
     * @param dish
     */
    public ShoppingCartItem(Dish dish) {
        this.dish = dish;
        this.amount = 1;
        this.itemPrice = dish.getPrice();
    }

    /**
     * 构造函数
     *
     * @param dish
     * @param amount
     * @param itemPrice
     */
    public ShoppingCartItem(Dish dish, int amount, float itemPrice) {
        this.dish = dish;
        this.amount = amount;
        this.itemPrice = itemPrice;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object obj) {
        ShoppingCartItem shoppingCartItem = (ShoppingCartItem) obj;
        return this.dish.getDish_id().equals(shoppingCartItem.dish.getDish_id());
    }

    public void plusOne() {
        amount += 1;
        itemPrice += dish.getPrice();
    }

    public void minusOne() {
        amount -= 1;
        itemPrice -= dish.getPrice();
    }
}
