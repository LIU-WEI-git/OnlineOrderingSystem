package ordering.controller;

import ordering.domain.Address;
import ordering.domain.Customer;
import ordering.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * 顾客地址信息操作相关的控制类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/26 21:27
 */
@Controller
@SessionAttributes({"customer"})
@RequestMapping("/myAddress")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * 顾客地址管理
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String managerMyAddress(Model model, HttpSession session)
    {
        model.addAttribute("addresses",addressRepository.getCustomerAddress(((Customer)session.getAttribute("customer")).getCustomer_account()));
        return "customer_address";
    }

    /**
     * 删除用户的一条地址
     *
     * @param address_id
     * @param model
     * @return
     */
    @RequestMapping(value="/delete_address",method = RequestMethod.GET)
    public String deleteAddress(@RequestParam(value = "address_id")String address_id, Model model)
    {
        addressRepository.deleteAddress(address_id);
        return "redirect:/myAddress";
    }

    /**
     * 显示编辑一条地址页面
     *
     * @param address_id
     * @param model
     * @return
     */
    @RequestMapping(value="/edit_address",method = RequestMethod.GET)
    public String editAddress(@RequestParam(value="address_id")String address_id,Model model)
    {
        model.addAttribute("address",addressRepository.getAddress(address_id));
        return "customer_edit_address";
    }

    /**
     * 编辑一条地址
     *
     * @param address_id
     * @param customer_account
     * @param contact
     * @param phone
     * @param info
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit_address",method = RequestMethod.POST)
    public String updateAddress(@RequestParam(value = "address_id")String address_id,
                                @RequestParam(value = "customer_account")String customer_account,
                                @RequestParam(value = "contact")String contact,
                                @RequestParam(value="phone")String phone,
                                @RequestParam(value = "address_info")String info,
                                Model model)
    {
        Address address = new Address(address_id, customer_account, contact, phone, info);
        addressRepository.resetAddress(address);
        return "redirect:/myAddress";
    }

    /**
     * 添加一条新的收货地址
     *
     * @param contact
     * @param phone
     * @param info
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/add_address",method = RequestMethod.POST)
    public String addAddress(@RequestParam(value = "contact")String contact,
                             @RequestParam(value = "phone")String phone,
                             @RequestParam(value = "address_info")String info,
                             HttpSession session, Model model)
    {
        System.out.println(contact);
        Date date =new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + day;
        }
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
            hour = "0" + hour;
        }
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        if (calendar.get(Calendar.MINUTE) < 10) {
            minute = "0" + minute;
        }
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        if (calendar.get(Calendar.SECOND) < 10) {
            second = "0" + second;
        }
        String address_id = day + hour + minute + second + ((Customer) session.getAttribute("customer")).getCustomer_account().substring(0, 4);
        if (addressRepository.isInDB(address_id)) {
            return "redirect:/myAddress/add_address?info=fail to register,please try again";
        }
        Address address = new Address(address_id, ((Customer) session.getAttribute("customer")).getCustomer_account(), contact, phone, info);
        addressRepository.addAddress(address);
        return "redirect:/myAddress";
    }

    /**
     * 显示新增地址页面
     *
     * @param model
     * @param info
     * @return
     */
    @RequestMapping(value = "/add_address",method = RequestMethod.GET)
    public String addressRegister(Model model,@RequestParam(value = "info",required = false)String info)
    {
        if(info!=null)
        {
            model.addAttribute(info);
        }
        return "customer_address_register";
    }
}
