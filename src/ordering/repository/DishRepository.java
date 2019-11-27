package ordering.repository;

import ordering.domain.Dish;
import ordering.utils.DishCategorySupport;
import ordering.utils.PaginationSupport;

import java.util.List;

/**
 * @author neilly
 * @version 1.0
 * @since 2019/11/19 10:48
 */
public interface DishRepository {

    /**
     * 取得菜品数量
     *
     * @return 菜品数量
     */
    long count();
    /**
     * 根据id获取菜品
     *
     * @param dish_id 菜品ID
     * @return 菜品
     */
    Dish findById(String dish_id);

    /**
     * 获取全部菜品
     *
     * @return 菜品列表
     */
    List<Dish> getAll();

    /**
     * 依据页码和指定页面大小，返回菜品列表
     *
     * @param pageNo 起始位置
     * @param PageSize 每页数量
     * @return 分页菜品
     */
    PaginationSupport<DishCategorySupport> findByPage(int pageNo, int PageSize);

    /**
     * 列出菜品的类别列表
     *
     * //@param categories 类别列表
     * @param dish 菜品
     * @return 菜品类别列表
     */
    DishCategorySupport listDishCategories(Dish dish);

    /**
     * 删除指定ID的菜品
     *
     * @param dish_id 菜品ID
     */
    void deleteDish(String dish_id);

    /**
     * 新建一个菜品
     *
     * @param dish 菜品
     * @return 保存的菜品
     */
    Dish save(Dish dish);


    List<Dish> searchDish(String a);
}
