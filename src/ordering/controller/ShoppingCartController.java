package ordering.controller;

import ordering.repository.DishRepository;
import ordering.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/23 20:45
 */
@Controller
@SessionAttributes({"shoppingCart"})
@RequestMapping(value = "/shoppingCart")
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
    @RequestMapping(value = "/addDish", method = {RequestMethod.POST, RequestMethod.GET})
    public String addDishToShoppingCart(@RequestParam(value = "dish_id") String dish_id, Model model, HttpSession session){
        //如果session属性中还没有customer，说明顾客还未登录，则跳转到登录界面
        if(session.getAttribute("customer")==null){
            return "redirect:/login";
        }
        ShoppingCart shoppingCart= (ShoppingCart) session.getAttribute("shoppingCart");
        shoppingCart.addItemToShoppingCart(dishRepository.findById(dish_id));
        model.addAttribute(shoppingCart);
        return "redirect:/shoppingCart";
    }

    /**
     * 购物车中的某一商品数量减一
     *
     * @param dish_id
     * @param model
     * @param session
     * @return 重定向到显示购物车页面
     */
    @RequestMapping(value = "/minusDish", method = RequestMethod.GET)
    public String minusDishFromShoppingCart(@RequestParam(value = "dish_id") String dish_id, Model model, HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        shoppingCart.minusItemFromShoppingCart(dish_id);
        model.addAttribute(shoppingCart);
        return "redirect:/shoppingCart";
    }

    /**
     * 删除购物车中的一项商品
     *
     * @param dish_id
     * @param model
     * @param session
     * @return 重定向到显示购物车页面
     */
    @RequestMapping(value = "/deleteDish", method = RequestMethod.GET)
    public String deleteDishFromShoppingCart(@RequestParam(value = "dish_id") String dish_id, Model model, HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        shoppingCart.deleteItemFromShoppingCart(dish_id);
        model.addAttribute(shoppingCart);
        return "redirect:/shoppingCart";
    }
}