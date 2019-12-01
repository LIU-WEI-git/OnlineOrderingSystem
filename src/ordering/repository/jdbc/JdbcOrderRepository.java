package ordering.repository.jdbc;

import ordering.domain.Address;
import ordering.domain.Customer;
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

/**
 *
 */
@Repository
public class JdbcOrderRepository implements OrderRepository {
    private JdbcTemplate jdbcTemplate;
    private  OrderRowMapper orderRowMapper=new OrderRowMapper();

    private static class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {
            Customer customer=new Customer(rs.getString("customer_account"),rs.getString("customer_name"),
                    rs.getString("customer_password"),rs.getTimestamp("customer_register_time"),rs.getString("customer_email"));
            Address address=new Address(rs.getString("address_id"),customer,rs.getString("contact"),
                    rs.getString("phone"),rs.getString("info"),rs.getInt("delete_tag"));
            return new Order(rs.getString("order_id"), customer,
                    address, rs.getTimestamp("create_time"),
                    rs.getString("remark"), rs.getInt("order_state"), rs.getInt("delivery_state"),
                    rs.getFloat("discount"),
                    rs.getFloat("order_price"));
        }
    }

    private static final String TOTAL_ORDERS="select count(*) from `order` o,customer c,address a where o.customer_account =c.customer_account and o.address_id=a.address_id and o.customer_account = ?";
    private static  final String CUSTOMER_ORDERS="select * from `order` o,customer c,address a where o.customer_account =c.customer_account and o.address_id=a.address_id and o.customer_account = ? ";
    private static  final String AdMIN_ORDERS="select * from `order` o ,customer c ,address a where o.customer_account=c.customer_account and o.address_id=a.address_id ";
   /* private static final String TOTAL_ORDERS="select count(*) from `order` where customer_account = ";
    private static final String CUSTOMER_ORDERS="select * from `order` where customer_account = ";*/

    // 设置订单折扣
    private static final String SET_DISCOUNT = "UPDATE `order` SET discount=? WHERE order_id=?";

    // 确认订单
    private static final String CONFIRM_ORDER = "UPDATE `order` SET order_state=1 WHERE order_id=?";
    // 完成订单
    private static final String COMPLETE_ORDER = "UPDATE `order` SET order_state=2 WHERE order_id=?";
    // 确认开始配送
    private static final String CONFIRM_DELIVERY = "UPDATE `order` SET delivery_state=1 WHERE order_id=?";
    // 完成配送
    private static final String COMPLETE_DELIVERY = "UPDATE `order` SET delivery_state=2 WHERE order_id=?";

    // order表查询
    private static final String SELECT_ORDER = "SELECT * FROM `order` o,customer c,address a where o.customer_account =c.customer_account and o.address_id=a.address_id ";
    // 排序
    private static final String ORDER_BY = " ORDER BY create_time DESC";
    //未确认 未配送
    private static final String SELECT_UNCONFIRMED_UNDELIVERED_ORDER = SELECT_ORDER + "and o.order_state=0 AND o.delivery_state=0";
    // 查询已确认但未配送的订单
    private static final String SELECT_CONFIRMED_UNDELIVERED_ORDER = SELECT_ORDER + " and o.order_state=1 AND o.delivery_state=0";
    // 查询正在配送的订单
    private static final String SELECT_DELIVERING_ORDER = SELECT_ORDER + " and o.order_state=1 AND o.delivery_state=1";
    // 查询已完成的订单（逆序）
    private static final String SELECT_COMPLETED_ORDER = SELECT_ORDER + " and o.order_state=2 AND o.delivery_state=2";

    // 获取已完成订单总数
    private static final String SELECT_COMPLETED_COUNT = "SELECT COUNT(*) FROM `order` WHERE order_state=2 AND delivery_state=2";
    // 获取收入
    private static final String TOTAL_INCOME = "SELECT COALESCE(SUM(v.per_income), 0) AS income FROM (SELECT order_id, create_time, (order_price + discount) AS per_income FROM `order` WHERE order_state=2 AND delivery_state=2) AS v";
    /*private static final String TOTAL_ORDERS="select count(*) from `order` where customer_account = ";
    private static  final String CUSTOMER_ORDERS="select * from `order` where customer_account = ";*/

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public boolean isCustomerInDB(String customer_account) {
        return jdbcTemplate.queryForObject(TOTAL_ORDERS , Integer.class,customer_account) != 0;
    }

    @Override
    public int getCustomerTotalOrders(String customer_account) {
        return jdbcTemplate.queryForObject(TOTAL_ORDERS,Integer.class,customer_account);
    }

    @Override
    public List<Order> getCustomerOrders(String customer_account) {
        return jdbcTemplate.query(CUSTOMER_ORDERS+" order by create_time DESC ",orderRowMapper,customer_account);
    }

    @Override
    public int getTotalOrder() {
        return jdbcTemplate.queryForObject("select count(*) from `order` ",Integer.class);
    }

    @Override
    public List<Order> getOrders() {
        return jdbcTemplate.query(AdMIN_ORDERS,orderRowMapper);
    }

    @Override
    public boolean deleteOrder(String order_id) {
        jdbcTemplate.update("delete from `order` where order_id = ?",order_id);
        return true;
    }

    @Override
    public Order getOrder(String order_id) {
        return jdbcTemplate.queryForObject(AdMIN_ORDERS+" and o.order_id=?", orderRowMapper,order_id);
    }

    @Override
    public List<Order> findall() {
        return jdbcTemplate.query(AdMIN_ORDERS,orderRowMapper);
    }


    @Override
    public boolean resetOrder(Order order) {
        jdbcTemplate.update("update `order` set customer_account=?,address_id=?," +
                "create_time=?,remark=?,order_state=?,delivery_state=?,discount=?,order_price=? where order_id =?",
                order.getCustomer().getCustomer_account(),order.getAddress().getAddress_id(),order.getCreate_time(),order.getRemark(),
                order.getOrder_state(),order.getDelivery_state(),order.getDiscount(),order.getOrder_price());
        return true;
    }

    @Override
    public boolean addOrder(Order order) {
        jdbcTemplate.update("insert into `order` (order_id, customer_account, address_id, create_time, remark, order_state, delivery_state, discount, order_price) VALUE "
                            +"(?,?,?,?,?,?,?,?,?)",order.getOrder_id(),order.getCustomer().getCustomer_account(),order.getAddress().getAddress_id(),
                            order.getCreate_time(),order.getRemark(),order.getOrder_state(),order.getDelivery_state(),order.getDiscount(),
                            order.getOrder_price());
        return true;
    }

    @Override
    public void setOrderDiscount(float discount, Order order) {
        jdbcTemplate.update(SET_DISCOUNT, discount, order.getOrder_id());
    }

    @Override
    public void confirmOrder(Order order) {
        jdbcTemplate.update(CONFIRM_ORDER, order.getOrder_id());
    }

    @Override
    public void confirmDelivery(Order order) {
        jdbcTemplate.update(CONFIRM_DELIVERY, order.getOrder_id());
    }

    @Override
    public void completeDelivery(Order order) {
        jdbcTemplate.update(COMPLETE_DELIVERY, order.getOrder_id());
    }

    @Override
    public void completeOrder(Order order) {
        jdbcTemplate.update(COMPLETE_ORDER, order.getOrder_id());
    }

    @Override
    public List<Order> getConfirmedAndUndeliveredOrder() {
        return jdbcTemplate.query(SELECT_CONFIRMED_UNDELIVERED_ORDER, orderRowMapper);
    }

    @Override
    public List<Order> getDeliveringOrder() {
        return jdbcTemplate.query(SELECT_DELIVERING_ORDER, orderRowMapper);
    }

    @Override
    public List<Order> getCompletedOrder() {
        return jdbcTemplate.query(SELECT_COMPLETED_ORDER + ORDER_BY, orderRowMapper);
    }
    @Override
    public List<Order> getUncomfirmedOrder() {
        return jdbcTemplate.query(SELECT_UNCONFIRMED_UNDELIVERED_ORDER + ORDER_BY, orderRowMapper);
    }


    @Override
    public long getTotalCompletedOrdersByDayNum(String day) {
        return jdbcTemplate.queryForObject(SELECT_COMPLETED_COUNT + " AND create_time LIKE '" + day + "%'", long.class);
    }

    @Override
    public long getTotalCompletedOrdersNum() {
        return jdbcTemplate.queryForObject(SELECT_COMPLETED_COUNT, long.class);
    }

    @Override
    public double getTotalIncomeByDay(String day) {
        return jdbcTemplate.queryForObject(TOTAL_INCOME + " WHERE v.create_time LIKE '" + day + "%'", double.class);
    }

    @Override
    public double getTotalIncome() {
        return jdbcTemplate.queryForObject(TOTAL_INCOME, double.class);
    }

    @Override
    public List<Order> completedOrders(String customer_account) {
        return jdbcTemplate.query(CUSTOMER_ORDERS+" and order_state =2 order by create_time DESC ",orderRowMapper,customer_account);
    }

    @Override
    public List<Order> uncompletedOrders(String customer_account) {
        return jdbcTemplate.query(CUSTOMER_ORDERS+" and (order_state =0 or order_state = 1 )order by create_time DESC ",orderRowMapper,customer_account);
    }


}
