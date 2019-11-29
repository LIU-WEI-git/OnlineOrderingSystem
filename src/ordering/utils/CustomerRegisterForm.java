package ordering.utils;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * 顾客注册表单类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/29 15:38
 */
public class CustomerRegisterForm {

    @NotBlank(message = "账号不能为空")
    @Size(min = 4, max = 20, message = "账号长度必须在4到20之间")
    private String customer_account;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度必须在4到20之间")
    private String customer_name;

    @NotBlank(message = "密码不能为空")
    private String customer_password;

    @NotBlank(message = "确认密码不能为空")
    private String confirm_password;

    @NotBlank(message = "电子邮箱不能为空")
    @Email(message = "请输入正确的电子邮箱地址")
    private String customer_email;

    /**
     * 无参构造函数
     */
    public CustomerRegisterForm() {
    }

    /**
     * 构造函数
     *
     * @param customer_account
     * @param customer_name
     * @param customer_password
     * @param confirm_password
     * @param customer_email
     */
    public CustomerRegisterForm(String customer_account, String customer_name, String customer_password, String confirm_password, String customer_email) {
        this.customer_account = customer_account;
        this.customer_name = customer_name;
        this.customer_password = customer_password;
        this.confirm_password = confirm_password;
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

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
}
