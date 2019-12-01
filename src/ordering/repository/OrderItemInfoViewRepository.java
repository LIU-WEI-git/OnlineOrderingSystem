package ordering.repository;

import ordering.domain.OrderItemInfoView;

import java.util.List;

/**
 * OrderItemInfo资源库接口
 *
 * @author niejunjie
 * @version 2.0
 * @since 2019/12/1 21:25
 */
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
