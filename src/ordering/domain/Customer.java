package ordering.domain;

import java.util.Date;

/**
 * Customer实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 20:53
 */
public class Customer {

    private String customer_account;
    private String customer_name;
    private String customer_password;
    private Date customer_register_time;
    private String customer_email;

    /**
     * 无参构造函数
     */
    public Customer() {
    }

    /**
     * 构造函数
     *
     * @param customer_account
     * @param customer_name
     * @param customer_password
     * @param customer_register_time
     * @param customer_email
     */
    public Customer(String customer_account, String customer_name, String customer_password, Date customer_register_time, String customer_email) {
        this.customer_account = customer_account;
        this.customer_name = customer_name;
        this.customer_password = customer_password;
        this.customer_register_time = customer_register_time;
        this.customer_email = customer_email;
    }

    public String getCustomer_account() {
        return customer_account;
    }

    public void setCustomer(String customer_account) {
        this.customer_account = customer_account;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    public Date getCustomer_register_time() {
        return customer_register_time;
    }

    public void setCustomer_register_time(Date customer_register_time) {
        this.customer_register_time = customer_register_time;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
}
