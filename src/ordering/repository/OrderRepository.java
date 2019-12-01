package ordering.repository;

import ordering.domain.Order;
import ordering.domain.OrderItem;

import java.util.List;

/**
 * Order资源库接口
 *
 * @author niejunjie
 * @version 2.0
 * @since 2019/12/1 21:25
 */
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

    /**
     * 设置订单折扣
     *
     * @param order
     */
    void setOrderDiscount(float discount, Order order);

    /**
     * 确认订单,尚未配送
     *
     * @param order
     */
    void confirmOrder(Order order);

    /**
     * 订单开始配送
     *
     * @param order
     */
    void confirmDelivery(Order order);

    /**
     * 完成配送
     *
     * @param order
     */
    void completeDelivery(Order order);

    /**
     * 完成订单
     *
     * @param order
     */
    void completeOrder(Order order);

    /**
     * 获取已确认但未配送的订单
     *
     * @return
     */
    List<Order> getConfirmedAndUndeliveredOrder();

    /**
     * 获取正在配送的订单
     *
     * @return
     */
    List<Order> getDeliveringOrder();

    /**
     * 获取已完成的订单
     *
     * @return
     */
    List<Order> getCompletedOrder();

    /**
     * 获取某天已完成的订单数（日期格式：yyyy-MM-dd，类型为string）
     *
     * @param day
     * @return
     */
    long getTotalCompletedOrdersByDayNum(String day);

    /**
     * 获取所有已完成订单数
     *
     * @return
     */
    long getTotalCompletedOrdersNum();

    /**
     * 获取某日收入总计（日期格式：yyyy-MM-dd，类型为string）
     *
     * @return
     */
    double getTotalIncomeByDay(String day);

    /**
     * 获取总收入
     *
     * @return
     */
    double getTotalIncome();

    /**
     * 未确认订单
     * @return
     */
    List<Order> getUncomfirmedOrder();

    /**
     * 获取顾客已完成的订单
     *
     * @param customer_account
     * @return
     */
    List<Order> completedOrders(String customer_account);

    /**
     * 获取顾客未完成的订单
     *
     * @param customer_account
     * @return
     */
    List<Order> uncompletedOrders(String customer_account);
}
