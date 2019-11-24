package ordering.utils;

import ordering.domain.Category;
import ordering.domain.Dish;

import java.util.List;

/**
 * 类别菜品辅助类
 *
 * @author neilly
 * @version 1.0
 * @since 2019/11/24 16:50
 */
public class CategoryDishSupport {

    /**
     * 菜品列表
     */
    private List<Dish> dishes;

    /**
     * 类别
     */
    private Category category;

    /**
     * 构造方法
     *
     * @param dishes
     * @param category
     */
    public CategoryDishSupport(List<Dish> dishes, Category category) {
        setDishes(dishes);
        setCategory(category);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
