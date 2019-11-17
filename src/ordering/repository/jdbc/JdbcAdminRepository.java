package ordering.repository.jdbc;

import ordering.domain.Admin;
import ordering.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/17 9:31
 */
@Repository
public class JdbcAdminRepository implements AdminRepository {

    private JdbcTemplate jdbcTemplate;
    private static final String SELECT_ADMIN = "select * from admin";

    private static class AdminRowMapper implements RowMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Admin(resultSet.getString("admin_account"), resultSet.getString("admin_name"),
                    resultSet.getString("admin_password"), resultSet.getTimestamp("admin_register_time"),
                    resultSet.getString("admin_email"), resultSet.getString("admin_phone"),
                    resultSet.getInt("delete_tag"));
        }
    }

    @Autowired
    public JdbcAdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Admin> getAdminList() {
        return jdbcTemplate.query(SELECT_ADMIN, new AdminRowMapper());
    }
}
