package ordering.domain;

/**
 * OrderAddressInfo视图类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/19 11:33
 */
public class OrderAddressInfoView {
    private String order_id;
    private String customer_account;
    private String customer_name;
    private String contact;
    private String phone;
    private String info;

    /**
     * 无参构造函数
     */
    public OrderAddressInfoView() {
    }

    /**
     * 构造函数
     *
     * @param order_id
     * @param customer_account
     * @param customer_name
     * @param contact
     * @param phone
     * @param info
     */
    public OrderAddressInfoView(String order_id, String customer_account, String customer_name, String contact, String phone, String info) {
        this.order_id = order_id;
        this.customer_account = customer_account;
        this.customer_name = customer_name;
        this.contact = contact;
        this.phone = phone;
        this.info = info;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_account() {
        return customer_account;
    }

    public void setCustomer_account(String customer_account) {
        this.customer_account = customer_account;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
