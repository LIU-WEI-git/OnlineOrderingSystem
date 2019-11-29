package ordering.controller;

import com.oogway.cat.security.AESUtils;
import ordering.config.RootConfig;
import ordering.domain.Category;
import ordering.domain.Customer;
import ordering.domain.Dish;
import ordering.repository.CategoryRepository;
import ordering.repository.CustomerRepository;
import ordering.repository.DishRepository;
import ordering.utils.CustomerRegisterForm;
import ordering.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;

/**
 * 欢迎页面相关方法的控制类
 *
 * @version: 1.0
 * Created in 2019/11/28 10:32
 */
@Controller
@SessionAttributes({"shoppingCart", "customer", "categoryList"})
@RequestMapping("/")
public class HelloController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DishRepository dishRepository;

    //设置一个私有变量用于搜索菜品翻页时和按类别查看菜品翻页时存储之前传入的参数
    private String urlParam = null;

    /**
     * 顾客欢迎页
     *
     * @param model
     * @return 欢迎页
     */
    @RequestMapping(method = RequestMethod.GET)
    public String customerWelcome(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
                                  Model model) {
        model.addAttribute(categoryRepository.getCategoryList());
        model.addAttribute(dishRepository.findByPage(pageNo, pageSize));
        model.addAttribute("url", "/");
        if (!model.containsAttribute("shoppingCart"))
            model.addAttribute(new ShoppingCart());
        return "customer_welcome";
    }

    /**
     * 顾客通过关键词搜索菜品
     *
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "searchByKeyword", method = RequestMethod.POST)
    public String searchDish(@RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                             @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
                             Model model) {
        if (keyword.equals("")) {
            model.addAttribute(dishRepository.findByPage(pageNo, pageSize));
            model.addAttribute("url", "/");
        } else {
            model.addAttribute("paginationSupport", dishRepository.searchByKeywordsPage(keyword, pageNo, pageSize));
            model.addAttribute("url", "/searchByKeyword");
            urlParam = keyword;
        }
        return "customer_welcome";
    }

    /**
     * 用户通过关键词搜索后翻页
     *
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "searchByKeyword", method = RequestMethod.GET)
    public String searchDishTurnPage(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
                                     Model model) {
        model.addAttribute("paginationSupport", dishRepository.searchByKeywordsPage(urlParam, pageNo, pageSize));
        return "customer_welcome";
    }

    /**
     * 顾客按类别查看菜品
     *
     * @param category_id
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "searchByCategory", method = RequestMethod.GET)
    public String searchDishByCategory(@RequestParam(value = "category_id", required = false) String category_id,
                                       @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,
                                       Model model, HttpSession session) {
        //如果category_id不为null，则将用户传入的category_id存入私有变量中urlParam中，为翻页做准备
        //如果category_id为null说明用户发送翻页请求，此时从存储变量中取出他之前查询的category_id
        if (category_id == null)
            category_id = urlParam;
        else urlParam = category_id;
        Category category = categoryRepository.getCategoryById(category_id);
        model.addAttribute("paginationSupport", dishRepository.searchByCategoryPage(category, pageNo, pageSize));
        model.addAttribute("url", "/searchByCategory");

        return "customer_welcome";
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
     * 显示顾客登录界面
     *
     * @param model
     * @return 顾客登录界面
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
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
        //判断账号是否在数据库中
        if (!customerRepository.isInDB(customer_account)) {
            return "redirect:/login?info=failure";
        }
        //获取数据库中的账号
        Customer customer = customerRepository.getCustomerByAccount(customer_account);
        //对数据库中的密码解码，判断密码是否符合
        if (AESUtils.decodes(customer.getCustomer_password(), RootConfig.SECRET_KEY, 128).equals(customer_password)) {
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
        model.addAttribute(new CustomerRegisterForm());
        return "customer_register";
    }

    /**
     * 处理用户提交的注册信息
     *
     * @param customerRegisterForm
     * @param errors
     * @param model
     * @return 注册成功返回首页，注册失败返回注册页面
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String customerRegisterSubmit(@Valid @ModelAttribute CustomerRegisterForm customerRegisterForm, Errors errors, Model model) {
        //检测表单错误
        if (errors.hasErrors()) {
            return "customer_register";
        }
        //检测账户是否存在
        if (customerRepository.isInDB(customerRegisterForm.getCustomer_account())) {
            return "redirect:/register?info=existed_account";
        }
        //对顾客密码加密后存储到数据库
        Customer customer = new Customer(customerRegisterForm.getCustomer_account(), customerRegisterForm.getCustomer_name(),
                AESUtils.ecodes(customerRegisterForm.getCustomer_password(), RootConfig.SECRET_KEY, 128),
                new Timestamp(System.currentTimeMillis()), customerRegisterForm.getCustomer_email());
        customerRepository.addCustomer(customer);
        model.addAttribute(customer);
        return "redirect:/";
    }
}
