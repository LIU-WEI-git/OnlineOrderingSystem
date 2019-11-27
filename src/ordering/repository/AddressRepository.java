package ordering.repository;


import com.sun.org.apache.xpath.internal.operations.Bool;
import ordering.domain.Address;

import java.util.List;

public interface AddressRepository {

    /**
     * 查看是否address_id已存在
     * @param address_id
     * @return
     */
    boolean isInDB(String address_id);
    /**
     * 查看指定用户的地址总数
     * @param customer_account
     * @return
     */
    int getTotalCustomerAddress(String customer_account);

    /**
     * 查看指定用户地址
     * @param customer_account
     * @return
     */
    List<Address> getCustomerAddress(String customer_account);

    /**
     * 新建地址
     * @param address
     * @return
     */
    boolean addAddress(Address address);

    /**
     * 删除指定地址
     * @param address_id
     * @return
     */
    boolean deleteAddress(String address_id);

    /**
     * 更新地址
     * @param address
     * @return
     */
    boolean resetAddress(Address address);

    /**
     * 通过address_id获取地址
     * @param address_id
     * @return
     */
    Address getAddress(String address_id);
}
