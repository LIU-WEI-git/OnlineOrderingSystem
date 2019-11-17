package ordering.domain;

/**
 * OrderDish实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 21:15
 */
public class OrderItem {

    private String order_id;
    private String dish_id;
    private int amount;

    /**
     * 无参构造函数
     */
    public OrderItem() {
    }

    /**
     * 构造函数
     *
     * @param order_id
     * @param dish_id
     * @param amount
     */
    public OrderItem(String order_id, String dish_id, int amount) {
        this.order_id = order_id;
        this.dish_id = dish_id;
        this.amount = amount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDish_id() {
        return dish_id;
    }

    public void setDish_id(String dish_id) {
        this.dish_id = dish_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
