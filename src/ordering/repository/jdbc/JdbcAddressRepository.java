package ordering.repository.jdbc;

import ordering.domain.Address;
import ordering.domain.Customer;
import ordering.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * address资源库jdbc实现类
 *
 * @author niejunjie
 * @version 2.0
 * @since 2019/11/17 09:31
 */
@Repository
public class JdbcAddressRepository implements AddressRepository {

    private JdbcTemplate jdbcTemplate;
    //private RowMapper<Address>  addressRowMapper=new BeanPropertyRowMapper<>(Address.class);

    private static final String SELECT_ADDRESS = "select a.address_id, a.customer_account, a.contact, a.phone, a.info, a.delete_tag, " +
            "c.customer_account, c.customer_name, c.customer_password, c.customer_register_time, c.customer_email from address a, customer c " +
            "where c.customer_account=a.customer_account";
    private static final String TOTAL_ADDRESS="select count(*) from address";
    private static final String INSERT_ADDRESS="insert into address (address_id, customer_account, contact, phone, info,delete_tag) value (?,?,?,?,?,?)";

    //自定义RowMapper
    private static final class AddressRowMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet resultSet, int i) throws SQLException {
            String address_id = resultSet.getString("address_id");
            String customer_account = resultSet.getString("customer_account");
            String customer_name = resultSet.getString("customer_name");
            String customer_password = resultSet.getString("customer_password");
            Timestamp customer_register_time = resultSet.getTimestamp("customer_register_time");
            String customer_email = resultSet.getString("customer_email");
            String contact = resultSet.getString("contact");
            String phone = resultSet.getString("phone");
            String info = resultSet.getString("info");
            int delete_tag = resultSet.getInt("delete_tag");
            Customer customer = new Customer(customer_account, customer_name, customer_password, customer_register_time, customer_email);
            return new Address(address_id, customer, contact, phone, info, delete_tag);
        }
    }

    @Autowired
    public JdbcAddressRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public boolean isInDB(String address_id)
    {
        //return jdbcTemplate.queryForObject(TOTAL_ADDRESS + " where address_id=\'" + address_id+"\'", Integer.class) != 0;
        return jdbcTemplate.queryForObject(TOTAL_ADDRESS + " where address_id=?", Integer.class, address_id) != 0;
    }
    @Override
    public int getTotalCustomerAddress(String customer_account) {
        return jdbcTemplate.queryForObject(TOTAL_ADDRESS + " where customer_account =? and delete_tag = 0", Integer.class, customer_account);
    }

    @Override
    public List<Address> getCustomerAddress(String customer_account) {
        return jdbcTemplate.query(SELECT_ADDRESS + " and c.customer_account=? and delete_tag = 0", new AddressRowMapper(), customer_account);
    }

    @Override
    public boolean addAddress(Address address) {
        jdbcTemplate.update(INSERT_ADDRESS,address.getAddress_id(),
                address.getCustomer().getCustomer_account(), address.getContact(),
                address.getPhone(),address.getInfo(),address.getDelete_tag());
        return true;
    }

    @Override
    public boolean deleteAddress(String address_id) {
        jdbcTemplate.update("update address set delete_tag = 1 where address_id = ?", address_id);
        return true;
    }

    @Override
    public boolean resetAddress(Address address) {
        jdbcTemplate.update("update address set customer_account =?,contact=?,phone = ?,info =? where address_id = ?",
                address.getCustomer().getCustomer_account(), address.getContact(), address.getPhone(), address.getInfo(), address.getAddress_id());
        return true;
    }

    @Override
    public Address getAddress(String address_id) {
        return jdbcTemplate.queryForObject(SELECT_ADDRESS + " and address_id=? and delete_tag=0", new AddressRowMapper(), address_id);
    }
}
