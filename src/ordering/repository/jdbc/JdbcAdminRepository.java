package ordering.repository.jdbc;

import ordering.domain.Admin;
import ordering.repository.AdminRepository;
import ordering.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员内容资源库jdbc实现类
 *
 * @author 刘威 neilly
 * @version 2.0
 * @since 2019/11/17 09:31
 */
@Repository
public class JdbcAdminRepository implements AdminRepository {

    private JdbcTemplate jdbc;

    /**
     * 自动注入数据
     *
     * @param jdbc
     */
    @Autowired
    public JdbcAdminRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * 取得管理员数量
     * @return 管理员数量
     */
    @Override
    public long count() {
        return jdbc.queryForObject("select count(admin_account) from admin", long.class);
    }

    /**
     * 新建一个管理员
     *
     * @param admin 新建的管理员实体
     * @return 管理员实体
     */
    @Override
    public Admin createAdmin(Admin admin) {
        jdbc.update(INSERT_ADMIN, admin.getAdmin_account(), admin.getAdmin_name(), admin.getAdmin_password(), admin.getAdmin_register_date(), admin.getAdmin_email(), admin.getAdmin_phone());
        return admin;
    }

    /**
     * 根据账号查找管理员
     *
     * @param admin_account 管理员账号
     * @return 管理员对象
     */
    @Override
    public Admin findAdminById(String admin_account) {
        return jdbc.queryForObject(SELECT_ADMIN_BY_ACCOUNT, new AdminRowMapper(), admin_account);
    }

    /**
     * 根据姓名查找管理员
     *
     * @param admin_name 管理员姓名
     * @return 管理员对象
     */
    @Override
    public Admin findAdminByUsername(String admin_name) {
        return jdbc.queryForObject(SELECT_ADMIN_BY_NAME, new AdminRowMapper(), admin_name);
    }

    /**
     * 依据页码和指定页面大小，返回管理员列表
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @return 管理员列表
     */
    @Override
    public PaginationSupport<Admin> findByPage(int pageNo, int pageSize) {
        int totalCount = (int) count();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<>(new ArrayList<>(0), 0);

        List<Admin> items = jdbc.query(SELECT_ADMIN_PAGE, new AdminRowMapper(), pageSize, startIndex);
        return new PaginationSupport<>(items, totalCount, pageSize, startIndex);
    }

    /**
     * 删除指定的管理员
     *
     * @param admin 指定的管理员
     */
    @Override
    public void deleteAdmin(Admin admin) {
        jdbc.update(DELETE_ADMIN, admin.getAdmin_account());
    }

    /**
     * 更新管理员个人信息
     *
     * @param admin_name 新姓名
     * @param admin_email 新邮箱
     * @param admin_phone 新电话
     * @param admin 指定管理员
     */
    @Override
    public void updateAdmin(String admin_name, String admin_email, String admin_phone, Admin admin) {
        jdbc.update(UPDATE_ADMIN, admin_name, admin_email, admin_phone, admin.getAdmin_account());
    }

    /**
     * 更改管理员密码
     *
     * @param admin_password 新密码
     * @param admin 指定管理员
     */
    @Override
    public void updateAdminPassword(String admin_password, Admin admin) {
        jdbc.update(UPDATE_ADMIN_PASSWORD, admin_password, admin.getAdmin_account());
    }

    /**
     * 数据库查询建立管理员对象
     */
    private static class AdminRowMapper implements RowMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet rs, int i) throws SQLException {
            return new Admin(rs.getString("admin_account"), rs.getString("admin_name"),
                    rs.getString("admin_password"), rs.getTimestamp("admin_register_time"),
                    rs.getString("admin_email"), rs.getString("admin_phone"),
                    rs.getInt("delete_tag"));
        }
    }

    /**
     * SQL语句
     */
    // 插入管理员
    private static final String INSERT_ADMIN = "INSERT INTO admin (admin_account, admin_name, admin_password, admin_register_time, admin_email, admin_phone) VALUES(?, ?, ?, ?, ?, ?)";
    // 取得所有管理员
    private static final String SELECT_ADMIN = "SELECT admin_account, admin_name, admin_password, admin_register_time, admin_email, admin_phone, delete_tag FROM admin";
    // 根据account查找管理员
    private static final String SELECT_ADMIN_BY_ACCOUNT = SELECT_ADMIN + " WHERE admin_account=?";
    // 根据name查找管理员
    private static final String SELECT_ADMIN_BY_NAME = SELECT_ADMIN + " WHERE admin_name=?";
    // 分页取得管理员
    private static final String SELECT_ADMIN_PAGE = SELECT_ADMIN + " ORDER BY admin_register_time LIMIT ? OFFSET ?";
    // 删除管理员
    private static final String DELETE_ADMIN = "UPDATE admin SET delete_tag=1 WHERE admin_account=?";
    // 更新管理员信息
    private static final String UPDATE_ADMIN = "UPDATE admin SET admin_name=?, admin_email=?, admin_phone=? WHERE admin_account=?";
    // 更新管理员密码
    private static final String UPDATE_ADMIN_PASSWORD = "UPDATE admin SET admin_password=? WHERE admin_account=?";
}
