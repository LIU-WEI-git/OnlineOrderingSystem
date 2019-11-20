package ordering.model;

import ordering.domain.Category;
import ordering.domain.Dish;

import java.util.List;

/**
 * 菜品类别辅助类
 *
 * @author neilly
 * @version 1.0
 * @since 2019/11/20
 */
public class DishCategorySupport {

    /**
     * 菜品类别
     */
    private List<Category> categories;

    /**
     * 菜品
     */
    private Dish dish;

    /**
     * 构造方法
     *
     * @param categories 菜品类别
     * @param dish 菜品
     */
    public DishCategorySupport(List<Category> categories, Dish dish) {
        setCategories(categories);
        setDish(dish);
    }

    /**
     * 取得菜品全部类别
     *
     * @return 菜品全部类别
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * 设置菜品全部类别
     *
     * @param categories 菜品类别
     */
    private void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     *
     * @return
     */
    public Dish getDish() {
        return dish;
    }

    private void setDish(Dish dish) {
        this.dish = dish;
    }
}
