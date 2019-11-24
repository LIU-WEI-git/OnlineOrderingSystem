package ordering.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import ordering.domain.Admin;
import ordering.repository.AdminRepository;
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
        List<Admin> list=adminRepository.findadminlsit();
        session.setAttribute("list",list);
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
}
