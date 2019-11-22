package ordering.domain;

/**
 * Dish实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 21:05
 */
public class Dish {

    private String dish_id;
    private String dish_name;
//    private DishCategorySupport category;
    private String picture_url;
    private float price;
    private String description;

    /**
     * 无参构造函数
     */
    public Dish() {
    }

    /**
     * 构造函数
     *
     * @param dish_id
     * @param dish_name
     * @param picture_url
     * @param price
     * @param description
     */
    public Dish(String dish_id, String dish_name, String picture_url, float price, String description) {
        this.dish_id = dish_id;
        this.dish_name = dish_name;
//        this.category = category;
        this.picture_url = picture_url;
        this.price = price;
        this.description = description;
    }

    public String getDish_id() {
        return dish_id;
    }

    public void setDish_id(String dish_id) {
        this.dish_id = dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

//    public DishCategorySupport getCategory() {
//        return category;
//    }
//
//    public void setCategory(DishCategorySupport category) {
//        this.category = category;
//    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
