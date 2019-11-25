package ordering.repository.jdbc;

import ordering.domain.Category;
import ordering.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    private RowMapper<Category> categoryRowMapper = new BeanPropertyRowMapper<>(Category.class);

    private static final String TOTAL_CATEGORY = "select count(*) from category";
    private static final String SELECT_CATEGORY = "select * from category";
    private static final String INSERT_CATEGORY = "insert into category (category_id,category_name) values (?,?)";
//    private static final String DELETE_CATEGORY = "delete from category where ";
    private static final String DELETE_CATEGORY = "delete from category where category_id=?";
    private static final String RENAME_CATEGORY = "update category set category_name=? where category_name=?";

    @Autowired
    public JdbcCategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isInDB(String category_name) {
        return jdbcTemplate.queryForObject(TOTAL_CATEGORY + " where category_name=" + category_name, Integer.class) != 0;
    }

    @Override
    public int totalCategories() {
        return jdbcTemplate.queryForObject(TOTAL_CATEGORY, Integer.class);
    }

    @Override
    public List<Category> getCategoryList() {
        return jdbcTemplate.query(SELECT_CATEGORY, categoryRowMapper);
    }

    @Override
    public Category getCategoryById(String category_id) {
        return jdbcTemplate.queryForObject(SELECT_CATEGORY + " where category_id=" + category_id, Category.class);
    }

    @Override
    public Category getCategoryByName(String category_name) {
        return jdbcTemplate.queryForObject(SELECT_CATEGORY + " where category_name=" + category_name, Category.class);
    }

    @Override
    public boolean addCategory(Category category) {
        jdbcTemplate.update(INSERT_CATEGORY, category.getCategory_id(), category.getCategory_name());
        return true;
    }

    @Override
    public Category deleteCategoryById(String category_id) {
        Category deletedCategory = getCategoryById(category_id);
        jdbcTemplate.update(DELETE_CATEGORY + "category_id=" + category_id);
        return deletedCategory;
    }

    @Override
    public Category deleteCategoryByName(String category_name) {
        Category deletedCategory = getCategoryById(category_name);
        jdbcTemplate.update(DELETE_CATEGORY + "category_name=" + category_name);
        return deletedCategory;
    }

    @Override
    public Category deleteCategory(Category category) {
        Category deletedCategory = getCategoryById(category.getCategory_id());
        jdbcTemplate.update(DELETE_CATEGORY,category.getCategory_id());
        return deletedCategory;
    }

    @Override
    public boolean renameCategory(String oldName, String newName) {
        jdbcTemplate.update(RENAME_CATEGORY, newName, oldName);
        return true;
    }
}
