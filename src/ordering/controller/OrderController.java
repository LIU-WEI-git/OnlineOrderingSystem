package ordering.controller;

import com.oogway.cat.security.MD5Utils;
import ordering.config.RootConfig;
import ordering.domain.Customer;
import ordering.domain.Order;
import ordering.domain.OrderItem;
import ordering.repository.*;
import ordering.utils.ShoppingCart;
import ordering.utils.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单相关操作的控制类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/26 21:32
 */
@Controller
@SessionAttributes({"customer","shoppingCart"})
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    private OrderAddressInfoViewRepository orderAddressInfoViewRepository;
    @Autowired
    private OrderItemInfoViewRepository orderItemInfoViewRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    /**
     * 用户查看自己的订单记录
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewCustomerOrder(Model model, HttpSession session)
    {
        //如果用户未登录，重定向到登录界面
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
     *
     * @param order_id
     * @param model
     * @return
     */
    @RequestMapping(value = "/address_info",method = RequestMethod.GET)
    public String viewOrderAddressInfo(@RequestParam(value ="order_id" )String order_id, Model model)
    {
        model.addAttribute("address",orderAddressInfoViewRepository.getAddress(order_id));
        return "customer_order_address";
    }

    /**
     * 查看订单所选菜品
     *
     * @param order_id
     * @param model
     * @return
     */
    @RequestMapping(value="/order_item",method=RequestMethod.GET)
    public String viewOrderItemInfo(@RequestParam(value="order_id" ) String order_id ,Model model)
    {
        model.addAttribute("orderitems",orderItemInfoViewRepository.getOrderItems(order_id));
        return "customer_order_item";
    }

    /**
     * 显示创建订单页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public String createOrder(Model model, HttpSession session) {
        model.addAttribute(addressRepository.getCustomerAddress(((Customer) session.getAttribute("customer")).getCustomer_account()));
        return "customer_create_order";
    }

    /**
     * 处理创建订单的信息
     *
     * @param address_id
     * @param remark
     * @param model
     * @param session
     * @return 成功跳转到创建订单成功页面
     */
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String createOrderSubmit(@RequestParam("address_id") String address_id,
                                    @RequestParam(value = "remark",required = false) String remark,
                                    Model model, HttpSession session) {
        Date create_time = new Date();
        //通过创建的订单的时间生成16位订单号
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = simpleDateFormat.format(create_time);
        String order_id = MD5Utils.Md5(formattedDate, 16);
        //获取顾客账号
        String customer_account = ((Customer) session.getAttribute("customer")).getCustomer_account();
        //获取折扣信息和订单总价
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        Order order = null;
        if (shoppingCart.getTotalPrice() > RootConfig.TARGET_PRICE) {
            order = new Order(order_id, customer_account, address_id, new Timestamp(create_time.getTime()),
                    remark, RootConfig.DISCOUNT, shoppingCart.getTotalPrice() - RootConfig.DISCOUNT);
        } else {
            order = new Order(order_id, customer_account, address_id, new Timestamp(create_time.getTime()), remark, 0, shoppingCart.getTotalPrice());
        }
        orderRepository.addOrder(order);
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItemList()) {
            orderItemRepository.insertItem(new OrderItem(order_id, shoppingCartItem.getDish().getDish_id(), shoppingCartItem.getAmount()));
        }
        model.addAttribute(new ShoppingCart());
        return "customer_payment_success";
    }
}
