package ordering.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import ordering.domain.*;
import ordering.repository.AdminRepository;
import ordering.repository.CategoryRepository;
import ordering.repository.DishRepository;
import ordering.repository.OrderRepository;
import ordering.utils.CategoryDishSupport;
import ordering.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private OrderRepository orderRepository;
    /**
     * 管理员登陆
     * @return
     */
    @RequestMapping(value = "/alogin", method = GET)
    public String showLoginForm() {
        return "adminlogin";
    }

    /**
     *管理员登陆
     * @param useraccount
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/alogin", method = POST)
    public String processLogin(@RequestParam(value = "useraccount", defaultValue = "") String useraccount,
                               @RequestParam(value = "password", defaultValue = "") String password, HttpSession session) {
        Admin admin=null;
        String l=null;
        try{
        admin = adminRepository.findByUserName(useraccount, password);}
        catch(Exception e){

        }
        if (admin != null&&admin.getDelete_tag()==admin.UNDELETED) {
            session.setAttribute("admin", admin);
            session.setAttribute("name", admin.getAdmin_name());
            return "admin_welcome";
        }
        else{
            l="用户名和密码不匹配";
            session.setAttribute("l",l);
            return "redirect:/admin/alogin";}




    }


    /**
     *管理员列表
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
     *删除管理员
     * @param userName
     * @return
     */
    @RequestMapping(value = "/deleteadmin", method = GET)
    public String deletedmin(@RequestParam(value = "admin_username", defaultValue = "") String userName) {
       adminRepository.deleteAdmin(adminRepository.findAdminByUsername(userName));
        return "redirect:/admin/user";
    }

    /**
     *添加管理员
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
     *添加管理员
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
     *显示菜品
     * @param session
     * @return
     */
    @RequestMapping(value = "/dish", method = GET)
    public String showdishlist(HttpSession session) {
        List<Dish> list=dishRepository.getAll();
        session.setAttribute("list",list);
        return "admin_dish";
    }

    /**
     * 修改菜品
     * @param dishid
     * @param session
     * @return
     */
    @RequestMapping(value = "/changedish", method = GET)
    public String changdish(@RequestParam(value = "dish_id", defaultValue = "") String dishid,HttpSession session) {
       Dish dish=dishRepository.findById(dishid);
        session.setAttribute("dish",dish);
        return "admin_dishchange";
    }

    /**
     * 分类展示菜品
     * @param signal
     * @param message
     * @param session
     * @return
     */
    @RequestMapping(value = "/searchdish", method = GET)
    public String searchdish(@RequestParam(value = "signal", defaultValue = "") String signal,
                             @RequestParam(value = "message", defaultValue = "") String message,HttpSession session) {
        if(signal.equals("all")){
            if(message.equals(null)){
            List<Dish> list=dishRepository.getAll();
            session.setAttribute("list",list);
            return "admin_dish";}
            else{
                List<Dish> list=dishRepository.searchDish(message);
                session.setAttribute("list",list);
                return "admin_dish";}
        }
        else{
        Category category=categoryRepository.getCategoryByName(signal);
        CategoryDishSupport w=categoryRepository.listCategoryDishes(category);
        List<Dish> list=w.getDishes();
        session.setAttribute("list",list);

        return "admin_dish";}
    }

    /**
     *
     * @return
     */
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
        return "redirect:/admin/person";
    }

    /**
     * 管理员登出
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = GET)
    public String logout(HttpSession session){
    session.removeAttribute("admin");
    return "redirect:/admin/alogin";
    }

    /**
     * 删除菜品类
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/deletecategory", method = GET)
    public String deletecategory(@RequestParam(value = "category_id", defaultValue = "") String id,HttpSession session) {
        Category category=categoryRepository.getCategoryById(id);
        CategoryDishSupport w=categoryRepository.listCategoryDishes(category);
        List<Dish> list=w.getDishes();
        if(list.isEmpty()){
        categoryRepository.deleteCategory(categoryRepository.getCategoryById(id));
        session.setAttribute("a",null);}
   else{
       String  a="提示：删除失败要删除的菜品类还包含菜品";
       session.setAttribute("a", a);
    }
   return "redirect:/admin/dishcategory";}

    /**
     * 菜品类更改
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/turnchange", method = GET)
    public String turnchange(@RequestParam(value = "category_id", defaultValue = "") String id,HttpSession session){
        Category category=categoryRepository.getCategoryById(id);
        session.setAttribute("category",category);
        return "admin_categorychange";
    }

    /**
     * 菜品类更改
     * @param name
     * @param session
     * @return
     */
    @RequestMapping(value = "/categorychange", method = GET)
    public String changecategory(@RequestParam(value = "name", defaultValue = "") String name, HttpSession session){

        Category category= (Category) session.getAttribute("category");
        categoryRepository.renameCategory(category.getCategory_name(),name);

        return "redirect:/admin/dishcategory";
    }


    /**
     * 添加菜品类
     * @return
     */
    @RequestMapping(value = "/addcategory" , method = GET)
    public String addacategory(){
        return "admin_categoryadd";
    }

    /**
     * 添加菜品类
     * @param id
     * @param name
     * @param session
     * @return
     */

    @RequestMapping(value = "/addcategory", method = POST)
    public String addcategory(@RequestParam(value = "id", defaultValue = "") String id,
                              @RequestParam(value = "name", defaultValue = "") String name,HttpSession session){
        Category a=null;
        Category b=null;
        try{a=categoryRepository.getCategoryById(id);}
        catch(Exception e){}
        try{b=categoryRepository.getCategoryByName(name);
        }catch(Exception e){}
        if(a==null&&b==null){
            Category category=new Category(id,name);
            categoryRepository.addCategory(category);
            session.setAttribute("u", null);
            return "redirect:/admin/dishcategory";}
        else{

            String  u="提示：添加失败，该编号或名称已被使用";
            session.setAttribute("u", u);
            return "redirect:/admin/addcategory";
        }


    }



    @RequestMapping(value = "/deletedish", method = GET)
    public String deletedish(@RequestParam(value = "dish_id", defaultValue = "") String id) {
        /*Dish dish=dishRepository.findById(id);*/
        dishRepository.deleteDish(id);
        return "redirect:/admin/dish";}


    /**
     * 添加菜品
     * @return
     */
    @RequestMapping(value = "/adddish" , method = GET)
    public String addadish(){
        return "admin_dishadd";
    }

    /**
     * 添加菜品
     * @param id
     * @param name
     * @param price
     * @param url
     * @param description
     * @param session
     * @return
     */

    @RequestMapping(value = "/adddish", method = POST)
    public String adddish(@RequestParam(value = "id", defaultValue = "") String id,
                          @RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "price", defaultValue = "") float price,
                          @RequestParam(value = "url", defaultValue = "") String url,
                          @RequestParam(value = "description", defaultValue = "") String description,HttpSession session){
        Dish a=null;
        try{a=dishRepository.findById(id);}catch(Exception e){}
        if(a==null){
            Dish dish=new Dish(id,name,url,price,description);
            dishRepository.save(dish);
            session.setAttribute("f", null);
            return "redirect:/admin/dish";}
        else{

            String  f="提示：添加失败，该菜品编号已被使用";
            session.setAttribute("f", f);
            return "redirect:/admin/adddish";
        }
    }

    /**
     * 修改本人密码
     * @return
     */
    @RequestMapping(value = "/turnpass", method = GET)
    public String turnpass(){
        return "admin_changepassword";
    }

    @RequestMapping(value = "/turnpass", method = POST)
    public String passwordchange(@RequestParam(value = "p_1", defaultValue = "") String p_1,
                                 @RequestParam(value = "p_2", defaultValue = "") String p_2,
                                 @RequestParam(value = "p_3", defaultValue = "") String p_3,HttpSession session){
        String  x=null;
        Admin admin= (Admin) session.getAttribute("admin");
        if(!admin.getAdmin_password().equals(p_1)){
            x="原密码错误";
            session.setAttribute("x",x);
            return "redirect:/admin/turnpass";
        }
        else if(!p_2.equals(p_3)){
            x="两次密码不一致";
            session.setAttribute("x",x);
            return "redirect:/admin/turnpass";
        }
      else{
      adminRepository.updateAdminPassword(p_2,admin);
            session.setAttribute("x",null);
            return "admin_addsuccess";
        }

    }


    @RequestMapping(value="/order",method = GET)
    public String viewCustomerOrder(HttpSession session)
    {
        List<Order> orders=null;
/*try{*/
         orders=orderRepository.findall();
       /* }catch(Exception e){
    }*/
        session.setAttribute("orders",orders);
        return "admin_orderlist";
    }


    @RequestMapping(value="/ordered",method = GET)
    public String Odered(@RequestParam(value = "id", defaultValue = "") String id)
    {
        Order order=orderRepository.getOrder(id);


        return "admin_orderlist";
    }
}
