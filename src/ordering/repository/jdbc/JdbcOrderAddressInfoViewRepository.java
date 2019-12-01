package ordering.repository.jdbc;

import ordering.domain.OrderAddressInfoView;
import ordering.repository.OrderAddressInfoViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * category资源库接口的jdbc的实现类
 *
 * @author: niejunjie
 * @version: 1.0
 * Created in 2019/11/19 19:40
 */
@Repository
public class JdbcOrderAddressInfoViewRepository implements OrderAddressInfoViewRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<OrderAddressInfoView> OrderMapper=new BeanPropertyRowMapper<>(OrderAddressInfoView.class);

    private static final String GET_ADDRESS="select * from order_address_info where order_id = ";

    @Autowired
    public JdbcOrderAddressInfoViewRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public OrderAddressInfoView getAddress(String order_id) {
        return jdbcTemplate.queryForObject(GET_ADDRESS+"\'"+order_id+"\'",OrderMapper);
    }
}
