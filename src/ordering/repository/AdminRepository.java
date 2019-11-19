package ordering.repository;

import ordering.domain.Admin;

import java.util.List;

/**
 * admin资源库接口
 *
 * @version: 1.0
 * Created in 2019/11/17 9:29
 */
public interface AdminRepository {

    List<Admin> getAdminList();
}
