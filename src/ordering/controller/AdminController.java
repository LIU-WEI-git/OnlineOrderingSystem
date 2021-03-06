package ordering.controller;

import ordering.domain.Admin;
import ordering.domain.Category;
import ordering.domain.Dish;
import ordering.domain.Order;
import ordering.repository.*;
import ordering.utils.CategoryDishSupport;
import ordering.utils.DishCategorySupport;
import ordering.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


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
    @Autowired
    private  OrderItemInfoViewRepository orderItemInfoViewRepository;
    @Autowired
    private  OrderAddressInfoViewRepository orderAddressInfoViewRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private static boolean isLegalDate(String sDate) {
        int legalLen = 10;
        if ((sDate == null) || (sDate.length() != legalLen)) {
            return false;
        }

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }}

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
            e.printStackTrace();
        }
        if (admin != null&&admin.getDelete_tag()==admin.UNDELETED) {
            session.setAttribute("admin", admin);
            session.setAttribute("name", admin.getAdmin_name());
            session.setAttribute("l",null);
            return "redirect:/admin/overall";
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


    @RequestMapping(value = "/Achange", method = GET)
    public String A_change(@RequestParam(value = "admin_username", defaultValue = "") String name,HttpSession session) {
       Admin peach=adminRepository.findAdminByUsername(name);
      session.setAttribute("peach",peach);
        return "admin_userchange";
    }


    @RequestMapping(value = "/Achange", method = POST)
    public String B_change(@RequestParam(value = "password", defaultValue = "") String password,
                           HttpSession session) {
        Admin admin= (Admin) session.getAttribute("peach");
        adminRepository.updateAdminPassword(password,admin);

        return "admin_addsuccess";
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
    public String showdishlist(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,HttpSession session,Model model) {
       /* List<Dish> list=dishRepository.getAll();*/
       PaginationSupport<DishCategorySupport> list=dishRepository.findByPage(pageNo,pageSize);
        List<Category> categories=categoryRepository.getCategoryList();
        model.addAttribute("list",list);
        /*session.setAttribute("list",list);*/
        session.setAttribute("categories",categories);
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
       /*List<Category> tcategories=categoryRepository.getCategoryList();*/
        session.setAttribute("dish",dish);
       /* session.setAttribute(" tcategories", tcategories);*/
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
                             @RequestParam(value = "message", defaultValue = "") String message,@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                             @RequestParam(value = "pageSize", defaultValue = "4") int pageSize,HttpSession session,Model model) {
        if(signal.equals("all")){
            if(message.equals("")){
          /*  List<Dish> list=dishRepository.getAll();
            session.setAttribute("list",list);*/
                PaginationSupport<DishCategorySupport> list=dishRepository.findByPage(pageNo,pageSize);
                model.addAttribute("list",list);
            return "admin_dish";}
            else{
               /* List<Dish> list=new ArrayList<>();*/
                PaginationSupport<DishCategorySupport> list=dishRepository.searchByKeywordsPage(message,pageNo,pageSize);
               /* for (int i = 0; i < p.getItems().size(); i++) {
                    Dish dish=p.getItems().get(i).getDish();
                    list.add(dish);
                }*/
                model.addAttribute("list",list);
                return "admin_dish";}
        }
        else{
        Category category=categoryRepository.getCategoryByName(signal);
            /* CategoryDishSupport w=categoryRepository.listCategoryDishes(category)*/
            PaginationSupport<DishCategorySupport> list=dishRepository.searchByCategoryPage(category,pageNo,pageSize);
       /* List<Dish> list=w.getDishes();*/
            model.addAttribute("list",list);

        return "admin_dish";}
    }

    /**
     * 改变菜品
     * @param id
     * @param name
     * @param price
     * @param description
     * @param dish_picture
     * @param cate
     * @return
     */
    @RequestMapping(value = "/changedish", method = POST)
    public String changdishac(@RequestParam(value = "m_id", defaultValue = "") String id,
                              @RequestParam(value = "dish_name", defaultValue = "") String name,
                              @RequestParam(value = "dish_price", defaultValue = "") float price,
                              @RequestParam(value = "dish_description", defaultValue = "") String description,
                              @RequestPart(value = "dish_picture") MultipartFile dish_picture,
                              @RequestParam(value = "cate", defaultValue = "") String[] cate,
                              HttpSession session) {

        String t = Arrays.toString(cate);
        String t2=t.substring(1,t.length()-1);
        List<Category> categories=new ArrayList<>();
        String[] split = t2.split(", ");
        for (int i = 0; i < split.length; i++) {
            String c=split[i];
            Category m=categoryRepository.getCategoryById(c);
            categories.add(m);
        }
        try {
            //处理上传的图片
            String originalFilename = dish_picture.getOriginalFilename();
            split = originalFilename.split("\\\\");
            String fileName = split[split.length - 1];
            String urlInDB = "images/" + fileName;
            String url = session.getServletContext().getRealPath("resources/images/");
            File uploadFile = new File(url, fileName);
            dish_picture.transferTo(uploadFile);

            Dish dish = new Dish(id, name, urlInDB, price, description);
            DishCategorySupport p = new DishCategorySupport(categories, dish);

            dishRepository.updateDish(name, urlInDB, categories, price, description, p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/dish";
    }

    /**
     * 显示菜品类别
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/dishcategory", method = GET)
    public String showdishcategory(HttpSession session) {
        List<Category> blist=categoryRepository.getCategoryList();
        session.setAttribute("blist",blist);
        return "admin_dishcategory";
    }

    /**
     * 个人信息
     * @return
     */

    @RequestMapping(value = "/person", method = GET)
    public String personal(){
        return "admin_person";
    }

    /**
     * 个人信息修改跳转
     * @return
     */
    @RequestMapping(value = "/personmate", method = GET)
    public String personmate(){
        return "admin_pchange";
    }

    /**
     * 个人信息修改
     * @param email
     * @param phone
     * @param session
     * @return
     */
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
        try {
            a = categoryRepository.getCategoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{b=categoryRepository.getCategoryByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    /**
     * 删除菜品
     * @param id
     * @return
     */
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
    public String addadish(HttpSession session){
        List<Category> pcategories=categoryRepository.getCategoryList();
        session.setAttribute("pcategories",pcategories);

        return "admin_dishadd";
    }

    /**
     * 添加菜品
     * @param id
     * @param name
     * @param price
     * @param dish_picture
     * @param description
     * @param session
     * @return
     */
    @RequestMapping(value = "/adddish", method = POST)
    public String adddish(@RequestParam(value = "id") String id,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "price") float price,
                          @RequestPart(value = "dish_picture") MultipartFile dish_picture,
                          @RequestParam(value = "description") String description,
                          @RequestParam(value = "cate") String[] cate, Model model, HttpSession session) {
        Dish a=null;
        try {
            a = dishRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Category> categories=new ArrayList<>();
        if(a==null){
            String t = Arrays.toString(cate);
            String t2=t.substring(1,t.length()-1);
            String[] split = t2.split(", ");
            for (int i = 0; i < split.length; i++) {
                String c=split[i];
                Category m=categoryRepository.getCategoryById(c);
                categories.add(m);

            }
            try {
                //处理上传的图片
                String originalFilename = dish_picture.getOriginalFilename();
                split = originalFilename.split("\\\\");
                String fileName = split[split.length - 1];
                String urlInDB = "images/" + fileName;
                String url = session.getServletContext().getRealPath("resources/images/");
                File uploadFile = new File(url, fileName);
                dish_picture.transferTo(uploadFile);

                Dish dish = new Dish(id, name, urlInDB, price, description);
                DishCategorySupport p = new DishCategorySupport(categories, dish);
                dishRepository.addDish(p);
                /*dishRepository.save(dish);*/
                session.setAttribute("f", null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:/admin/dish";
        } else {

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

    /**
     * 订单页跳转
     * @param session
     * @return
     */
   /* @RequestMapping(value="/order",method = GET)
    public String viewCustomerOrder(HttpSession session)
    {
        List<Order> orders=null;

         orders=orderRepository.findall();


        session.setAttribute("orders",orders);
        return "admin_orderlist";
    }*/
    @RequestMapping(value="/order" ,method = GET)
    public String viewCustomerOrder(@RequestParam(value = "pageNo",defaultValue="1")int pageNo,@RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
                                    @RequestParam(value = "complete",required = false)String complete,Model model, HttpSession session)
    {
/*
        PaginationSupport<Order> paginationSupport=orderRepository.adminFindPage(pageNo,pageSize);*//*customerFindPage(orders,pageNo,pageSize);*//*
        model.addAttribute("paginationSupport",paginationSupport);*/

        List<Order> orders=new ArrayList<>();
        if(complete==null)
        {
            orders=orderRepository.getOrders();
            /*orders=orderRepository.getCustomerOrders(((Customer)session.getAttribute("customer")).getCustomer_account());*/
        }else if(complete.equals("0"))
        {
            orders=orderRepository.getUncomfirmedOrder();
            /* orders=orderRepository.completedOrders(((Customer)session.getAttribute("customer")).getCustomer_account());*/
        }
        else if(complete.equals("3"))
        {
            orders=orderRepository.getConfirmedAndUndeliveredOrder();
            /* orders=orderRepository.completedOrders(((Customer)session.getAttribute("customer")).getCustomer_account());*/
        }
        else if(complete.equals("2"))
        {
            orders=orderRepository.getCompletedOrder();
           /* orders=orderRepository.completedOrders(((Customer)session.getAttribute("customer")).getCustomer_account());*/
        }
        else if(complete.equals("1"))
        {
            orders=orderRepository.getDeliveringOrder();
            /* orders=orderRepository.completedOrders(((Customer)session.getAttribute("customer")).getCustomer_account());*/
        }
        /*else{
            orders=orderRepository.getCompletedOrder();
           *//* orders=orderRepository.uncompletedOrders(((Customer)session.getAttribute("customer")).getCustomer_account());*//*
        }*/
        //orders = orderRepository.getCustomerOrders(String.valueOf(10086123));
        if(pageNo<=0)
        {
            pageNo=1;
        }
        PaginationSupport<Order> paginationSupport=orderRepository.customerFindPage(orders,pageNo,pageSize);
        model.addAttribute("paginationSupport",paginationSupport);
        if(complete==null)
        {
            model.addAttribute("complete","all");
        }
        else {
            model.addAttribute("complete",complete);
        }
        return "admin_orderlist";
    }
    /**
     * 订单id搜素
     * @param id
     * @return
     */
    @RequestMapping(value="/ordered",method = GET)
    public String Odered(@RequestParam(value = "id", defaultValue = "") String id)
    {
        Order order=orderRepository.getOrder(id);


        return "admin_orderlist";
    }


    /**
     * 总览页面
     * @param session
     * @return
     */
    @RequestMapping(value="/overall",method = GET)
    public String overalll(HttpSession session)
    {
 double income=orderRepository.getTotalIncome();
 long totalorder=orderRepository.getTotalCompletedOrdersNum();
 int cs=customerRepository.totalCustomers();
 long as=adminRepository.count();
 session.setAttribute("income",income);
 session.setAttribute("totalorder",totalorder);
 session.setAttribute("cs",cs);
 session.setAttribute("as",as);
 session.removeAttribute("dayincome");
 session.removeAttribute("dayorder");
        return "admin_allview";
    }


    /**
     * 按type查找order
     * @param signal
     * @param session
     * @return
     */
    @RequestMapping(value = "/ordertype", method = GET)
    public String searchdish(@RequestParam(value = "signal", defaultValue = "") String signal,
                            HttpSession session) {

        List<Order> orders=new ArrayList<>();
        if(signal.equals("deliverying")){
            orders=orderRepository.getDeliveringOrder();

          }
       else if(signal.equals("deliveried")){
         orders=orderRepository.getCompletedOrder();


       }
       else if(signal.equals("nodelivery")){
             orders=orderRepository.getConfirmedAndUndeliveredOrder();

        }else if(signal.equals("noconfirmed")){
            orders=orderRepository.getUncomfirmedOrder();

        }
       else if(signal.equals("all")){
            orders=orderRepository.findall();

        }
        session.setAttribute("orders",orders);
       return "admin_orderlist";
        }

    /**
     * 搜索订单
     * @param message
     * @return
     */
    @RequestMapping(value = "/searchorder", method = GET)
    public String searchorderbycustomer(@RequestParam(value = "message", defaultValue = "") String message,
                                        @RequestParam(value = "pageNo",defaultValue="1")int pageNo,@RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
                                        Model model) {
        List<Order> orders=new ArrayList<>();
        orders=orderRepository.getCustomerOrders(message);
        PaginationSupport<Order> paginationSupport=orderRepository.customerFindPage(orders,pageNo,pageSize);
        model.addAttribute("paginationSupport",paginationSupport);
       /*session.setAttribute("orders",orders);*/


            return "admin_orderlist";
    }

    @RequestMapping(value = "/searchincome", method = GET)
    public String searchincome(@RequestParam(value = "date", defaultValue = "") String date,Model model,HttpSession session) {
      boolean a=isLegalDate(date);
        if (a) {

            double dayincome = orderRepository.getTotalIncomeByDay(date);

       long   dayorder=orderRepository.getTotalCompletedOrdersByDayNum(date);
        session.setAttribute("dayincome",dayincome);
        session.setAttribute("dayorder",dayorder);
        model.addAttribute("error" ,null);
        }
        else{
            String error="请填写正确格式";
            model.addAttribute("error" ,error);
            session.setAttribute("dayincome",0);
            session.setAttribute("dayorder",0);
        }
        return "admin_allview";
    }

    /**
     * 确认订单
     * @param id
     * @return
     */
    @RequestMapping(value="/confirmorder",method = GET)
    public String confirmorder(@RequestParam(value = "order_id", defaultValue = "") String id)
    {
        Order order=orderRepository.getOrder(id);
        orderRepository.confirmOrder(order);
        return "redirect:/admin/order";
    }

    /**
     * 开始配送
     * @param id
     * @return
     */
    @RequestMapping(value="/begindeliver",method = GET)
    public String begindeliver(@RequestParam(value = "order_id", defaultValue = "") String id)
    {
        Order order=orderRepository.getOrder(id);
        orderRepository.confirmDelivery(order);
        return "redirect:/admin/order";
    }

    /**
     * 结束配送
     * @param id
     * @return
     */
    @RequestMapping(value="/enddeliver",method = GET)
    public String enddeliver(@RequestParam(value = "order_id", defaultValue = "") String id)
    {
        Order order=orderRepository.getOrder(id);
        orderRepository.completeDelivery(order);
        orderRepository.completeOrder(order);
        return "redirect:/admin/order";
    }

    /**
     * 订单详情
     * @param order_id
     * @param model
     * @return
     */
    @RequestMapping(value="/order_item",method=GET)
    public String viewOrderItemInfo(@RequestParam(value="order_id" ) String order_id ,Model model)
    {

        model.addAttribute("orderitems",orderItemInfoViewRepository.getOrderItems(order_id));
        return "admin_orderitem";
    }

    /**
     * 订单地址详情
     * @param order_id
     * @param model
     * @return
     */
    @RequestMapping(value = "/address_info",method = GET)
    public String viewOrderAddressInfo(@RequestParam(value ="order_id" )String order_id, Model model)
    {

        model.addAttribute("address",orderAddressInfoViewRepository.getAddress(order_id));
        return "admin_orderadress";
    }

    @RequestMapping(value = "/reo",method = GET)
    public String Reo()
    {

        return "redirect:/admin/overall";
    }

    @RequestMapping(value = "/explain",method = GET)
    public String explain()
    {

        return "admin_qiye";
    }
}
