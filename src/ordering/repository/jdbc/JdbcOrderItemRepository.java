package ordering.repository.jdbc;

import ordering.domain.OrderItem;
import ordering.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderItemRepository implements OrderItemRepository {
    JdbcTemplate jdbcTemplate;
    private RowMapper<OrderItem> item =new BeanPropertyRowMapper<>(OrderItem.class);

    public static final String SELECT_ORDER_ITEM="select * from order_item ";
    public static final String TOTAL_ORDER_ITEM="select count(*) from order_item ";
    public static final String INSERT_ORDER_ITEM="insert into order_item (order_id, dish_id, amount) value(?,?,?)";
    public static final String SELECT_DISH="select dish_id from dish ";
    @Autowired
    public JdbcOrderItemRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public int getTotalOrderItem(String order_id) {
        return jdbcTemplate.queryForObject(TOTAL_ORDER_ITEM+" where order_id ="+order_id,Integer.class);
    }

    @Override
    public boolean insertItem(OrderItem orderItem) {
        jdbcTemplate.update(INSERT_ORDER_ITEM,orderItem.getOrder_id(),orderItem.getDish_id(),orderItem.getAmount());
        return true;
    }

    @Override
    public List<OrderItem> allItems() {
        return jdbcTemplate.query(SELECT_ORDER_ITEM,item);
    }

    @Override
    public List<OrderItem> itemsOfOrder(String order_id) {
        return jdbcTemplate.query(SELECT_ORDER_ITEM+"where order_id = "+order_id,item);
    }

    @Override
    public List<OrderItem> searchByDish_name(String dish_name) {
        return jdbcTemplate.query(SELECT_ORDER_ITEM+"where dish_id in ("+SELECT_DISH+"where dish_name LIKE '%"+dish_name+"%')",item);
    }

    @Override
    public List<OrderItem> searchByDish_id(String dish_id) {
        return jdbcTemplate.query(SELECT_ORDER_ITEM +"where dish_id in ("+SELECT_DISH+"where dish_id = '"+dish_id+"')",item);
    }

    @Override
    public int amountOfDish(String dish_id) {
        return jdbcTemplate.queryForObject("select sum(amount) from order_item where dish_id = '"+dish_id+"'",Integer.class);
    }
}
