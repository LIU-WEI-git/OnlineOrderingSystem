package ordering.controller;

import com.oogway.cat.security.AESUtils;
import com.oogway.cat.security.MD5Utils;
import ordering.config.RootConfig;
import ordering.domain.*;
import ordering.repository.*;
import ordering.repository.DishRepository;
import ordering.utils.ShoppingCart;
import ordering.utils.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 顾客相关页面控制类
 *
 * @version: 1.0
 * Created in 2019/11/19 18:55
 */
@Controller
@SessionAttributes({"customer", "shoppingCart", "categoryList"})
@RequestMapping(value="/")
public class CustomerController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DishRepository dishRepository;
    /**
     * 顾客欢迎页
     *
     * @param model
     * @return 欢迎页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String customerWelcome(@RequestParam(value = "category_id", required = false) String category,
                                  @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
                                  Model model) {
        //TODO 按菜品类别查看菜品
        model.addAttribute(categoryRepository.getCategoryList());
        model.addAttribute(dishRepository.findByPage(pageNo,pageSize));
        if (!model.containsAttribute("shoppingCart"))
            model.addAttribute(new ShoppingCart());
        return "customer_welcome";
    }

    /**
     * 显示顾客登录界面
     *
     * @param model
     * @return 顾客登录界面
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String customerLogin(Model model, @RequestParam(value = "info", required = false) String info) {
        if (info != null)
            model.addAttribute(info);
        return "customer_login";
    }

    /**
     * 处理顾客提交的登录表单信息
     *
     * @param customer_account
     * @param customer_password
     * @param model
     * @return 登录成功重定向回首页，登录失败返回登录页面，并在url中返回错误信息
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String customerLoginSubmit(@RequestParam("customer_account") String customer_account,
                                      @RequestParam("customer_password") String customer_password,
                                      Model model) {
        if (!customerRepository.isInDB(customer_account)) {
            return "redirect:/login?info=failure";
        }
        Customer customer = customerRepository.getCustomerByAccount(customer_account);
        if (AESUtils.decodes(customer.getCustomer_password(),RootConfig.SECRET_KEY,128).equals(customer_password)) {
            model.addAttribute(customer);
            return "redirect:/";
        }
        return "redirect:/login?info=failure";
    }

    /**
     * 登出操作
     *
     * @param session
     * @return 返回首页
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String customerLogout(HttpSession session, SessionStatus sessionStatus) {
        session.removeAttribute("customer");
        session.removeAttribute("shoppingCart");
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:/";
    }

    /**
     * 显示注册页面
     *
     * @param model
     * @return 顾客注册页面
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String customerRegister(Model model, @RequestParam(value = "info", required = false) String info) {
        if (info != null)
            model.addAttribute(info);
//        model.addAttribute(new Customer());
        return "customer_register";
    }

//    /**
//     * 处理用户提交的注册信息
//     *
//     * @param customer
//     * @param errors
//     * @param model
//     * @param request
//     * @return 注册成功返回首页，注册失败返回注册页面
//     */
//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    public String customerRegisterSubmit(@Valid Customer customer, Errors errors, Model model) {
//        //TODO error无法检测表单错误
//        if (errors.hasErrors()) {
//            return "customer_register";
//        }
//        customer.setCustomer_register_time(new Timestamp(System.currentTimeMillis()));
//        customerRepository.addCustomer(customer);
//        model.addAttribute("customer", customer);
//        return "redirect:/";
//    }

    /**
     * 处理用户提交的注册信息
     *
     * @param customer_account
     * @param customer_password
     * @param customer_name
     * @param customer_email
     * @param model
     * @return 注册成功返回首页，账号已存在返回注册页面，并在url中发送错误信息
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String customerRegisterSubmit(@RequestParam("customer_account") String customer_account,
                                         @RequestParam("customer_password") String customer_password,
                                         @RequestParam("customer_name") String customer_name,
                                         @RequestParam("customer_email") String customer_email,
                                         Model model) {
        if (customerRepository.isInDB(customer_account))
            return "redirect:/register?info=existed_account";
        //对顾客输入的密码进行加密操作
        String encode_password= AESUtils.ecodes(customer_password, RootConfig.SECRET_KEY,128);
        Customer customer = new Customer(customer_account, customer_name, encode_password, new Timestamp(System.currentTimeMillis()), customer_email);
        customerRepository.addCustomer(customer);
        model.addAttribute(customer);
        return "redirect:/";
    }

    /**
     * 查看菜品详情
     *
     * @param dishId
     * @param model
     * @return 菜品详情页
     */
    @RequestMapping(value = "dishDetail/{dishId}", method = RequestMethod.GET)
    public String customerViewDishDetail(@PathVariable("dishId") String dishId, Model model) {
        Dish dish = dishRepository.findById(dishId);
        model.addAttribute(dish);
        return "customer_dish_detail";
    }

    /**
     * 查看个人信息页面
     *
     * @return 个人信息页面
     */
    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String viewCustomerAccount() {
        return "customer_account";
    }

    /**
     * 显示账户信息设置页面
     *
     * @return 账户设置页面
     */
    @RequestMapping(value = "account/resetAccount", method = RequestMethod.GET)
    public String resetAccount() {
        return "customer_reset_account";
    }

    /**
     * 处理用户在账户设置页面提交的表单
     *
     * @param model
     * @return 成功修改信息则重定向到个人账户页面
     */
    @RequestMapping(value = "account/resetAccount", method = RequestMethod.POST)
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
    @RequestMapping(value = "account/resetPassword", method = RequestMethod.GET)
    public String resetPassword() {
        return "customer_reset_password";
    }

    /**
     * 处理用户在密码设置页面提交的表单
     *
     * @param model
     * @return 成功修改密码则重定向到个人账户页面，失败则返回密码设置页面并给出提示
     */
    @RequestMapping(value = "account/resetPassword", method = RequestMethod.POST)
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
