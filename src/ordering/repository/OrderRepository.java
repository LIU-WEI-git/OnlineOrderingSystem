package ordering.repository;

import ordering.domain.Order;
import ordering.domain.OrderItem;

import java.util.List;


public interface OrderRepository {

    /**
     * 查看查询顾客是否有订单记录
     *
     * @param custome_account
     * @return
     */
    boolean isCustomerInDB(String custome_account);

    /**
     * 查看顾客历史订单数量
     *
     * @param customer_account
     * @return
     */
    int getCustomerTotalOrders(String customer_account);

    /**
     * 获取customer的详细订单
     *
     * @param customer_account
     * @return
     */
    List<Order> getCustomerOrders(String customer_account);

    /**
     * 获取所有订单数目
     * @return
     */
    int getTotalOrder();

    /**
     * 获取所有订单（按时间排序）
     * @return
     */
    List<Order> getOrders();

    /**
     * 通过order_id删除一条订单记录
     * @param order_id
     * @return
     */
    boolean deleteOrder(String order_id);

    /**
     * 通过order_id获取一条订单记录
     * @param order_id
     * @return
     */
    Order getOrder(String order_id);

    /**
     *
     * @return
     */
    List<Order> findall();
    /**
     * 更新一条订单记录
     * @param order
     * @return
     */
    boolean resetOrder(Order order);

    /**
     * 添加一条订单记录
     * @param order
     * @return
     */
    boolean addOrder(Order order);
}
