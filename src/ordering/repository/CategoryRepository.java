package ordering.repository;

import ordering.domain.Category;

import java.util.List;

/**
 * category资源库接口
 *
 * @version: 1.0
 * Created in 2019/11/19 19:25
 */
public interface CategoryRepository {

    /**
     * 判断某菜品名是否在数据库中
     *
     * @param category_name
     * @return 返回判断结果
     */
    boolean isInDB(String category_name);

    /**
     * 获得当前菜品总数
     *
     * @return 菜品总数
     */
    int totalCategories();

    /**
     * 获取所有菜品类别
     *
     * @return 返回菜品类别列表
     */
    List<Category> getCategoryList();

    /**
     * 通过类别id查找类别
     *
     * @param category_id
     * @return 返回一个Category类
     */
    Category getCategoryById(String category_id);

    /**
     * 通过类别名字查找类别
     *
     * @param category_name
     * @return 返回一个Category类
     */
    Category getCategoryByName(String category_name);

    /**
     * 增加一个菜品类别
     *
     * @param category
     * @return 返回是否增加成功的结果
     */
    boolean addCategory(Category category);

    /**
     * 通过Id删除一个菜品类别
     *
     * @param category_id
     * @return 返回被删除的类别
     */
    Category deleteCategoryById(String category_id);

    /**
     * 通过类别名删除一个菜品类别
     *
     * @param category_name
     * @return 返回被删除的类别
     */
    Category deleteCategoryByName(String category_name);

    /**
     * 删除指定category
     *
     * @param category 需要被删除的category对象
     * @return 被删除的category对象
     */
    Category deleteCategory(Category category);

    /**
     * 修改一个菜品类别的名字
     *
     * @param oldName
     * @param newName
     * @return 返回是否修改成功的结果
     */
    boolean renameCategory(String oldName, String newName);
}
