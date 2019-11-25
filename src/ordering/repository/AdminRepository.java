package ordering.repository;

import ordering.domain.Admin;
import ordering.utils.PaginationSupport;

/**
 * 管理员资源库接口
 *
 * @author 刘威 neilly
 * @version 2.0
 * @since 2019/11/17 09:29
 */
public interface AdminRepository {

    /**
     * 取得管理员数量
     * @return 管理员数量
     */
    long count();

    /**
     * 新建一个管理员
     *
     * @param admin 新建的管理员实体
     * @return 管理员实体
     */
    Admin createAdmin(Admin admin);

    /**
     * 根据账号查找管理员
     *
     * @param admin_account 管理员账号
     * @return 管理员对象
     */
    Admin findAdminById(String admin_account);

    /**
     * 根据姓名查找管理员
     *
     * @param admin_name 管理员姓名
     * @return 管理员对象
     */
    Admin findAdminByUsername(String admin_name);

    /**
     * 依据页码和指定页面大小，返回管理员列表
     * @param pageNo 页码
     * @param pageSize 页面大小
     * @return 管理员列表
     */
    PaginationSupport<Admin> findByPage(int pageNo, int pageSize);

    /**
     * 删除指定的管理员
     *
     * @param admin 指定的管理员
     */
    void deleteAdmin(Admin admin);

    /**
     * 更新管理员个人信息
     *
     * @param admin_name 新姓名
     * @param admin_email 新邮箱
     * @param admin_phone 新电话
     * @param admin 指定管理员
     */
    void updateAdmin(String admin_name, String admin_email, String admin_phone, Admin admin);

    /**
     * 更改管理员密码
     *
     * @param admin_password 新密码
     * @param admin 指定管理员
     */
    void updateAdminPassword(String admin_password, Admin admin);

}
