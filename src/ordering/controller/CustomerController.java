package ordering.controller;

import ordering.domain.Customer;
import ordering.domain.Dish;
import ordering.repository.CategoryRepository;
import ordering.repository.CustomerRepository;
import ordering.repository.DishRepository;
import ordering.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

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
    public String customerWelcome(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,Model model) {
        model.addAttribute(categoryRepository.getCategoryList());
        model.addAttribute(dishRepository.findByPage(pageNo,pageSize));
        return "customer_welcome";
    }

    /**
     * 显示顾客登录界面
     *
     * @param model
     * @return 顾客登录界面
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String customerLogin(Model model){
        return "customer_login";
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
     * @return 注册成功返回首页，注册失败返回注册页面
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String customerRegisterSubmit(@Valid Customer customer, Errors errors, Model model, HttpServletRequest request) {
        //TODO error无法检测表单错误
        if (errors.hasErrors()) {
            return "customer_register";
        }
        customer.setCustomer_register_time(new Timestamp(System.currentTimeMillis()));
        customerRepository.addCustomer(customer);
        model.addAttribute("customer", customer);
        //注册成功则自动登录，并在session中加入shoppingCart
        model.addAttribute(new ShoppingCart());
        return "customer_welcome";
//        return "redirect:/{";
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

}
