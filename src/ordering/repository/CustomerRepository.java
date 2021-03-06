package ordering.repository;

import ordering.domain.Customer;

import java.sql.Timestamp;
import java.util.List;

/**
 * customer资源库接口
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/19 20:52
 */
public interface CustomerRepository {

    /**
     * 判断某customer是否在数据库中
     * @param customer_account
     * @return 判断结果
     */
    boolean isInDB(String customer_account);

    /**
     * 获得customer数量
     *
     * @return customer数量
     */
    int totalCustomers();

    /**
     *获取customer列表
     *
     * @return 返回customer列表
     */
    List<Customer> getCustomerList();

    /**
     * 通过账号获取一个customer
     *
     * @param customer_account
     * @return 指定账号的customer
     */
    Customer getCustomerByAccount(String customer_account);

    /**
     * 获取所有采用指定用户名的customer
     * @param customer_name
     * @return 使用了指定用户名的customer列表
     */
    List<Customer> getCustomerByName(String customer_name);

    /**
     * 获取所有customer的注册时间
     * @return 注册时间列表
     */
    List<Timestamp> getCustomerRegisterTimeList();

    /**
     * 增加一个customer
     *
     * @param customer
     * @return 结果
     */
    boolean addCustomer(Customer customer);

    /**
     * 修改指定账户的customer信息，只能修改用户名，密码，邮箱
     *
     * @param customer 包含了新信息的customer
     * @return 结果
     */
    boolean resetCustomerInfo(Customer customer);

    /**
     * 通过账号删除一个customer
     *
     * @param customer_account
     * @return 被删除的customer
     */
    Customer deleteCustomerByAccount(String customer_account);

    /**
     * 删除一个customer
     * @param customer 需要被删除customer对象
     * @return 被删除的customer对象
     */
    Customer deleteCustomer(Customer customer);
}
