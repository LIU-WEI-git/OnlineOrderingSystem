package ordering.repository.jdbc;

import ordering.domain.Category;
import ordering.domain.Dish;
import ordering.repository.DishRepository;
import ordering.utils.DishCategorySupport;
import ordering.utils.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜品内容资源库接口的jdbc实现类
 *
 * @author neilly
 * @version 1.0
 * @since 2019/11/19 11:06
 */
@Repository
public class JdbcDishRepository implements DishRepository {

    private JdbcTemplate jdbc;

    /**
     * 自动注入数据
     *
     * @param jdbc
     */
    @Autowired
    public JdbcDishRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * 菜品数量
     *
     * @return 菜品数量
     */
    @Override
    public long count() {
        return jdbc.queryForObject("SELECT COUNT(dish_id) FROM dish WHERE delete_tag=0", long.class);
    }

    /**
     * 查询菜品
     *
     * @param dish_id 菜品ID
     * @return 菜品
     */
    @Override
    public Dish findById(String dish_id) {
        try {
            return jdbc.queryForObject(SELECT_FROM_DISH + " AND dish_id=?", new DishRowMapper(), dish_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 模糊搜索菜品名称，或直接搜索完整的菜品ID，分页列出
     *
     * @param keywords 菜品关键词（菜品名称、菜品ID）
     * @param pageNo   起始位置
     * @param PageSize 每页数量
     * @return 分页菜品
     */
    @Override
    public PaginationSupport<DishCategorySupport> searchByKeywordsPage(String keywords, int pageNo, int PageSize) {
        int totalCount = jdbc.queryForObject("SELECT COUNT(*) FROM Dish WHERE dish.delete_tag=0 AND (dish_name like '%" + keywords + "%' or dish_id='" + keywords + "')", int.class);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, PageSize);
        if (totalCount < 1)
            return new PaginationSupport<>(new ArrayList<>(0), 0);

        List<Dish> dishes = jdbc.query(SELECT_FROM_DISH + " AND (dish_name LIKE '%" + keywords + "%' OR dish_id='" + keywords + "')" + SELECT_PAGE, new DishRowMapper(), PageSize, startIndex);
        List<DishCategorySupport> items = new ArrayList<>();
        for (Dish dish : dishes) {
            items.add(listDishCategories(dish));
        }
        return new PaginationSupport<>(items, totalCount, PageSize, startIndex);
    }

    /**
     * 获取全部菜品，未分页
     *
     * @return 菜品列表
     */
    @Override
    public List<Dish> getAll() {
        return jdbc.query(SELECT_FROM_DISH, new DishRowMapper());
    }

    /**
     * 搜索菜品类别列出所有菜品
     *
     * @param category 选择的菜品类别
     * @param pageNo   起始位置
     * @param PageSize 每页数量
     * @return 分页菜品
     */
    @Override
    public PaginationSupport<DishCategorySupport> searchByCategoryPage(Category category, int pageNo, int PageSize) {
        int totalCount = jdbc.queryForObject("SELECT COUNT(*) FROM dish d JOIN dish_category dc WHERE d.dish_id=dc.dish_id AND category_id='" + category.getCategory_id() + "'", int.class);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, PageSize);
        if (totalCount < 1)
            return new PaginationSupport<>(new ArrayList<>(0), 0);

        List<Dish> dishes = jdbc.query(SELECT_CATEGORY_DISH + SELECT_PAGE, new DishRowMapper(), category.getCategory_id(), PageSize, startIndex);
        List<DishCategorySupport> items = new ArrayList<>();
        for (Dish dish : dishes) {
            items.add(listDishCategories(dish));
        }
        return new PaginationSupport<>(items, totalCount, PageSize, startIndex);
    }

    /**
     * 分页获取所有菜品
     *
     * @param pageNo   起始位置
     * @param PageSize 每页数量
     * @return 菜品列表
     */
    @Override
    public PaginationSupport<DishCategorySupport> findByPage(int pageNo, int PageSize) {
        int totalCount = (int) count();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, PageSize);
        if (totalCount < 1)
            return new PaginationSupport<>(new ArrayList<>(0), 0);

        List<Dish> dishes = jdbc.query(SELECT_FROM_DISH + SELECT_PAGE, new DishRowMapper(), PageSize, startIndex);
        List<DishCategorySupport> items = new ArrayList<>();
        for (Dish dish : dishes) {
            items.add(listDishCategories(dish));
        }
        return new PaginationSupport<>(items, totalCount, PageSize, startIndex);
    }

    /**
     * 列出指定菜品的所有类别
     *
     * @param dish 菜品
     * @return 菜品和对应类别
     */
    @Override
    public DishCategorySupport listDishCategories(Dish dish) {

        List<Category> categories = jdbc.query(SELECT_DISH_CATEGORY, new CategoryRowMapper(), dish.getDish_id());
        return new DishCategorySupport(categories, dish);
    }


//    @Override
//    public List<Dish> searchDish(String keywords) {
//
//        return jdbc.query(SELECT_DISH, new DishRowMapper(), keywords, keywords);
//    }


