package ordering.repository.jdbc;

import ordering.domain.Admin;
import ordering.domain.Order;
import ordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Order> orderRowMapper=new BeanPropertyRowMapper<Order>(Order.class);

    private static class orderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {
            return new Order(rs.getString("order_id"), rs.getString("customer_account"),
                    rs.getString("address_id"), rs.getTimestamp("create_time"),
                    rs.getString("remark"), rs.getInt("order_state"), rs.getInt("delivery_state"),
                    rs.getFloat("discount"),
                    rs.getFloat("order_price"));
        }
    }

    public static final String TOTAL_ORDERS="select count(*) from `order` where customer_account = ";
    public static  final String CUSTOMER_ORDERS="select * from `order` where customer_account = ";
    public static  final String AdMIN_ORDERS="select * from `order`";


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
        return jdbcTemplate.queryForObject(TOTAL_ORDERS+customer_account,Integer.class);
    }

    @Override
    public List<Order> getCustomerOrders(String customer_account) {
        return jdbcTemplate.query(CUSTOMER_ORDERS+"'"+customer_account+"' order by create_time DESC ",orderRowMapper);
    }

    @Override
    public int getTotalOrder() {
        return jdbcTemplate.queryForObject("select count(*) from `order` ",Integer.class);
    }

    @Override
    public List<Order> getOrders() {
        return jdbcTemplate.query("select * from `order` ",orderRowMapper);
    }

    @Override
    public boolean deleteOrder(String order_id) {
        jdbcTemplate.update("delete from `order` where order_id ="+order_id);
        return true;
    }

    @Override
    public Order getOrder(String order_id) {
        return jdbcTemplate.queryForObject("select * from `order` where order_id = "+order_id,orderRowMapper);
    }

    @Override
    public List<Order> findall() {
        return jdbcTemplate.query(AdMIN_ORDERS,new orderRowMapper());
    }


    @Override
    public boolean resetOrder(Order order) {
        jdbcTemplate.update("update `order` set customer_account=?,address_id=?," +
                "create_time=?,remark=?,order_state=?,delivery_state=?,discount=?,order_price=? where order_id =?",
                order.getCustomer_account(),order.getAddress_id(),order.getCreate_time(),order.getRemark(),
                order.getOrder_state(),order.getDelivery_state(),order.getDiscount(),order.getOrder_price());
        return true;
    }

    @Override
    public boolean addOrder(Order order) {
        jdbcTemplate.update("insert into `order` (order_id, customer_account, address_id, create_time, remark, order_state, delivery_state, discount, order_price) VALUE "
                            +"(?,?,?,?,?,?,?,?,?)",order.getOrder_id(),order.getCustomer_account(),order.getAddress_id(),
                            order.getCreate_time(),order.getRemark(),order.getOrder_state(),order.getDelivery_state(),order.getDiscount(),
                            order.getOrder_price());
        return true;
    }


}
