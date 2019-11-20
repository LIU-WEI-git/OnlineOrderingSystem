package ordering.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Customer实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 20:53
 */
public class Customer {

    @NotNull(message = "账号不能为空")
    private String customer_account;

    @NotNull(message = "用户名不能为空")
    private String customer_name;

    @NotNull(message = "密码不能为空")
    private String customer_password;

    private Timestamp customer_register_time;

    @NotNull(message = "电子邮箱不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$", message = "请输入正确的电子邮箱地址")
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
    public Customer(String customer_account, String customer_name, String customer_password, Timestamp customer_register_time, String customer_email) {
        this.customer_account = customer_account;
        this.customer_name = customer_name;
        this.customer_password = customer_password;
        this.customer_register_time = customer_register_time;
        this.customer_email = customer_email;
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

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    public Timestamp getCustomer_register_time() {
        return customer_register_time;
    }

    public void setCustomer_register_time(Timestamp customer_register_time) {
        this.customer_register_time = customer_register_time;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
}
