package ordering.domain;

/**
 * OrderItemInfo视图类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/19 11:36
 */
public class OrderItemInfoView {
    private String order_id;
    private String dish_name;
    private String picture_url;
    private float price;
    private int amount;
    private int total_price;

    /**
     * 无参构造函数
     */
    public OrderItemInfoView() {
    }

    /**
     * 构造函数
     *
     * @param order_id
     * @param dish_name
     * @param picture_url
     * @param price
     * @param amount
     * @param total_price
     */
    public OrderItemInfoView(String order_id, String dish_name, String picture_url, float price, int amount, int total_price) {
        this.order_id = order_id;
        this.dish_name = dish_name;
        this.picture_url = picture_url;
        this.price = price;
        this.amount = amount;
        this.total_price = total_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
