package ordering.controller;

import ordering.repository.DishRepository;
import ordering.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

/**
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/23 20:45
 */
@Controller
@SessionAttributes({"/shoppingCart"})
@RequestMapping(value = "shoppingCart")
public class ShoppingCartController {

    @Autowired
    private DishRepository dishRepository;

    /**
     * 查看购物车页面
     *
     * @param model
     * @return 购物车页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewShoppingCart(Model model){
        return "customer_shopping_cart";
    }

    /**
     * 向购物车中添加商品
     *
     * @param dish_id
     * @param model
     * @param session
     * @return 重定向到显示购物车页面
     */
    @RequestMapping(value = "/addDish",method = RequestMethod.POST)
    public String addDishToShoppingCart(@RequestParam(value = "dish_id") String dish_id, Model model, HttpSession session){
        ShoppingCart shoppingCart= (ShoppingCart) session.getAttribute("shoppingCart");
        shoppingCart.addItemToShoppingCart(dishRepository.findById(dish_id));
        model.addAttribute(shoppingCart);
        new RedirectAttributesModelMap().addAttribute("info","add_success");
        return "redirect:/shoppingCart";
    }

}
