package ordering.domain;

/**
 * Address实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 21:00
 */
public class Address {

    private String address_id;
    private String customer_account;
    private String contact;
    private String phone;
    private String info;
    private int delete_tag;

    public static int ADDRESS_UNDELETED = 0;
    public static int ADDRESS_DELETED = 1;

    /**
     * 无参构造函数
     */
    public Address() {
    }

    /**
     * 构造函数，默认删除状态为为删除
     *
     * @param address_id
     * @param customer_account
     * @param contact
     * @param phone
     * @param info
     */
    public Address(String address_id, String customer_account, String contact, String phone, String info) {
        this.address_id = address_id;
        this.customer_account = customer_account;
        this.contact = contact;
        this.phone = phone;
        this.info = info;
        this.delete_tag = ADDRESS_UNDELETED;
    }

    /**
     * 构造函数
     *
     * @param address_id
     * @param customer_account
     * @param contact
     * @param phone
     * @param info
     * @param delete_tag
     */
    public Address(String address_id, String customer_account, String contact, String phone, String info,int delete_tag) {
        this.address_id = address_id;
        this.customer_account = customer_account;
        this.contact = contact;
        this.phone = phone;
        this.info = info;
        this.delete_tag=delete_tag;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getCustomer_account() {
        return customer_account;
    }

    public void setCustomer_account(String customer_account) {
        this.customer_account = customer_account;
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

    public int getDelete_tag() {
        return delete_tag;
    }

    public void setDelete_tag(int delete_tag) {
        this.delete_tag = delete_tag;
    }
}
