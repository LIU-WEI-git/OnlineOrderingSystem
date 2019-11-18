package ordering.controller;

import ordering.repository.jdbc.JdbcAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private JdbcAdminRepository adminRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String hello(Model model) {
        //测试数据库连接，界面应当显示数据库第一个admin的account
        model.addAttribute("admin", adminRepository.getAdminList().get(0));
        return "hello";
    }

//    @RequestMapping(value = "/alogin", method = GET)
//    public String showLoginForm() {
//        return "adminlogin";
//    }
}
