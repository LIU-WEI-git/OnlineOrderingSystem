package ordering.domain;

/**
 * DishCategory实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 21:13
 */
public class DishCategory {

    private String category_id;
    private String dish_id;

    /**
     * 无参构造函数
     */
    public DishCategory() {
    }

    /**
     * 构造函数
     *
     * @param category_id
     * @param dish_id
     */
    public DishCategory(String category_id, String dish_id) {
        this.category_id = category_id;
        this.dish_id = dish_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getDish_id() {
        return dish_id;
    }

    public void setDish_id(String dish_id) {
        this.dish_id = dish_id;
    }
}
