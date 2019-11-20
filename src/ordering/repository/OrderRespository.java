package ordering.repository;

import ordering.domain.Order;
import ordering.domain.OrderItem;

import java.util.List;
public interface OrderRespository {
    List<OrderItem> getOrderitemsByCustomer(String customerid);


}
