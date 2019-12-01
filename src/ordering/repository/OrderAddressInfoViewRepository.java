package ordering.repository;

import ordering.domain.OrderAddressInfoView;

/**
 * 数据库视图OrderAddressInfo实现的接口
 *
 * @author niejunjie
 * @version 2.0
 * @since 2019/11/17 09:31
 */
public interface OrderAddressInfoViewRepository {
    /**
     * 通过order_id 获取order地址的详细信息
     *
     * @param order_id
     * @return
     */
    OrderAddressInfoView getAddress(String order_id);

}
