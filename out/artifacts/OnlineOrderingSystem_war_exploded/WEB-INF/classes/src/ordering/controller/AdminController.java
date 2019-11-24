package ordering.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import ordering.domain.Admin;
import ordering.domain.Category;
import ordering.domain.Dish;
import ordering.domain.DishCategory;
import ordering.repository.AdminRepository;
import ordering.repository.CategoryRepository;
import ordering.repository.DishRepository;
import ordering.utils.CategoryDishSupport;
import ordering.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    /**
     *
     * @return
     */
    @RequestMapping(value = "/alogin", method = GET)
    public String showLoginForm() {
        return "adminlogin";
    }

    /**
     *
     * @param useraccount
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/alogin", method = POST)
    public String processLogin(@RequestParam(value = "useraccount", defaultValue = "") String useraccount,
                               @RequestParam(value = "password", defaultValue = "") String password, HttpSession session) {

        Admin admin = adminRepository.findByUserName(useraccount, password);
        if (admin != null) {
            session.setAttribute("admin", admin);
            session.setAttribute("name", admin.getAdmin_name());
            return "admin_welcome";
        } else {
            return "store";}


    }


    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/user", method = GET)
    public String showadminlist(HttpSession session) {
        List<Admin> alist=adminRepository.findadminlsit();
        session.setAttribute("alist",alist);
        return "admin_user";
    }

    /**
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/deleteadmin", method = GET)
    public String deletedmin(@RequestParam(value = "admin_username", defaultValue = "") String userName) {
       adminRepository.deleteAdmin(adminRepository.findAdminByUsername(userName));
        return "redirect:/admin/user";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addadmin", method = GET)
    public String addaadmin(Model model){
        model.addAttribute(new Admin());

        return "admin_useradd";
    }

    /**
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/addadmin", method = POST)
    public String adddamin(@RequestParam(value = "admin_account", defaultValue = "") String account,
                           @RequestParam(value = "admin_password", defaultValue = "") String password,
                           @RequestParam(value = "admin_email", defaultValue = "") String email,
                           @RequestParam(value = "admin_name", defaultValue = "") String name,
                           @RequestParam(value = "admin_phone", defaultValue = "") String phone){
        Date day=new Date();
        Admin admin=new Admin(account,name,password,day,email,phone);
        admin=adminRepository.createAdmin(admin);
        return "admin_addsuccess";
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/dish", method = GET)
    public String showdishlist(HttpSession session) {
        List<Dish> list=dishRepository.getAll();
        session.setAttribute("list",list);
        return "admin_dish";
    }

    @RequestMapping(value = "/changedish", method = GET)
    public String changdish(@RequestParam(value = "dish_id", defaultValue = "") String dishid,HttpSession session) {
       Dish dish=dishRepository.findById(dishid);
        session.setAttribute("dish",dish);
        return "admin_dishchange";
    }

    @RequestMapping(value = "/searchdish", method = GET)
    public String searchdish(@RequestParam(value = "signal", defaultValue = "") String signal,
                             @RequestParam(value = "message", defaultValue = "") String message,HttpSession session) {
        if(signal.equals("all")){
            List<Dish> list=dishRepository.getAll();
            session.setAttribute("list",list);
            return "admin_dish";
        }
        else{
        Category category=categoryRepository.getCategoryByName(signal);
        CategoryDishSupport w=categoryRepository.listCategoryDishes(category);
        List<Dish> list=w.getDishes();
        session.setAttribute("list",list);

        return "admin_dish";}
    }

    @RequestMapping(value = "/changedish", method = POST)
    public String changdishac(){
        return "";
    }

    @RequestMapping(value = "/dishcategory", method = GET)
    public String showdishcategory(HttpSession session) {
        List<Category> blist=categoryRepository.getCategoryList();
        session.setAttribute("blist",blist);
        return "admin_dishcategory";
    }


    @RequestMapping(value = "/person", method = GET)
    public String personal(){
        return "admin_person";
    }


    @RequestMapping(value = "/personmate", method = GET)
    public String personmate(){
        return "admin_pchange";
    }

    @RequestMapping(value = "/personma", method = GET)
    public String changeperson(@RequestParam(value = "email", defaultValue = "") String email,
                           @RequestParam(value = "phone", defaultValue = "") String phone, HttpSession session){

 Admin admin= (Admin) session.getAttribute("admin");
 adminRepository.updateAdmin(admin.getAdmin_name(),email,phone,admin);
 admin.setAdmin_email(email);
 admin.setAdmin_phone(phone);
session.setAttribute("admin",admin);
        return "admin_addsuccess";
    }

}
