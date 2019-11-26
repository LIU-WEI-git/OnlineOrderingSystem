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
     *
     * @return
     */
    List<Order> findall();
}
