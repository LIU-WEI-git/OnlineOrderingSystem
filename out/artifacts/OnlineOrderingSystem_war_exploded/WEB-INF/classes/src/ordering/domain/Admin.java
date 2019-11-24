package ordering.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import java.util.Date;

/**
 * Admin实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 20:13
 */
public class Admin {

    @NotNull
    @Size(min=2, max=6, message = "{admin_account.size}")
    private String admin_account;

    @NotNull
    @Size(min=2, max=16,message = "{admin_name.size}")
    private String admin_name;

    @NotNull
    @Size(min=3, max=12,message = "{admin_password.size}")
    private String admin_password;


    private Date admin_register_time;

    @NotNull
    @Email
    private String admin_email;

    @NotNull
    @Size(min = 3, max = 15)
    private String admin_phone;
    private int delete_tag;

    public static final int UNDELETED = 0;
    public static final int DELETED = 1;

    /**
     * 无参构造方法
     */
    public Admin() {
    }

    /**
     * 构造方法，默认delete_tag为未删除
     *
     * @param admin_account
     * @param admin_name
     * @param admin_password
     * @param admin_register_time
     * @param admin_email
     * @param admin_phone
     */
    public Admin(String admin_account, String admin_name, String admin_password, Date admin_register_time, String admin_email, String admin_phone) {
        this.admin_account = admin_account;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
        this.admin_register_time = admin_register_time;
        this.admin_email = admin_email;
        this.admin_phone = admin_phone;
        this.delete_tag = UNDELETED;
    }

    /**
     * 构造方法
     *
     * @param admin_account
     * @param admin_name
     * @param admin_password
     * @param admin_register_time
     * @param admin_email
     * @param admin_phone
     * @param delete_tag
     */
    public Admin(String admin_account, String admin_name, String admin_password, Date admin_register_time, String admin_email, String admin_phone, int delete_tag) {
        this.admin_account = admin_account;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
        this.admin_register_time = admin_register_time;
        this.admin_email = admin_email;
        this.admin_phone = admin_phone;
        this.delete_tag = delete_tag;
    }

    public String getAdmin_account() {
        return admin_account;
    }

    public void setAdmin_account(String admin_account) {
        this.admin_account = admin_account;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public Date getAdmin_register_date() {
        return admin_register_time;
    }

    public void setAdmin_register_date(Date admin_register_date) {
        this.admin_register_time = admin_register_date;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public int isDelete_tag() {
        return delete_tag;
    }

    public void setDelete_tag(int delete_tag) {
        this.delete_tag = delete_tag;
    }

    public int getDelete_tag() {  return delete_tag;
    }
}
