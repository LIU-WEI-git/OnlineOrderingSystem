package ordering.domain;

import java.util.Date;

/**
 * Order实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 21:18
 */
public class Order {

    private String order_id;
    private Customer customer;
    private Address address;
    private Date create_time;
    private String remark;
    private int order_state;
    private int delivery_state;
    private float discount;
    private float order_price;

    public static final int ORDER_UNPROCESSED = 0;
    public static final int ORDER_RECEIVED = 1;
    public static final int ORDER_COMPLETED = 2;
    public static final int UNDELIVERED = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;

    /**
     * 无参构造函数
     */
    public Order() {
    }

    /**
     * 构造函数，默认订单状态为未接单，默认配送状态为未配送
     *
     * @param order_id
     * @param customer
     * @param address
     * @param create_time
     * @param remark
     * @param discount
     * @param order_price
     */
    public Order(String order_id, Customer customer, Address address, Date create_time, String remark, float discount, float order_price) {
        this.order_id = order_id;
        this.customer = customer;
        this.address = address;
        this.create_time = create_time;
        this.remark = remark;
        this.discount = discount;
        this.order_price = order_price;
        this.order_state=ORDER_UNPROCESSED;
        this.delivery_state=UNDELIVERED;
    }

    /**
     * 构造函数
     *
     * @param order_id
     * @param customer
     * @param address
     * @param create_time
     * @param remark
     * @param order_state
     * @param delivery_state
     * @param discount
     * @param order_price
     */
    public Order(String order_id,Customer customer, Address address, Date create_time, String remark, int order_state, int delivery_state, float discount, float order_price) {
        this.order_id = order_id;
        this.customer = customer;
        this.address = address;
        this.create_time = create_time;
        this.remark = remark;
        this.order_state = order_state;
        this.delivery_state = delivery_state;
        this.discount = discount;
        this.order_price = order_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOrder_state() {
        return order_state;
    }

    public void setOrder_state(int order_state) {
        this.order_state = order_state;
    }

    public int getDelivery_state() {
        return delivery_state;
    }

    public void setDelivery_state(int delivery_state) {
        this.delivery_state = delivery_state;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }
}
