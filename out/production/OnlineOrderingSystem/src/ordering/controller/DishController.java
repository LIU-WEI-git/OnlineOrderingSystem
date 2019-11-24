package ordering.controller;

import ordering.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 菜品控制器类
 *
 * @author neilly
 * @version 1.0
 * @since 2019/11/19 11:28
 */
@Controller
@RequestMapping("/admin/products")
public class DishController {

    @Autowired
    private DishRepository dishRepository;

//    /**
//     * 展示菜品列表
//     *
//     * @return 菜品列表
//     */
//    @RequestMapping(method = RequestMethod.GET)
//    public String dishes(Model model) {
//        model.addAttribute("dishes", dishRepository.getAll());
//        return "fruits";
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String dishes(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "3") int pageSize, Model model) {
        model.addAttribute("paginationSupport", dishRepository.findByPage(pageNo, pageSize));
    return "admin_products";
    }

}
