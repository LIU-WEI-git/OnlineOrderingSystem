package ordering.repository;

import ordering.domain.OrderItem;

import java.util.List;

/**
 * OrderItem资源库接口
 *
 * @author niejunjie
 * @version 2.0
 * @since 2019/12/1 21:25
 */
public interface OrderItemRepository {
    /**
     * 查看一份订单中的菜品数目
     * @param order_id
     * @return
     */
    int getTotalOrderItem(String order_id);

    /**
     * 插入一条菜品记录
     * @param orderItem
     * @return
     */
    boolean insertItem(OrderItem orderItem);

    /**
     * 查看所有的菜品出售记录
     * @return
     */
    List<OrderItem> allItems();

    /**
     * 查看一份订单中的菜品
     * @param order_id
     * @return
     */
    List<OrderItem> itemsOfOrder(String order_id);

    /**
     * 通过菜品名称关键字查询出售该菜品的记录
     * @param dish_name
     * @return
     */
    List<OrderItem> searchByDish_name(String dish_name);

    /**
     * 通过dish_id查询出售所指菜品的记录
     * @param dish_id
     * @return
     */
    List<OrderItem> searchByDish_id(String dish_id);

    /**
     * 通过dish_id查看一种菜品的售出总数
     * @param dish_id
     * @return
     */
    int amountOfDish(String dish_id);
}
