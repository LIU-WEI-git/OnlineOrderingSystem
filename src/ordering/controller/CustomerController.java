package ordering.controller;

import com.mysql.cj.Session;
import ordering.domain.Dish;
import ordering.domain.*;
import ordering.repository.*;
import ordering.repository.DishRepository;
import ordering.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
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

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemInfoViewRepository orderItemInfoViewRepository;
    @Autowired
    private OrderAddressInfoViewRepository orderAddressInfoViewRepository;
    @Autowired
    private AddressRepository addressRepository;
    /**
     * 顾客欢迎页
     *
     * @param model
     * @return 欢迎页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String customerWelcome(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,Model model) {
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
        if (customer.getCustomer_password().equals(customer_password)) {
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
        Customer customer = new Customer(customer_account, customer_name, customer_password, new Timestamp(System.currentTimeMillis()), customer_email);
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
     * 用户查看自己的订单记录
     *
     * @param model
     * @return
     */
    @RequestMapping(value="order",method = RequestMethod.GET)
    public String viewCustomerOrder(Model model,HttpSession session)
    {
        if(session.getAttribute("customer")==null){
            return "redirect:/login";
        }
        List<Order> orders;
        orders=orderRepository.getCustomerOrders(((Customer)session.getAttribute("customer")).getCustomer_account());
        //orders = orderRepository.getCustomerOrders(String.valueOf(10086123));
        model.addAttribute("orders",orders);
        return "customer_order";
    }

    /**
     * 查看订单的地址详情
     * @param order_id
     * @param model
     * @return
     */
    @RequestMapping(value = "address_info",method = RequestMethod.GET)
    public String viewOrderAddressInfo(@RequestParam(value ="order_id" )String order_id,Model model)
    {
        model.addAttribute("address",orderAddressInfoViewRepository.getAddress(order_id));
        return "customer_order_address";
    }

    /**
     * 查看订单所选菜品
     * @param order_id
     * @param model
     * @return
     */
    @RequestMapping(value="order_item",method=RequestMethod.GET)
    public String viewOrderItemInfo(@RequestParam(value="order_id" ) String order_id ,Model model)
    {
        model.addAttribute("orderitems",orderItemInfoViewRepository.getOrderItems(order_id));
        return "customer_order_item";
    }

    /**
     * 顾客地址管理
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="myaddress",method = RequestMethod.GET)
    public String managerMyAddress(Model model,HttpSession session)
    {
        model.addAttribute("addresses",addressRepository.getCustomerAddress(((Customer)session.getAttribute("customer")).getCustomer_account()));
        return "customer_address";
    }

    /**
     * 删除用户的一条地址
     * @param address_id
     * @param model
     * @return
     */
    @RequestMapping(value="delete_address",method = RequestMethod.GET)
    public String deleteAddress(@RequestParam(value = "address_id")String address_id,Model model)
    {
        addressRepository.deleteAddress(address_id);
        return "redirect:/myaddress";
    }

    @RequestMapping(value="edit_address",method = RequestMethod.GET)
    public String editAddress(@RequestParam(value="address_id")String address_id,Model model)
    {
        model.addAttribute("address",addressRepository.getCustomerAddress(address_id));
        return "customer_edit_address";
    }
}
