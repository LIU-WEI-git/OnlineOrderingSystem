package ordering.controller;

import com.oogway.cat.security.AESUtils;
import ordering.config.RootConfig;
import ordering.domain.Customer;
import ordering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * 顾客相关页面控制类
 *
 * @version: 1.0
 * Created in 2019/11/19 18:55
 */
@Controller
@SessionAttributes({"customer", "shoppingCart", "categoryList"})
@RequestMapping(value = "/account")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 查看个人信息页面
     *
     * @return 个人信息页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewCustomerAccount() {
        return "customer_account";
    }

    /**
     * 显示账户信息设置页面
     *
     * @return 账户设置页面
     */
    @RequestMapping(value = "/resetAccount", method = RequestMethod.GET)
    public String resetAccount() {
        return "customer_reset_account";
    }

    /**
     * 处理用户在账户设置页面提交的表单
     *
     * @param model
     * @return 成功修改信息则重定向到个人账户页面
     */
    @RequestMapping(value = "/resetAccount", method = RequestMethod.POST)
    public String resetAccountSubmit(@RequestParam("new_customer_name") String new_customer_name,
                                     @RequestParam("new_customer_email") String new_customer_email,
                                     HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        customer.setCustomer_name(new_customer_name);
        customer.setCustomer_email(new_customer_email);
        customerRepository.resetCustomerInfo(customer);
        model.addAttribute(customer);
        return "redirect:/account?info=reset_account_success";
    }

    /**
     * 显示密码设置页面
     *
     * @return 密码设置页面
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPassword() {
        return "customer_reset_password";
    }

    /**
     * 处理用户在密码设置页面提交的表单
     *
     * @param model
     * @return 成功修改密码则重定向到个人账户页面，失败则返回密码设置页面并给出提示
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPasswordSubmit(@RequestParam("old_password") String old_password,
                                      @RequestParam("new_password") String new_password,
                                      @RequestParam("confirm_password") String confirm_password,
                                      HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (!AESUtils.decodes(customer.getCustomer_password(), RootConfig.SECRET_KEY, 128).equals(old_password)) {
            return "redirect:/account/resetPassword?info=wrong_old_password";
        }
        if (!new_password.equals(confirm_password)) {
            return "redirect:/account/resetPassword?info=wrong_confirm_password";
        }
        //将新密码加密后存储到数据库
        customer.setCustomer_password(AESUtils.ecodes(new_password, RootConfig.SECRET_KEY, 128));
        customerRepository.resetCustomerInfo(customer);
        return "redirect:/account?info=reset_password_success";
    }
}
