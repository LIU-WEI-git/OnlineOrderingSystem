package ordering.repository.jdbc;

import ordering.domain.OrderAddressInfoView;
import ordering.repository.OrderAddressInfoViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOrderAddressInfoViewRepository implements OrderAddressInfoViewRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<OrderAddressInfoView> OrderMapper=new BeanPropertyRowMapper<OrderAddressInfoView>(OrderAddressInfoView.class);

    public static final String GET_ADDRESS="select * from order_address_info where order_id = ";

    @Autowired
    public JdbcOrderAddressInfoViewRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public OrderAddressInfoView getAddress(String order_id) {
        return jdbcTemplate.queryForObject(GET_ADDRESS+order_id,OrderMapper);
    }
}
