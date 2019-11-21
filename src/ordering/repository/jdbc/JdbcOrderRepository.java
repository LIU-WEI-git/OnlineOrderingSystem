package ordering.repository.jdbc;

import ordering.domain.Order;
import ordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Order> orderRowMapper=new BeanPropertyRowMapper<Order>(Order.class);

    public static final String TOTAL_ORDERS="select count(*) from Order where customer_account = ?";
    public static  final String CUSTOMER_ORDERS="select * from Order where customer_account = ?";

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public boolean isCustomerInDB(String customer_account) {
        if(jdbcTemplate.queryForObject(TOTAL_ORDERS+customer_account,Integer.class)==0)
            return false;
        else return true;
    }

    @Override
    public int getCustomerTotalOrders(String customer_account) {
        return jdbcTemplate.queryForObject(TOTAL_ORDERS,Integer.class,customer_account);
    }

    @Override
    public List<Order> getCustomerOrders(String customer_account) {
        return jdbcTemplate.query(CUSTOMER_ORDERS,orderRowMapper,customer_account);
    }
}
