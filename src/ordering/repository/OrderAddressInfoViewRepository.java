package ordering.repository;

import ordering.domain.OrderAddressInfoView;

public interface OrderAddressInfoViewRepository {
    /**
     * 通过order_id 获取order地址的详细信息
     *
     * @param order_id
     * @return
     */
    OrderAddressInfoView getAddress(String order_id);

}
