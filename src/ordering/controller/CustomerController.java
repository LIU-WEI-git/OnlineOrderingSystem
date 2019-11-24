package ordering.controller;

import com.mysql.cj.Session;
import ordering.domain.*;
import ordering.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * 顾客相关页面控制类
 *
 * @version: 1.0
 * Created in 2019/11/19 18:55
 */
@Controller
@SessionAttributes({"customer"})
@RequestMapping(value="/")
public class CustomerController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemInfoViewRepository orderItemInfoViewRepository;
    @Autowired
    private OrderAddressInfoViewRepository orderAddressInfoViewRepository;
    /**
     * 顾客欢迎页
     *
     * @param model
     * @return 欢迎页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String customerWelcome(Model model) {
        model.addAttribute(categoryRepository.getCategoryList());
        return "customer_welcome";
    }

    /**
     * 显示注册页面
     *
     * @param model
     * @return 顾客注册页面
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String customerRegister(Model model) {
        model.addAttribute(new Customer());
        return "customer_register";
    }

    /**
     * 处理用户提交的注册信息
     *
     * @param customer
     * @param errors
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String customerRegisterSubmit(@Valid Customer customer, Errors errors, Model model, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "customer_register";
        }
        customer.setCustomer_register_time(new Timestamp(System.currentTimeMillis()));
        customerRepository.addCustomer(customer);
        model.addAttribute("customer", customer);
        return "customer_welcome";
//        return "redirect:/{";
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
        List<Order> orders;
        orders=orderRepository.getCustomerOrders(((Customer)session.getAttribute("customer")).getCustomer_account());
        //orders = orderRepository.getCustomerOrders(String.valueOf(10086123));
        for(int i=0;i<orders.size();i++)
        {
            System.out.println(orders.get(i).toString());
        }
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

}
