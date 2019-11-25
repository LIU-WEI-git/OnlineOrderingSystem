package ordering.utils;

import ordering.domain.Dish;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/22 17:01
 */
public class ShoppingCart {
    private List<ShoppingCartItem> shoppingCartItemList;
    private float totalPrice;

    /**
     * 构造函数
     */
    public ShoppingCart() {
        this.shoppingCartItemList = new ArrayList<>();
        this.totalPrice = 0;
    }

    /**
     * 构造函数
     *
     * @param shoppingCartItem
     * @param totalPrice
     */
    public ShoppingCart(List<ShoppingCartItem> shoppingCartItem, float totalPrice) {
        this.shoppingCartItemList = shoppingCartItem;
        this.totalPrice = totalPrice;
    }

    /**
     * 向购物车内增加一个子项或购物车中某一子项数量加一
     *
     * @param dish
     */
    public void addItemToShoppingCart(Dish dish) {
        //购物车内已有同名商品，则其数量加一
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            if (shoppingCartItem.getDish().getDish_id().equals(dish.getDish_id())) {
                shoppingCartItem.plusOne();
                totalPrice += dish.getPrice();
                return;
            }
        }
        //购物车内无同名商品，新建一个购物车子项放入购物车
        shoppingCartItemList.add(new ShoppingCartItem(dish));
        totalPrice += dish.getPrice();
    }

    /**
     * 删除一个购物车子项
     *
     * @param dish_id
     */
    public void deleteItemFromShoppingCart(String dish_id) {
        for (int i = 0; i < shoppingCartItemList.size(); i++) {
            ShoppingCartItem shoppingCartItem = shoppingCartItemList.get(i);
            if (shoppingCartItem.getDish().getDish_id().equals(dish_id)) {
                shoppingCartItemList.remove(i);
                totalPrice -= shoppingCartItem.getDish().getPrice();
                return;
            }
        }
    }

    /**
     * 购物车某一商品数量减一
     *
     * @param dish_id
     */
    public void minusItemFromShoppingCart(String dish_id) {
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            if (shoppingCartItem.getDish().getDish_id().equals(dish_id)) {
                if (shoppingCartItem.getAmount() > 1) {
                    shoppingCartItem.minusOne();
                    totalPrice -= shoppingCartItem.getDish().getPrice();
                }
            }
        }
    }

    public List<ShoppingCartItem> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
