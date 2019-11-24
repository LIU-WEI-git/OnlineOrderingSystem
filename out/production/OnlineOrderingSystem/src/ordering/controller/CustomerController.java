package ordering.controller;

import ordering.domain.Customer;
import ordering.repository.CategoryRepository;
import ordering.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;

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
}
