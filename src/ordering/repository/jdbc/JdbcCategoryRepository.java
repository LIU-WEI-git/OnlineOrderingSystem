package ordering.repository.jdbc;

import ordering.domain.Category;
import ordering.domain.Dish;
import ordering.repository.CategoryRepository;
import ordering.utils.CategoryDishSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * category资源库接口的jdbc的实现类
 *
 * @author: 刘威
 * @version: 1.0
 * Created in 2019/11/19 19:40
 */
@Repository
public class JdbcCategoryRepository implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String TOTAL_CATEGORY = "select count(*) from category where delete_tag=0";
    private static final String SELECT_CATEGORY = "select * from category where delete_tag=0";
    private static final String INSERT_CATEGORY = "insert into category (category_id,category_name) values (?,?)";
//    private static final String DELETE_CATEGORY = "delete from category where ";
//    private static final String DELETE_CATEGORY = "delete from category where category_id=?";
    private static final String DELETE_CATEGORY = "update category set delete_tag=1";
    private static final String RENAME_CATEGORY = "update category set category_name=? where category_name=?";

    private static final String SELECT_CATEGORY_DISH = "SELECT dc.category_id, d.dish_id, d.dish_name, d.picture_url, d.price, d.description FROM dish d JOIN dish_category dc WHERE d.dish_id=dc.dish_id AND category_id=?";

    @Autowired
    public JdbcCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isInDB(String category_id) {
        return jdbcTemplate.queryForObject(TOTAL_CATEGORY + " and category_id=?", Integer.class, category_id) != 0;
    }

    @Override
    public int totalCategories() {
        return jdbcTemplate.queryForObject(TOTAL_CATEGORY, Integer.class);
    }

    @Override
    public List<Category> getCategoryList() {
        return jdbcTemplate.query(SELECT_CATEGORY, new CategoryRowMapper());
    }

    @Override
    public Category getCategoryById(String category_id) {
        return jdbcTemplate.queryForObject(SELECT_CATEGORY + " and category_id=?", new CategoryRowMapper(), category_id);
    }

    @Override
    public Category getCategoryByName(String category_name) {
        return jdbcTemplate.queryForObject(SELECT_CATEGORY + " and category_name=?", new CategoryRowMapper(), category_name);
    }

    @Override
    public boolean addCategory(Category category) {
        jdbcTemplate.update(INSERT_CATEGORY, category.getCategory_id(), category.getCategory_name());
        return true;
    }

    @Override
    public Category deleteCategoryById(String category_id) {
        Category deletedCategory = getCategoryById(category_id);
        jdbcTemplate.update(DELETE_CATEGORY + " where category_id=?", category_id);
        return deletedCategory;
    }

    @Override
    public Category deleteCategoryByName(String category_name) {
        Category deletedCategory = getCategoryById(category_name);
        jdbcTemplate.update(DELETE_CATEGORY + " where category_name=?", category_name);
        return deletedCategory;
    }

    @Override
    public Category deleteCategory(Category category) {
        Category deletedCategory = getCategoryById(category.getCategory_id());
        jdbcTemplate.update(DELETE_CATEGORY + " where category_id=?", category.getCategory_id());
        return deletedCategory;
    }

    @Override
    public boolean renameCategory(String oldName, String newName) {
        jdbcTemplate.update(RENAME_CATEGORY, newName, oldName);
        return true;
    }

    @Override
    public CategoryDishSupport listCategoryDishes(Category category) {
        List<Dish> dishes = jdbcTemplate.query(SELECT_CATEGORY_DISH, new DishRowMapper(), category.getCategory_id());
        return new CategoryDishSupport(dishes, category);
    }

    /**
     * 数据库查询建立菜品对象
     */
    private static final class DishRowMapper implements RowMapper<Dish> {

        @Override
        public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
            String dish_id = rs.getString("dish_id");
            String dish_name = rs.getString("dish_name");
            String picture_url = rs.getString("picture_url");
            float price = rs.getFloat("price");
            String description = rs.getString("description");
            return new Dish(dish_id, dish_name, picture_url, price, description);
        }
    }

    /**
     * 数据库查询建立类别对象
     */
    private static final class CategoryRowMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            String category_id = rs.getString("category_id");
            String category_name = rs.getString("category_name");
            return new Category(category_id, category_name);
        }
    }
}
