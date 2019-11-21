package ordering.repository.jdbc;

import ordering.domain.OrderItemInfoView;
import ordering.repository.OrderItemInfoViewRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderItemInfoViewRepository implements OrderItemInfoViewRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<OrderItemInfoView> items=new BeanPropertyRowMapper<OrderItemInfoView>(OrderItemInfoView.class);

    public static  final String TOTAL_ITEMS="select count(*) from order_item_info where order_id = ?";
    public static final String GET_ORDERITEMS="select * from order_item_info where order_id = ?";
    @Override
    public int totalItems(String order_id)
    {
        return jdbcTemplate.queryForObject(TOTAL_ITEMS,Integer.class,order_id);
    }

    @Override
    public List<OrderItemInfoView> getOrderItems(String order_id)
    {
        return jdbcTemplate.query(GET_ORDERITEMS,items,order_id);
    }
}
