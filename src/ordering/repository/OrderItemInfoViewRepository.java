package ordering.repository;

import ordering.domain.OrderItemInfoView;

import java.util.List;

public interface OrderItemInfoViewRepository {
    /**
     * 获取订单中菜品的数目
     *
     * @param order_id
     * @return
     */
    int totalItems(String order_id);

    /**
     * 获取订单中的菜品清单
     *
     * @param order_id
     * @return
     */
    List<OrderItemInfoView>  getOrderItems(String order_id);
}
