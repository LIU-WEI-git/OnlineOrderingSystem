package ordering.repository;

import ordering.domain.Category;
import ordering.domain.Dish;
import ordering.utils.DishCategorySupport;
import ordering.utils.PaginationSupport;

import java.util.List;

/**
 * 菜品资源库接口
 *
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
     * 模糊搜索菜品名称，或直接搜索完整的菜品ID，分页列出
     *
     * @param keywords 菜品关键词（菜品名称、菜品ID）
     * @param pageNo 起始位置
     * @param PageSize 每页数量
     * @return 分页菜品
     */
    PaginationSupport<DishCategorySupport> searchByKeywordsPage(String keywords, int pageNo, int PageSize);

    /**
     * 获取全部菜品（弃用）
     *
     * @return 菜品列表
     */
    List<Dish> getAll();

    /**
     * 搜索菜品类别列出所有菜品
     *
     * @param category 选择的菜品类别
     * @param pageNo 起始位置
     * @param PageSize 每页数量
     * @return 分页菜品
     */
    PaginationSupport<DishCategorySupport> searchByCategoryPage(Category category, int pageNo, int PageSize);

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
     * @param dishCategorySupport 菜品
     */
    void addDish(DishCategorySupport dishCategorySupport);

    /**
     * 更新菜品
     *      包括：更新dish信息、更新dish_category信息（先删除原有的，重新添加）
     *
     * @param dish_name 新菜品名称
     * @param picture_url 新图片url
     * @param price 新价格
     * @param description 新菜品描述
     * @param dishCategorySupport 菜品类别辅助对象
     */
    void updateDish(String dish_name, String picture_url, List<Category> categories, float price, String description, DishCategorySupport dishCategorySupport);

}
