package ordering.domain;

/**
 * Category实体类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/9 21:04
 */
public class Category {

    private static final int UNDELETED = 0;

    private String category_id;
    private String category_name;
    private int delete_tag;

    /**
     * 无参构造函数
     */
    public Category() {
    }

    /**
     * 构造函数
     *
     * @param category_id
     * @param category_name
     */
    public Category(String category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.delete_tag = UNDELETED;
    }

    /**
     * 构造函数
     *
     * @param category_id
     * @param category_name
     * @param delete_tag
     */
    public Category(String category_id, String category_name, int delete_tag) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.delete_tag = delete_tag;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getDelete_tag() {
        return delete_tag;
    }

    public void setDelete_tag(int delete_tag) {
        this.delete_tag = delete_tag;
    }
}
