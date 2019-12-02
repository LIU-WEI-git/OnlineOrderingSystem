package ordering.repository.jdbc;

import ordering.domain.OrderItemInfoView;
import ordering.repository.OrderItemInfoViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *视图orderItemInfo资源访问的JDBC实现类
 *
 * @author niejunjie
 * @version 1.0
 */
@Repository
public class JdbcOrderItemInfoViewRepository implements OrderItemInfoViewRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<OrderItemInfoView> item=new BeanPropertyRowMapper<>(OrderItemInfoView.class);

    private static  final String TOTAL_ITEMS="select count(*) from order_item_info where order_id = ";
    private static final String GET_ORDERITEMS="select * from order_item_info where order_id = ";

    @Autowired
    public JdbcOrderItemInfoViewRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public int totalItems(String order_id)
    {
        return jdbcTemplate.queryForObject(TOTAL_ITEMS + "?", Integer.class, order_id);
    }

    @Override
    public List<OrderItemInfoView> getOrderItems(String order_id)
    {
        return jdbcTemplate.query(GET_ORDERITEMS + "?", item, order_id);
    }
}
