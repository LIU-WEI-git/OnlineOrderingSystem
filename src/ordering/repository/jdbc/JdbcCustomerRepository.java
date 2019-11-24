package ordering.repository.jdbc;

import ordering.domain.Customer;
import ordering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * customer资源库jdbc实现类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/19 21:20
 */
@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);

    private static final String TOTAL_CUSTOMER = "select count(*) from customer";
    private static final String SELECT_CUSTOMRE = "select * from customer";
    private static final String SELECT_CUSTOMER_REGISTER_TIME = "select customer_register_time from customer";
    private static final String INSERT_CUSTOMER = "insert into customer " +
            "(customer_account, customer_name, customer_password, customer_register_time, customer_email) values (?,?,?,?,?)";
    private static final String UPDATE_CUSTOMER = "update customer set customer_name=?,customer_password=?,customer_email=? where customer_account=?";
    private static final String DELETE_CUSTOMER = "delete from customer where customer_account=?";

    @Autowired
    public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isInDB(String customer_account) {
        return jdbcTemplate.queryForObject(TOTAL_CUSTOMER + " where customer_account=" + customer_account, Integer.class) != 0;
    }

    @Override
    public int totalCustomers() {
        return jdbcTemplate.queryForObject(TOTAL_CUSTOMER, Integer.class);
    }

    @Override
    public List<Customer> getCustomerList() {
        return jdbcTemplate.query(SELECT_CUSTOMRE, customerRowMapper);
    }

    @Override
    public Customer getCustomerByAccount(String customer_account) {
        return jdbcTemplate.queryForObject(SELECT_CUSTOMRE + " where customer_account=" + customer_account, Customer.class);
    }

    @Override
    public List<Customer> getCustomerByName(String customer_name) {
        return jdbcTemplate.query(SELECT_CUSTOMRE + " where customer_name=" + customer_name, customerRowMapper);
    }

    @Override
    public List<Timestamp> getCustomerRegisterTimeList() {
        return jdbcTemplate.queryForList(SELECT_CUSTOMER_REGISTER_TIME, Timestamp.class);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER, customer.getCustomer_account(), customer.getCustomer_name(),
                customer.getCustomer_password(), customer.getCustomer_register_time(), customer.getCustomer_email());
        return true;
    }

    @Override
    public boolean resetCustomerInfo(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER, customer.getCustomer_name(), customer.getCustomer_password(), customer.getCustomer_email(), customer.getCustomer_account());
        return true;
    }

    @Override
    public Customer deleteCustomerByAccount(String customer_account) {
        Customer deletedCustomer=getCustomerByAccount(customer_account);
        jdbcTemplate.update(DELETE_CUSTOMER,customer_account);
        return deletedCustomer;
    }


    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer deletedCustomer=getCustomerByAccount(customer.getCustomer_account());
        jdbcTemplate.update(DELETE_CUSTOMER,customer.getCustomer_account());
        return deletedCustomer;
    }
}
