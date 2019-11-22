package ordering.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import ordering.domain.Admin;
import ordering.repository.AdminRepository;
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

    @RequestMapping(value = "/alogin", method = GET)
    public String showLoginForm() {
        return "adminlogin";
    }

    @RequestMapping(value = "/alogin", method = POST)
    public String processLogin(@RequestParam(value = "userName", defaultValue = "") String userName,
                               @RequestParam(value = "password", defaultValue = "") String password, HttpSession session) {

        Admin admin = adminRepository.findAdminByUsername(userName);
        if (admin != null) {
            session.setAttribute("admin", admin);
            session.setAttribute("name", admin.getAdmin_name());
            return "admin_welcome";
        } else {
            return "store";
        }

    }

}