    /**
     * 删除菜品
     * 包括：删除dish表、dish_category表信息
     *
     * @param dish_id 菜品ID
     */
    @Override
    public void deleteDish(String dish_id) {
        // 在dish_category内删除
        jdbc.update(DELETE_DISH_CATEGORY, dish_id);
        // 在dish内删除
        jdbc.update(DELETE_DISH, dish_id);
    }

    /**
     * 添加菜品
     *
     * @param dishCategorySupport 菜品
     */
    @Override
    public void addDish(DishCategorySupport dishCategorySupport) {
        // 添加dish表
        SimpleJdbcInsert jdbcInsertDish = new SimpleJdbcInsert(jdbc).withTableName("dish");
        Map<String, Object> dishArgs = new HashMap<>();
        // TODO: dish ID自动生成
        dishArgs.put("dish_id", dishCategorySupport.getDish().getDish_id());
        dishArgs.put("dish_name", dishCategorySupport.getDish().getDish_name());
        dishArgs.put("picture_url", dishCategorySupport.getDish().getPicture_url());
        dishArgs.put("price", dishCategorySupport.getDish().getPrice());
        dishArgs.put("description", dishCategorySupport.getDish().getDescription());
        jdbcInsertDish.execute(dishArgs);

        // 添加dish_category表
        SimpleJdbcInsert jdbcInsertDish_Category = new SimpleJdbcInsert(jdbc).withTableName("dish_category");
        Map<String, Object> dish_categoryArgs = new HashMap<>();
        for (Category category : dishCategorySupport.getCategories()) {
            dish_categoryArgs.put("category_id", category.getCategory_id());
            dish_categoryArgs.put("dish_id", dishCategorySupport.getDish().getDish_id());
            jdbcInsertDish_Category.execute(dish_categoryArgs);
        }

    }

    /**
     * 更新菜品
     * 包括：更新dish信息、更新dish_category信息（先删除原有的，重新添加）
     *
     * @param dish_name           新菜品名称
     * @param picture_url         新图片url
     * @param price               新价格
     * @param description         新菜品描述
     * @param dishCategorySupport 菜品类别辅助对象
     */
    @Override
    public void updateDish(String dish_name, String picture_url, List<Category> categories, float price, String description, DishCategorySupport dishCategorySupport) {
        // 更新dish表
        jdbc.update(UPDATE_DISH, dish_name, picture_url, price, description, dishCategorySupport.getDish().getDish_id());

        // 先删除dish_category表内所有和此菜品有关信息
        jdbc.update(DELETE_DISH_CATEGORY, dishCategorySupport.getDish().getDish_id());

        // 重新添加dish_category表
        // 保存添加后状态
        dishCategorySupport.setCategories(categories);
        // jdbc添加
        SimpleJdbcInsert jdbcInsertDish_Category = new SimpleJdbcInsert(jdbc).withTableName("dish_category");
        Map<String, Object> dish_categoryArgs = new HashMap<>();
        for (Category category : categories) {
            dish_categoryArgs.put("category_id", category.getCategory_id());
            dish_categoryArgs.put("dish_id", dishCategorySupport.getDish().getDish_id());
            jdbcInsertDish_Category.execute(dish_categoryArgs);
        }

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

    /**
     * SQL语句
     */
    // 取得所有菜品
//    private static final String SELECT_DISH="SELECT dish_id, dish_name, picture_url, price, description FROM dish WHERE dish_id  LIKE ? OR dish_name LIKE ?";
    private static final String SELECT_FROM_DISH = "SELECT dish_id, dish_name, picture_url, price, description FROM dish WHERE delete_tag=0";
    // 分页
    private static final String SELECT_PAGE = " ORDER BY dish_id LIMIT ? OFFSET ?";
    // 根据菜品ID取得对应菜品类别列表
    private static final String SELECT_DISH_CATEGORY = "SELECT dc.dish_id, c.category_id, c.category_name FROM category c JOIN dish_category dc WHERE c.category_id=dc.category_id AND dish_id=?";
    // 删除菜品类别
    private static final String DELETE_DISH_CATEGORY = "DELETE FROM dish_category WHERE dish_id=?";
    // 删除菜品
//    private static final String DELETE_DISH = "DELETE FROM dish WHERE dish_id=?";
    private static final String DELETE_DISH = "UPDATE dish SET delete_tag=1 WHERE dish_id=?";
    // 更新菜品
    private static final String UPDATE_DISH = "UPDATE dish SET dish_name=?, picture_url=?, price=?, description=? WHERE dish_id=?";
    // 搜索菜品类别
    private static final String SELECT_CATEGORY_DISH = "SELECT dc.category_id, d.dish_id, d.dish_name, d.picture_url, d.price, d.description FROM dish d JOIN dish_category dc WHERE d.dish_id=dc.dish_id AND category_id=?";
}
