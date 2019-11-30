package ordering.repository.jdbc;

import ordering.domain.Address;
import ordering.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcAddressRepository implements AddressRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Address>  addressRowMapper=new BeanPropertyRowMapper<>(Address.class);

    private static final String SELECT_ADDRESS="select * from address";
    private static final String TOTAL_ADDRESS="select count(*) from address";
    private static final String INSERT_ADDRESS="insert into address (address_id, customer_account, contact, phone, info,delete_tag) value (?,?,?,?,?,?)";
    @Autowired
    public JdbcAddressRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public boolean isInDB(String address_id)
    {
        return jdbcTemplate.queryForObject(TOTAL_ADDRESS + " where address_id=\'" + address_id+"\'", Integer.class) != 0;
    }
    @Override
    public int getTotalCustomerAddress(String customer_account) {
        return jdbcTemplate.queryForObject(TOTAL_ADDRESS+" where customer_account ="+customer_account+" and delete_tag = 0",Integer.class);
    }

    @Override
    public List<Address> getCustomerAddress(String customer_account) {
        return jdbcTemplate.query(SELECT_ADDRESS + " where customer_account =\'" + customer_account + "\' and delete_tag = 0", addressRowMapper);
    }

    @Override
    public boolean addAddress(Address address) {
        jdbcTemplate.update(INSERT_ADDRESS,address.getAddress_id(),
                address.getCustomer_account(),address.getContact(),
                address.getPhone(),address.getInfo(),address.getDelete_tag());
        return true;
    }

    @Override
    public boolean deleteAddress(String address_id) {
        jdbcTemplate.update("update address set delete_tag = 1 where address_id = \'"+address_id+"\'");
        return true;
    }

    @Override
    public boolean resetAddress(Address address) {
        jdbcTemplate.update("update address set customer_account =?,contact=?,phone = ?,info =? where address_id = ?",
                address.getCustomer_account(),address.getContact(),address.getPhone(),address.getInfo(),address.getAddress_id());
        return true;
    }

    @Override
    public Address getAddress(String address_id) {
        return jdbcTemplate.queryForObject("select * from address where address_id ="+address_id,addressRowMapper);
    }
}
